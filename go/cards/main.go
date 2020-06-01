package main

func main() {
	cards := newDeckFromFile("my_cards.csv")
	cards.shuffle()
	cards.print()
}
