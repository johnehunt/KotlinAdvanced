package com.jjh.simple

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val bufferedChannel = Channel<Int>(4)

suspend fun sendDataTask() {
    repeat(7) {
        delay(50)
        println("Sending $it")
        bufferedChannel.send(it)
    }
}

fun main() {
    println("Launching channel task")
    GlobalScope.launch { sendDataTask() }
    GlobalScope.launch {
        delay(750)
        while(true) {
            delay(100)
            println("Received: ${bufferedChannel.receive()}")
        }
    }
    println("After launching coroutines")
    println("Waiting for task - press enter to continue:")
    readLine()
    println("Done")
}