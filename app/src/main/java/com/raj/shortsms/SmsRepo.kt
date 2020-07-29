package com.raj.shortsms

import android.content.Context
import android.provider.Telephony
import java.util.*
import kotlin.collections.ArrayList

fun get(applicationContext: Context?): ArrayList<Conversation>{
    val cursor = applicationContext?.contentResolver?.query(Telephony.Sms.CONTENT_URI, null, null, null, null)

    val numbers = ArrayList<String>()
    val messages = ArrayList<Message>()
    var results = ArrayList<Conversation>()

    while (cursor != null && cursor.moveToNext()) {
        val smsDate = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE))
        val number = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
        val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))

        numbers.add(number)
        messages.add(Message(number, body, Date(smsDate.toLong())))
    }

    cursor?.close()



    numbers.forEach { number ->
        if (results.find { it.number == number } == null) {
            val msg = messages.filter { it.number == number }
            results.add(Conversation(number = number, message = msg))
        }
    }

    return  results

}