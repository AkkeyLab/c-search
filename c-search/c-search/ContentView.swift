import SwiftUI
import shared

struct ContentView: View {
    @State private var responseText = ""
    @State private var searchText = ""

    var body: some View {
        VStack {
            Text(responseText)
            Spacer()
            HStack {
                TextField(
                    "Enter corporate name",
                    text: $searchText
                )
                .textFieldStyle(.roundedBorder)
                Button(action: { load() }) {
                    Label("Search", systemImage: "magnifyingglass")
                }
                .buttonStyle(.bordered)
            }
        }
        .padding()
    }

    private func load() {
        guard !searchText.isEmpty,
              let fullWidthString = searchText.applyingTransform(.fullwidthToHalfwidth, reverse: true)
        else { return }
        SearchCorporate().search(name: fullWidthString) { result, _ in
            guard let responseText = result else { return }
            self.responseText = responseText
        }
    }
}

struct ContentViewPreviews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

