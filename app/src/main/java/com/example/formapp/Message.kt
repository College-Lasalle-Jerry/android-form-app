package com.example.formapp

import java.io.Serializable

data class Message(
    val contactName: String,
    val contactNumber: String,
    val displayName: String,
    val includeJunior: Boolean,
    val jobTitle: String,
    val immediateStart: Boolean,
    val startDate: String
) : Serializable {
    fun getFullJobDescription() =
        if (includeJunior) "a Junior $jobTitle" else "an $jobTitle"

    fun getAvailablity() = if(immediateStart) "immediately" else "from $startDate"
}

// to format the code
// cmd + a ->  to select all the code.
// cmd + option + l -> to format the code.