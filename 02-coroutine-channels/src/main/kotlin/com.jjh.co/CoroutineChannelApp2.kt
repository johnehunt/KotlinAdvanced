package com.jjh.simple

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val msgChannel = Channel<String>()

suspend fun sendString(channel: Channel<String>, s: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(s)
    }
}

fun main() {
    println("Multiple senders and one receiver")
    GlobalScope.launch {
        GlobalScope.launch { sendString(msgChannel, "John", 200L) }
        GlobalScope.launch { sendString(msgChannel, "Denise", 500L) }
        repeat(6) { // receive first six
            println(msgChannel.receive())
        }
    }
    println("After launching coroutines")
    println("Waiting for task - press enter to continue:")
    readLine()
    println("Done")
}