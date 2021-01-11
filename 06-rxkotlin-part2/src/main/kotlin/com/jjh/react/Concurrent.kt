package com.jjh.react

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main(args: Array<String>) {
    Flowable.fromCallable {
        Thread.sleep(1000) //  imitate expensive computation
        "Done"
    }.subscribeOn(Schedulers.io())  // run in background
     .observeOn(Schedulers.single())  // display result in foreground
     .subscribe({ x: String? -> println(x) }) { obj: Throwable -> obj.printStackTrace() }

    Thread.sleep(2000) // <--- wait for the flow to finish

    // An alternative approach
    Flowable.range(1, 10)
            .parallel()
            .runOn(Schedulers.computation())
            .map { v: Int -> v * v }
            .sequential()
            .blockingSubscribe { x: Int? -> println(x) }

}