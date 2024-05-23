package com.example.smartspend

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.data.AnalysisRepository
import com.example.smartspend.data.Categories
import com.example.smartspend.data.CategoryRepository
import com.example.smartspend.data.NotificationData
import com.example.smartspend.data.NotificationRepository
import com.example.smartspend.data.TransectionItem
import com.example.smartspend.data.TransectionRepository
import com.example.smartspend.data.dataBaseRepository
import kotlin.random.Random


fun setNotification(userEmail: String){
    var newNotifications = NotificationData()
    val db = dataBaseRepository.getDb()

    db.collection("Notifications").document(userEmail).collection("notifications")
        .get()
        .addOnSuccessListener { result ->
            if (result != null) {
                for (document in result) {
                    newNotifications = NotificationData(document.data?.get("description") as? String, document.data?.get("transType") as? String)

                    NotificationRepository.addNotification(newNotifications)
                }
            }
            else{

            }
        }
}

fun getNotification(notifications: MutableSet<NotificationData>): MutableSet<NotificationData> {
    val notificationn = mutableSetOf<NotificationData>()
    for (notification in notifications){
        val notfcation = NotificationData(notification.description, notification.type)
        notificationn.add(notfcation)
    }
    return notificationn
}
// This composable function renders a notification row
@Composable
fun notificationRow(
    modifier: Modifier = Modifier,
    trans: NotificationData,){

    if (trans.type == "income"){
        Surface(
            modifier
                .padding(30.dp, 10.dp, 60.dp, 3.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .clickable { }
            ,
            //color = Color(18, 148, 139)
        ) {

            Column(
                modifier
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface),
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp),
                    ){
                        Text(text = "INCOME",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xff009177),
                            fontWeight = FontWeight.W700,
                            fontSize = 17.sp
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        trans.description?.let {
                            Text(text = it,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                    }
                }
            }
        }
        Row (
            modifier = Modifier
                .padding(7.dp, 0.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically

        ){
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xff009177))
            ){
                Row(
                    modifier = Modifier
                        .padding(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "S",
                        color =Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Text(
                        text = "S",
                        color = Color.Green,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .scale(1f, -1f) // Inverted vertically
                            .rotate(degrees = 180f) // Rotated 180 degrees to correct orientation
                    )
                }
                //Icon(imageVector = Icons.Outlined.Drafts, contentDescription ="", tint = Color(0xff009177) )
            }

        }
    }
    else{
        Surface(
            modifier
                .padding(60.dp, 3.dp, 30.dp, 0.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
            ,
            //color = Color(18, 148, 139)
        ) {

            Column(
                modifier
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface),
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp),
                    ){
                        Text(text = "Expense",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(10, 130, 30),
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        Text(text = "${trans.description}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
        Row (
            modifier = Modifier
                .padding(5.dp, 0.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Right,
            verticalAlignment = Alignment.CenterVertically

        ){
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
            ){
                Row(
                    modifier = Modifier
                        .padding(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Outlined.MailOutline, contentDescription ="", tint = Color.Red)
                }

            }

        }
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
                    var entryDate = document.data?.get("date")as? String

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
    var category = Categories()
    // Query the database to get the user's categories
    db.collection("Categories")
        .document(userEmail)
        .collection("budget")
        .get()
        .addOnSuccessListener {
            if(it != null){
                for (i in it){
                    // Extract category details from the document
                    category = Categories(name = i.data?.get("cateName") as? String, color = Color(
                        Random(0).nextInt(256), Random(5).nextInt(256), Random(80).nextInt(256)
                    ), amount = i.data?.get("budget") as? String, spend = i.data.get("spent") as? String)

                    // Add the category to the repository
                    CategoryRepository.addCategory(category)

                }
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

        if (trans.category == "accomodation" || trans.category == "Accomodation") {
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

        if (category.name.equals("accomodation") || category.name.equals("Accomodation")){
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
    analysis.add(Categories("accomodation",Color(225,7,8), accommodationPercentage.toString()))
    analysis.add(Categories("education",Color(0,225,0), educationPercentage.toString()))
    analysis.add(Categories("transport",Color(0,0,225), transportPercentage.toString()))
    analysis.add(Categories("food",Color(225,225,0), foodPercentage.toString()))
    analysis.add(Categories("health", Color(225,0,225),healthPercentage.toString()))

    //Toast.makeText(context, "$food", Toast.LENGTH_SHORT).show()

    return analysis
}
fun getCategory( categories: MutableSet<Categories>): MutableSet<Categories> {

    var analysis = mutableSetOf<Categories>()

    // Initialize variables for tracking transaction totals
    var AccommodationSpending = 0.0
    var EducationSpending  = 0.0
    var TransportSpending = 0.0
    var FoodSpending = 0.0
    var HealthSpending = 0.0


    // Initialize variables for tracking category amounts
    var accommodation = 0.0
    var education = 0.0
    var transport = 0.0
    var food = 0.0
    var health = 0.0


    // Get the category amounts from the categories data
    for (category in categories){

        var amount: String = null.toString()

        if (category.name.equals("accomodation") || category.name.equals("Accomodation")){
            amount = category.amount.toString()
            accommodation = amount.toDouble()
            AccommodationSpending = category.spend.toString().toDouble()
        }
        else if (category.name.equals("education") || category.name.equals("Education")){
            amount = category.amount.toString()
            education = amount.toDouble()
            EducationSpending = category.spend.toString().toDouble()
        }
        else if (category.name.equals("transport") || category.name.equals("Transport")) {
            amount = category.amount.toString()
            transport = amount.toDouble()
            TransportSpending = category.spend.toString().toDouble()
        }
        else if (category.name.equals("food") || category.name.equals("Food")) {
            amount = category.amount.toString()
            food = amount.toDouble()
            FoodSpending = category.spend.toString().toDouble()
        }
        else if (category.name.equals("health") || category.name.equals("Health")) {
            amount = category.amount.toString()
            health = amount.toDouble()
            HealthSpending = category.spend.toString().toDouble()
        }
    }

// Add the category percentages to the repository
    analysis.add(Categories("accomodation",Color(225,7,8), accommodation.toString(), AccommodationSpending.toString()))
    analysis.add(Categories("education",Color(0,225,0), education.toString(), EducationSpending.toString()))
    analysis.add(Categories("transport",Color(0,0,225), transport.toString(), TransportSpending.toString()))
    analysis.add(Categories("food",Color(225,225,0), food.toString(), FoodSpending.toString()))
    analysis.add(Categories("health", Color(225,0,225),health.toString(), HealthSpending.toString()))

    //Toast.makeText(context, "$food", Toast.LENGTH_SHORT).show()

    return analysis
}