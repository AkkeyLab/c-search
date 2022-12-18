import SwiftUI
import shared

struct ContentView: View {
    @State private var responseText = "No search result"
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
        responseText = "Now Loading"
        let networkError: () -> Void = {
            responseText = "Network Error"
        }
        SearchCorporate().search(name: fullWidthString) { result, error in
            guard error == nil else { return networkError() }
            guard let responseText = result else { return networkError() }
            self.responseText = responseText
        }
    }
}

struct ContentViewPreviews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

