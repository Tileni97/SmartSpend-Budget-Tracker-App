package com.example.smartspend.data



class NotesDataSource {

    fun loadTrans(): List<TransectionItem> {
        return listOf(

            // dummy content to act as notes initially
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200),
            TransectionItem(Description = "transport", ammount = 200)
        )
    }
}