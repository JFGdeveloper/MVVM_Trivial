package com.javidev.trivial.data

// esto es nuestro wraper que usaremos en
// el repository

data class Data0rException<T,Boolean,E:Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)
