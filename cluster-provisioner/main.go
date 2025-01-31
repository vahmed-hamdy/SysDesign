package main

import "fmt"

import "rsc.io/quote"

func main() {
	var arr [3]int
	arr[0] = 1
	message := quote.Go()
	fmt.Println(message)
	fmt.Printf("Hello, World! %d\n", arr[0])
	for i := 0; i < len(arr); i++ {
		arr[i] = i * 2 // initializing items of array
	}
	for i := 0; i < len(arr); i++ {
		fmt.Printf("Item at index %d is %d\n", i, arr[i]) // printing items of array
	}

	var constArr = [...]int{1, 2, 3}

	for i := 0; i < len(constArr); i++ {
		arr[i] = i * 2 // initializing items of array
	}
	for i := 0; i < len(constArr); i++ {
		fmt.Printf("Item at index %d is %d\n", i, constArr[i]) // printing items of array
	}

	array := [3]float64{7.0, 8.5, 9.1}
	xf := Sum(&array) // Note the explicit address-of operator
	// to pass a pointer to the array
	fmt.Printf("The sum of the array is: %f", xf)
}

func Sum(a *[3]float64) (sum float64) {
	for _, v := range a { // dereferencing *a to get back to the array is not necessary!
		sum += v
	}
	return
}
