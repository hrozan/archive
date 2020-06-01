package main

import (
	"os"
	"testing"
)

func TestNewDeck(t *testing.T) {
	d := newDeck()

	if len(d) != 52 {
		t.Errorf("Expect deck length of 52, but go %d", len(d))
	}

	if d[0] != "Ace of Spades" {
		t.Errorf("Expect frist caed of Ace of Spades, but got %s", d[0])
	}

	if d[len(d)-1] != "King of Clubs" {
		t.Errorf("Expect frist caed of King of Club, but got %s", d[len(d)-1])
	}
}

func TestSaveToDeckAndNewDeckFromFile(t *testing.T) {
	fileName := "_decktesting"
	// remove previus files from disk
	os.Remove(fileName)

	deck := newDeck()
	deck.saveToFile(fileName)

	loadDeck := newDeckFromFile(fileName)

	if len(loadDeck) != 52 {
		t.Errorf("Expect deck length of 52, but go %d", len(loadDeck))
	}

	os.Remove(fileName)
}
