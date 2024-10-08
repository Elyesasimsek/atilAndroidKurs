package com.elyesasimsek.kotlinoopproject

class Dog: Animal() {
    fun test() {
        super.sing()
    }

    override fun sing(){
        println("Dog Class")
    }
}