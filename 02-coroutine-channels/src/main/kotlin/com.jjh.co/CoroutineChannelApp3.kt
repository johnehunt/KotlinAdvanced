package com.jjh.simple

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

suspend fun myOtherConsumer() {
    repeat(3) { // receive three
        println("other: ${msgChannel.receive()}")
    }
}

fun main() {
    println("One sender and multiple receivers")
    GlobalScope.launch {
        GlobalScope.launch { sendString(msgChannel, "Hello", 200L) }
        GlobalScope.launch{ myOtherConsumer()}
        repeat(3) { // receive three
            println("Main: ${msgChannel.receive()}")
        }
    }
    println("After launching coroutines")
    println("Waiting for task - press enter to continue:")
    readLine()
    println("Done")
}