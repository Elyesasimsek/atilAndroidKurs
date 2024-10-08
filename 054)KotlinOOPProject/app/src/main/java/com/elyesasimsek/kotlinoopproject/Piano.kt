package com.elyesasimsek.kotlinoopproject

class Piano : HouseDecor, Instrumenr{
    var brand: String? = null
    var digital: Boolean? = null

    override var roomName: String
        get() = "Kitchen"
        set(value) {}
}