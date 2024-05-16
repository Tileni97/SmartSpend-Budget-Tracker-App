package com.example.smartspend

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
import androidx.compose.material.icons.outlined.AccessTime
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
import com.example.notesapp.util.formatDate
import com.example.smartspend.data.AnalysisRepository
import com.example.smartspend.data.Categories
import com.example.smartspend.data.CategoryRepository
import com.example.smartspend.data.Notifications
import com.example.smartspend.data.TransectionItem
import com.example.smartspend.data.TransectionRepository
import com.example.smartspend.data.dataBaseRepository

@Composable
fun notificationRow(
    modifier: Modifier = Modifier,
    trans: Notifications,){

    if (trans.isRead){
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
                        Text(text = trans.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xff009177),
                            fontWeight = FontWeight.W700,
                            fontSize = 17.sp
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        Text(text = trans.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400
                        )
                    }
                }

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(imageVector = Icons.Outlined.AccessTime, contentDescription ="" )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = formatDate(trans.entryDate.time),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.W400,
                        fontSize = 13.sp)

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
                .clickable {
                    trans.isRead = true
                    //notificationRow()
                }
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
                        Text(text = trans.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(10, 130, 30),
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        Text(text = "unread message ...",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(imageVector = Icons.Outlined.AccessTime, contentDescription ="" )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = formatDate(trans.entryDate.time),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.W400,
                        fontSize = 13.sp)

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

fun setTransection(userEmail: String){
    var newTrans = TransectionItem()
    val db = dataBaseRepository.getDb()
    db.collection("transections")
        .document(userEmail)
        .collection("transections")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                if (document != null){
                    var category = document.data?.get("category") as? String
                    var amount = document.data?.get("amount") as? String
                    var type = document.data?.get("transType") as? String
                    var docId = document.id
                    var entryDate = document.data?.get("date") as? String

                    newTrans = TransectionItem(category, amount, type, docId,entryDate)

                    TransectionRepository.addTransection(newTrans)
                }
            }
        }
        .addOnFailureListener { exception ->
            println("Error getting documents: $exception")
        }

}

fun setCategory(userEmail: String){
    var newTrans = Categories()
    val db = dataBaseRepository.getDb()
    db.collection("categories")
        .document(userEmail)
        .get()
        .addOnSuccessListener {
            if(it != null){
                var accommodation = it.data?.get("accommodation") as? String
                var education = it.data?.get("education") as? String
                var transport = it.data?.get("transport") as? String
                var food = it.data?.get("food") as? String
                var health = it.data?.get("health") as? String

                CategoryRepository.addCategory(Categories("accommodation", accommodation?:"0"))
                CategoryRepository.addCategory(Categories("education", education?:"0"))
                CategoryRepository.addCategory(Categories("transport", transport?:"0"))
                CategoryRepository.addCategory(Categories("food", food?:"0"))
                CategoryRepository.addCategory(Categories("health", health?:"0"))
            }
        }
        .addOnFailureListener {
            println("Error getting documents: ${it.message}")
        }
}


fun setAnalysis(transList: MutableSet<TransectionItem>, categories: MutableSet<Categories>){

    var amount: String = null.toString()

    // Transactions variables
    var totalAccommodation = 0.0
    var totalTransport  = 0.0
    var totalEducation = 0.0
    var totalFood = 0.0
    var totalHealth = 0.0

    // Categories variables
    var accommodation = 0.0
    var education = 0.0
    var transport = 0.0
    var food = 0.0
    var health = 0.0

    for (trans in transList) {
        if (trans.category == "accommodation" || trans.category == "Accommodation") {
            amount = trans.ammount.toString()
            totalAccommodation += amount.toDouble()
        }
        else if (trans.category.equals("education") || trans.category.equals("Education")){
            amount = trans.ammount.toString()
            totalEducation += amount.toDouble()
        }
        else if (trans.category.equals("transport") || trans.category.equals("Transport")) {
            amount = trans.ammount.toString()
            totalTransport += amount.toDouble()
        }
        else if (trans.category.equals("food") || trans.category.equals("Food")) {
            amount = trans.ammount.toString()
            totalFood += amount.toDouble()
        }
        else if (trans.category.equals("health") || trans.category.equals("Health")) {
            amount = trans.ammount.toString()
            totalHealth += amount.toDouble()
        }
        else{
        }
    }

    for (category in categories){
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

    var totalSpending = totalAccommodation + totalTransport + totalEducation + totalFood + totalHealth
    var totalCategories = accommodation + education + transport + food + health

    var accommodationPercentage = (totalAccommodation / accommodation * 100).toInt()
    var educationPercentage = (totalEducation / education * 100).toInt()
    var transportPercentage = (totalTransport / transport * 100).toInt()
    var foodPercentage = (totalFood / food * 100).toInt()
    var healthPercentage = (totalHealth / health * 100).toInt()

    var overallPercentage = (totalSpending / totalCategories * 100).toInt()

    AnalysisRepository.setOverAllPercentage(overallPercentage.toString())

    AnalysisRepository.addCategory(Categories("accommodation", accommodationPercentage.toString()))
    AnalysisRepository.addCategory(Categories("education", educationPercentage.toString()))
    AnalysisRepository.addCategory(Categories("transport", transportPercentage.toString()))
    AnalysisRepository.addCategory(Categories("food", foodPercentage.toString()))
    AnalysisRepository.addCategory(Categories("health", healthPercentage.toString()))
}
