package com.elyesasimsek.classesandfunctios

class Simpson {

    //property
    var name = ""
    var age = 0
    var job = ""

    //constructor
    constructor(){
        println("Constructor executed")
    }

    constructor(name: String, age: Int, job: String) {
        this.name = name
        this.age = age
        this.job = job

    }


}