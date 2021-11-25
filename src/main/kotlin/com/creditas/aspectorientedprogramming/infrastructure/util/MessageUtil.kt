package com.creditas.aspectorientedprogramming.infrastructure.util

import java.text.MessageFormat
import java.util.ResourceBundle

object MessageUtil {
    const val MESSAGE_0001 = "MESSAGE-0001"
    const val MESSAGE_0002 = "MESSAGE-0002"
    const val MESSAGE_0003 = "MESSAGE-0003"
    private const val MESSAGES = "messages/pt_br"

    fun createMessageWithResourceBundle(code: String, vararg params: Any): String {
        val resourceBundle = ResourceBundle.getBundle(MESSAGES)
        return run {
            val pattern = resourceBundle.getString(code)
            MessageFormat.format(pattern, *params)
        }
    }

    fun getMessage(code: String): String {
        val messageBundle = ResourceBundle.getBundle(MESSAGES)
        return messageBundle.getString(code)
    }
}
