package main

import (
	"fmt"
)

func main() {
	// Mode 1
	//var colors map[string]string
	// Mode 2
	//colors := make(map[string] string)
	// colors["white"] = "#ffffff"
	// Mode 3
	colors := map[string]string{
		"red":   "ff0000",
		"green": "4bf745",
		"blue":  "0000ff",
	}

	printMap(colors)
}

func printMap(c map[string]string) {
	for key, value := range c {
		fmt.Printf("%s = %s \n", key, value)
	}
}
