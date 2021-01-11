package com.jjh.simple

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Create common shared channel
val channel = Channel<Int>()

suspend fun sendDataToChannelTask() {
    repeat(3) {
        delay(100)
        println("Sending $it")
        channel.send(it)
    }
}

suspend fun receiveDataFromChannelTask() {
    repeat(3) {
        println("Received: ${channel.receive()}")
    }
}

fun main() {
    println("Launching channel task")
    GlobalScope.launch{ sendDataToChannelTask() }
    GlobalScope.launch { receiveDataFromChannelTask() }
    println("After launching coroutines")
    println("Waiting for task - press enter to continue:")
    readLine()
    println("Done")
}