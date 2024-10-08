package com.elyesasimsek.kotlinoopproject

open class Family(private var name: String?, private var age: Int?) {
    fun getName(): String? {
        return name
    }

    fun setName(newName: String?) {
        // name özelliği val ile tanımlandığı için değiştirilemez.
        // Eğer name özelliğini değiştirmek istiyorsanız, var ile tanımlamanız gerekir.
        // Örneğin: private var name: String?
        // Bu durumda, setName metodu şu şekilde olabilir:
         name = newName
    }

    fun getAge(): Int? {
        return age
    }

    fun setAge(newAge: Int?) {
        // age özelliği valile tanımlandığı için değiştirilemez.
        // Eğer age özelliğini değiştirmek istiyorsanız, var ile tanımlamanız gerekir.
        // Örneğin: private var age: Int?
        // Bu durumda, setAge metodu şu şekilde olabilir:
         age = newAge
    }

}