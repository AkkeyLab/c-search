import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()

	var body: some View {
		Text(greet).onAppear() {
		    load()
		}
	}

	private func load() {
	    SearchCorporate().search { result, error in
	        print(result, error)
	    }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}