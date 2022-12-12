import SwiftUI
import shared

struct ContentView: View {
    @State private var corporate = ""

    var body: some View {
        Text(corporate)
            .onAppear() {
                load()
            }
    }

    private func load() {
        SearchCorporate().search(name: "ＡｋｋｅｙＬａｂ") { result, _ in
            guard let corporate = result else { return }
            self.corporate = corporate
        }
    }
}

struct ContentViewPreviews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

