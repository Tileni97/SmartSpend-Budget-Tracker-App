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

                    var newTrans = TransectionItem(category, amount, type, docId,entryDate)

                    TransectionRepository.addTransection(newTrans)
                }
            }
        }
        .addOnFailureListener { exception ->
            println("Error getting documents: $exception")
        }

}

fun setAnalysis(transList: MutableSet<TransectionItem>){

}
