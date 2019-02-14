package main

import "fmt"

type contactInfo struct {
	email   string
	zipCode int
}

type person struct {
	firstName string
	lastName  string
	contactInfo
}

func (p person) print() {
	fmt.Printf("%+v", p)
}

func (p person) updateName(newName string) {
	p.firstName = newName
}

func main() {
	jim := person{
		firstName: "Jim",
		lastName:  "Parker",
		contactInfo: contactInfo{
			email:   "jim@email",
			zipCode: 12343,
		},
	}

	jim.updateName("Jimmy")
	jim.print()

}
