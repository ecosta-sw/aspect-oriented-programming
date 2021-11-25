package com.creditas.aspectorientedprogramming.core.crosscutting.localdate

import java.time.LocalDate

fun LocalDate.age(): Short {
    return LocalDate.now().year.minus(this.year).toShort()
}