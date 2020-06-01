package main

import (
	"fmt"
	"net/http"
	"time"
)

func main() {
	links := [] string{
		"http://google.com",
		"http://facebook.com",
		"http://stackoverflow.com",
		"http://golang.org",
		"http://amazon.com",
	}

	// creates a channel
	c := make(chan string)

	for _, link := range links {
		go checkLink(link, c)
	}

	// fmt.Println(<-c) // blocking code
	// infinity loop
	for l := range c {
		go func(link string) {
			time.Sleep(time.Second * 5)
			checkLink(link, c) // blocking code
		}(l)
	}

}

func checkLink(link string, c chan string) {
	_, err := http.Get(link) // blocking code
	if err != nil {
		fmt.Println(link, "might be down!")
		c <- link
		return
	}
	fmt.Println(link, "is ok")
	c <- link
}
