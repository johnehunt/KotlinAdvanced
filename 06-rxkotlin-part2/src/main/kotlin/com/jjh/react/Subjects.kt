package com.jjh.react

import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.subjects.ReplaySubject

fun main(args: Array<String>) {
    val list = listOf("John", "Denise", "Phoebe", "Gryff", "Adam", "Jasmine")
    println(list)
    val observable = list.toObservable()

    val subject = ReplaySubject.create<String>()
    observable.subscribe(subject)

    subject.subscribeBy{println("simpleprinter: $it")}

    subject.subscribeBy { s -> println("Received value: $s") }
}