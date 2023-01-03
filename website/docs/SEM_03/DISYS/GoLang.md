# The Go Programming Language

## Ch. 4.2 - Slices

A slice is a subsequence of an array. Consists of a pointer to the first element of the slice, a length and a capacity. The length is always less than or equal to the capacity, and the capacity is usually the number of elements from the pointer to the end of the underlying array.  
It is possible to extend a slice by slicing the slice (`extendedSlice := slice[:k]` where `k <= cap(slice)`) but the last index must be at most the capacity of the slice.  
It is not possible to use `==` to test for equality of slices.

|Terms|Explanation|
--|--
Slice operator `s[i:j]`|Creates a slice from the sequence s from element i (included) to element j (excluded).<br></br>i and j are by default 0 and len(s) if omitted.
Pointer|Points to the first element of the slice. Since the pointer points to an element of the underlying array (an alias), changing elements of the slice will change elements of the underlying array.
Length `(len)`|The number of elements of the slice
Capacity `(cap)`|At least as big as the length, and usually the number of elements from the pointer to the last element of the underlying array.

Goroutines correspond to threads in golang.  
Processes start goroutines, so one process can start multiple goroutines.  
Start a goroutine by adding “go “ before a function call.  
A race condition is when two goroutines share some data and the order of which each line of the goroutines run determines the actual outcome. Hard to reproduce errors that only occurs sometimes.  
This can be fixed with locks.
