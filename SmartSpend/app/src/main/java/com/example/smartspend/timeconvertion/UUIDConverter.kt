package com.example.notesapp.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {

    @TypeConverter
    // pass in uuid and return a string
    fun fromUID(uuid: UUID): String? {
        // return a string
        return uuid.toString()
    }

    @TypeConverter
    // this one is the other way around
    // pass in string and return uuid
    fun uuidFromString(string: String): UUID? {
        return UUID.fromString(string)
    }

}