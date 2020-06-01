package main

import "fmt"

func main() {
	var number []int
	for i := 0; i < 11; i++ {
		number = append(number, i)
	}

	for _, value := range number {
		if value%2 == 0 {
			fmt.Println("Even: ", value)
		} else {
			fmt.Println("Odd: ", value)
		}
	}

}
