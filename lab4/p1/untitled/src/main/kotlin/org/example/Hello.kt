package org.example

import khttp.*

fun main(args: Array<String>) {
    println("Hello, World")

    val payload = mapOf("key1" to "value1", "key2" to "value2")
    val r = get("http://httpbin.org/get", params=payload)

    println(r.url)

}

