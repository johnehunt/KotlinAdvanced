package com.midmarsh.util

class Calculator {

    fun add(x: Int, y: Int): Int {
        return x + y
    }

    fun div(x: Double, y: Double): Double {
        if (y == 0.0) {
            throw DivideByZeroException(x)
        }
        return x / y
    }
}