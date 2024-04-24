package com.example.notesapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// so here we are going to create a function
// so this function is going to get the time in the form of a long
// because it's a timestamp. It comes as a long type and then
// we want to get a string.
fun formatDate(time: Long): String {
    // it's a java date
    // so this will convert the time stamp into an actual date
    val date = Date(time)

    // then use the format to invoke the SimpleDateFormat
    // pattern is basically telling the format on how it should be structured
    // there are a lot of different ways to show the date
    // and the pass the locale default of the device
    val format = SimpleDateFormat("EEE, d MMM hh:mm aaa",
        Locale.getDefault())

    // and then return the format and then pass our date.
    return format.format(date)

    // and now go back to the "NoteScreen" file inside the screen package and
    // modify the below to the current
    // Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d, MMM")),
}

