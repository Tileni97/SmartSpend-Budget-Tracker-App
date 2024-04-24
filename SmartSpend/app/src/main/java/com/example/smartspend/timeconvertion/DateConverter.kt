package com.example.notesapp.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    // So we are going to convert date into something that's not a date
    @TypeConverter
    // pass in date which is just a time stamp and return a long
    fun timeStampFromDate(date: Date): Long {
        return date.time

    }

    @TypeConverter
    // pass in timestamp of type Long and return date which we made nullable
    fun dateFromTimestamp(timestamp: Long): Date? {
        // then return the actual date and pass in timestamp
        return Date(timestamp)
    }
}