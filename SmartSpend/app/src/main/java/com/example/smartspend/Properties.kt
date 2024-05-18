package com.example.smartspend

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.smartspend.data.AnalysisRepository
import com.example.smartspend.data.Categories
import com.example.smartspend.data.CategoryRepository
import com.example.smartspend.data.Notifications
import com.example.smartspend.data.TransectionItem
import com.example.smartspend.data.TransectionRepository
import com.example.smartspend.data.dataBaseRepository

// This composable function renders a notification row
@Composable
fun notificationRow(
    modifier: Modifier = Modifier,
    trans: Notifications,
){
    // Check if the notification is read or unread
    if (trans.isRead){
        // Render the read notification UI
        // ... (code omitted for brevity)
    }
    else{
        // Render the unread notification UI
        // ... (code omitted for brevity)
    }
}

// This function fetches and sets the transaction data from the database
fun setTransection(userEmail: String){
    var newTrans = TransectionItem()
    val db = dataBaseRepository.getDb()
    // Query the database to get the user's transactions
    db.collection("transections")
        .document(userEmail)
        .collection("transections")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                if (document != null){
                    // Extract transaction details from the document
                    var category = document.data?.get("category") as? String
                    var amount = document.data?.get("amount") as? String
                    var type = document.data?.get("transType") as? String
                    var docId = document.id
                    var entryDate = document.data?.get("date") as? String

                    // Create a new TransectionItem object with the extracted data
                    newTrans = TransectionItem(category, amount, type, docId,entryDate)

                    // Add the transaction to the repository
                    TransectionRepository.addTransection(newTrans)
                }
            }
        }
        .addOnFailureListener { exception ->
            println("Error getting documents: $exception")
        }
}

// This function fetches and sets the category data from the database
fun setCategory(userEmail: String, context: Context){

    val db = dataBaseRepository.getDb()
    // Query the database to get the user's categories
    db.collection("Categories")
        .document(userEmail)
        .get()
        .addOnSuccessListener {
            if(it != null){

                // Extract category details from the document
                var accommodation = it.data?.get("accommodation") as? String
                var education = it.data?.get("education") as? String
                var transport = it.data?.get("transport") as? String
                var food = it.data?.get("food") as? String
                var health = it.data?.get("health") as? String

                // Add the categories to the repository
                CategoryRepository.addCategory(Categories("accommodation", Color(225,7,8),accommodation?:"0"))
                CategoryRepository.addCategory(Categories("education", Color(0,225,0),education?:"0"))
                CategoryRepository.addCategory(Categories("transport",Color(0,0,225), transport?:"0"))
                CategoryRepository.addCategory(Categories("food", Color(225,225,0),food?:"0"))
                CategoryRepository.addCategory(Categories("health",Color(225,0,225), health?:"0"))

               // Toast.makeText(context, "$accommodation $education $transport $food $health", Toast.LENGTH_SHORT).show()
            }
        }
        .addOnFailureListener {
            println("Error getting documents: ${it.message}")
        }
}

// This function calculates and sets the analysis data based on transactions and categories
fun setAnalysis(transList: MutableSet<TransectionItem>, categories: MutableSet<Categories>,context: Context): MutableSet<Categories> {

    var analysis = mutableSetOf<Categories>()

    // Initialize variables for tracking transaction totals
    var totalAccommodation = 0.0
    var totalTransport  = 0.0
    var totalEducation = 0.0
    var totalFood = 0.0
    var totalHealth = 0.0

    // Initialize variables for tracking category amounts
    var accommodation = 0.0
    var education = 0.0
    var transport = 0.0
    var food = 0.0
    var health = 0.0

    // Calculate the total spending for each category based on transactions
    for (trans in transList) {

        var amount: String = null.toString()

        if (trans.category == "accommodation" || trans.category == "Accommodation") {
            amount = trans.amount.toString()
            totalAccommodation += amount.toDouble()
        }
        else if (trans.category.equals("education") || trans.category.equals("Education")){
            amount = trans.amount.toString()
            totalEducation += amount.toDouble()
        }
        else if (trans.category.equals("transport") || trans.category.equals("Transport")) {
            amount = trans.amount.toString()
            totalTransport += amount.toDouble()
        }
        else if (trans.category.equals("food") || trans.category.equals("Food")) {
            amount = trans.amount.toString()
            totalFood += amount.toDouble()
        }
        else if (trans.category.equals("health") || trans.category.equals("Health")) {
            amount = trans.amount.toString()
            totalHealth += amount.toDouble()
        }
        else{
            // Handle other categories if needed
        }
    }

    // Get the category amounts from the categories data
    for (category in categories){

        var amount: String = null.toString()

        if (category.name.equals("accommodation") || category.name.equals("Accommodation")){
            amount = category.amount.toString()
            accommodation = amount.toDouble()
        }
        else if (category.name.equals("education") || category.name.equals("Education")){
            amount = category.amount.toString()
            education = amount.toDouble()
        }
        else if (category.name.equals("transport") || category.name.equals("Transport")) {
            amount = category.amount.toString()
            transport = amount.toDouble()
        }
        else if (category.name.equals("food") || category.name.equals("Food")) {
            amount = category.amount.toString()
            food = amount.toDouble()
        }
        else if (category.name.equals("health") || category.name.equals("Health")) {
            amount = category.amount.toString()
            health = amount.toDouble()
        }
    }

    // Calculate the total spending and total categories
    var totalSpending = totalAccommodation + totalTransport + totalEducation + totalFood + totalHealth
    var totalCategories = accommodation + education + transport + food + health

    // Calculate the percentage of spending for each category

    var accommodationPercentage = (totalAccommodation / accommodation * 100).toInt()
    var educationPercentage = (totalEducation / education * 100).toInt()
    var transportPercentage = (totalTransport / transport * 100).toInt()

    var foodPercentage = (totalFood / food * 100).toInt()
// Calculate the percentage of spending for the health category
    var healthPercentage = (totalHealth / health * 100).toInt()

// Calculate the overall percentage of spending
    var overallPercentage = (totalSpending / totalCategories * 100).toInt()

// Set the overall percentage in the repository
    AnalysisRepository.setOverAllPercentage(overallPercentage.toString())

// Add the category percentages to the repository
    analysis.add(Categories("accommodation",Color(225,7,8), accommodationPercentage.toString()))
    analysis.add(Categories("education",Color(0,225,0), educationPercentage.toString()))
    analysis.add(Categories("transport",Color(0,0,225), transportPercentage.toString()))
    analysis.add(Categories("food",Color(225,225,0), foodPercentage.toString()))
    analysis.add(Categories("health", Color(225,0,225),healthPercentage.toString()))

    //Toast.makeText(context, "$food", Toast.LENGTH_SHORT).show()

    return analysis
}

// This function is confirming if the transaction amount is greater than the category amount
fun transectionConfirm(userEmail: String, category: String, amount: Double): Boolean{
    var isError = false
    var cate = 0.0
    val db = dataBaseRepository.getDb()
    // Query the database to get the user's categories
    db.collection("Categories")
        .document(userEmail)
        .get()
        .addOnSuccessListener {
            if(it != null){
                // Extract category details from the document
                cate = (it.data?.get(category) as? String).toString().toDouble()
            }
        }
        .addOnFailureListener {
            println("Error getting documents: ${it.message}")
        }
    cate.toDouble()
    if (cate < amount){
        isError = true
    }
    else{
        isError = false
    }
    return isError
}
