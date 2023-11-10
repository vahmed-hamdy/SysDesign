package main

import "fmt"

import "rsc.io/quote"

func main() {
	message := quote.Go()
	fmt.Println(message)
}
