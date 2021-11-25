package com.creditas.aspectorientedprogramming.configuration

fun readJsonResource(fileName: String) = ClassLoader.getSystemResource("json/$fileName.json").readText()
