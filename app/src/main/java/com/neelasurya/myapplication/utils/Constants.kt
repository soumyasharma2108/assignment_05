package com.neelasurya.myapplication.utils

/** The base URL of the API */
const val BASE_URL: String = "https://xyz.me"
const val MAX_RESULTS = 10

enum class EnumStatusType constructor(val value: Int) {
    STATUS_QUEUE(0),
    STATUS_TRANSIT(1),
    STATUS_DELIVERED(2),
    STATUS_CANCELLED(3);
}
