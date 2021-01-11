package com.jjh.react

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun printMe(s: String) {
    println(s)
}

class MyObserver: Observer<String> {
    override fun onComplete() {
        println("Complete")
    }

    override fun onSubscribe(d: Disposable?) {
        println("Subscribed")
    }

    override fun onNext(t: String?) {
        println("Next item $t")
    }

    override fun onError(e: Throwable?) {
        println("Opps - An Error $e")
    }
}

fun main(args: Array<String>) {
    val list = listOf("John", "Denise", "Phoebe", "Gryff", "Adam", "Jasmine")
    println(list)
    val observable = list.toObservable()
    //println("observable: $observable")

    observable.subscribeWith(MyObserver())

    observable.subscribeBy{println("simpleprinter: $it")}

    observable.subscribeBy(  // named arguments for lambda Subscribers
            onNext = { println(it) },
            onError = { it.printStackTrace() },
            onComplete = { println("Done!") }
    )

    observable.subscribeBy { s -> println("Received value: $s") }

}