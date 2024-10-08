package com.elyesasimsek.kotlinoopproject

class User : People{
    var name: String? = null
    var age: Int? = null


    constructor(name: String?, age: Int?) {
        this.name = name
        this.age = age
    }

    init {
        println("init")
    }
}