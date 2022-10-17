package main

import (
	"fmt"
	"os"
	"strconv"
	"time"
)

func fork(in chan int, leftOut chan bool, rightOut chan bool, leftIdx int, rightIdx int) {
	used := false
	for true {
		asker := <-in // directed channels ensure that two philosophers never communicate
		if used {
			if asker == leftIdx {
				leftOut <- false
			} else if asker == rightIdx {
				rightOut <- false
			} else {
				used = false
			}
		} else {
			if asker == leftIdx {
				leftOut <- true
			} else if asker == rightIdx {
				rightOut <- true
			}
			used = true
		}
	}
}

var MUST_EAT_MINIMUM_TIMES int

func philosopher(leftForkIn chan int, leftForkOut chan bool,
	rightForkIn chan int, rightForkOut chan bool, idx int, doneChan chan int) {
	eatenTimes := 0
	state := "default"
	fmt.Printf("philosopher %d is thinking now\n", idx)
	//LOGIK LOOP
	for true {
		leftForkIn <- idx
		receivedLeftFork := <-leftForkOut
		if receivedLeftFork {
			// JEG HAR VENSTRE GAFFEL
			rightForkIn <- idx
			receivedRightFork := <-rightForkOut
			if receivedRightFork {
				state = "eating"
				eatenTimes++
				fmt.Printf("philosopher %d is eating now, this is the %d time\n", idx, eatenTimes)
				rightForkIn <- 0
			} else {
				if state != "thinking" {
					fmt.Printf("philosopher %d is thinking now\n", idx)
				}
				state = "thinking"
			}
			leftForkIn <- 0 // Philosophers will always put down their left fork if they can't pickup the right fork,
			// to ensure their neighbouring philosophers can have a chance of eating (to avoid a deadlock)
		} else {
			if state != "thinking" {
				fmt.Printf("philosopher %d is thinking now\n", idx)
			}
			state = "thinking"
		}
		if eatenTimes == MUST_EAT_MINIMUM_TIMES {
			doneChan <- idx
		}
	}

}

func main() {
	var n int // Amount of philosophers
	if len(os.Args) > 2 {
		MUST_EAT_MINIMUM_TIMES, _ = strconv.Atoi(os.Args[2])
		n, _ = strconv.Atoi(os.Args[1])
	} else {
		n = 5
		MUST_EAT_MINIMUM_TIMES = 3
	}

	var done = make([]bool, n)
	var doneChan = make(chan int)

	start := time.Now()

	prevIn := make(chan int)        // Each fork has 3 channels associated, one input and two outputs (one for each neighbouring philosopher).
	prevLeftOut := make(chan bool)  // This insures that a philosopher can only communicate with it's neighbouring forks, and never other philosophers
	prevRightOut := make(chan bool) // We also know that the fork will never be blocked from responding a philosopher.

	firstIn := prevIn
	firstLeftOut := prevLeftOut

	go fork(prevIn, prevLeftOut, prevRightOut, n, 1)

	for i := 1; i < n; i++ {
		newIn := make(chan int)
		newLeftOut := make(chan bool)
		newRightOut := make(chan bool)
		go fork(newIn, newLeftOut, newRightOut, i, i+1)
		go philosopher(prevIn, prevRightOut, newIn, newLeftOut, i, doneChan)
		prevIn = newIn
		prevLeftOut = newLeftOut
		prevRightOut = newRightOut
	}

	go philosopher(prevIn, prevRightOut, firstIn, firstLeftOut, n, doneChan)

	for true {
		idxDone := <-doneChan
		done[idxDone-1] = true
		trulyDone := true
		for _, element := range done {
			trulyDone = trulyDone && element
		}
		if trulyDone {
			fmt.Printf("tog %s\n", time.Since(start))
			break
		}
	}
}
