package com.example.smartspend.Screens.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SyncAlt
import androidx.compose.material.icons.rounded.DeleteSweep
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Update
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.util.formatDate
import com.example.smartspend.R
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme
import com.example.smartspend.data.NotesDataSource
import com.example.smartspend.data.TransectionItem
import com.example.smartspend.navigation.Routes

@Composable
fun DashBordActivity() {
    var translist = listOf<TransectionItem>(
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
    Box(modifier = Modifier
        .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 100.dp))
        .fillMaxWidth()
        .height(270.dp)
        .background(color = Color(0xff009177)))

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xff009177)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Rounded.Person, contentDescription = "")
                Text(
                    text = "Bank Balance",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif
                )
                Box (
                    modifier = Modifier
                        .clip(RoundedCornerShape(size = 20.dp))
                        .padding(10.dp)
                        .clickable { }
                ){
                    Icon(imageVector = Icons.Rounded.Logout, contentDescription = "")
                }
            }

            Text(
                text = "N$15 000",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        DashTopBar()
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .width(90.dp)
                    .clickable { },

            ){
                Row {
                    Icon(imageVector = Icons.Outlined.SyncAlt, contentDescription = "")
                    Text(text = "Transfer",
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 15.sp,fontFamily = FontFamily.SansSerif
                    )
                }
            }
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .width(90.dp)
                    .clickable { }
            ){
                Row {
                    Icon(imageVector = Icons.Outlined.AttachMoney, contentDescription = "")
                    Text(text = "Pay",
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 15.sp,fontFamily = FontFamily.SansSerif
                    )
                }
            }
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .width(90.dp)
                    .clickable { }
            ){
                Row {
                    Icon(imageVector = Icons.Outlined.Settings, contentDescription = "")
                    Text(text = "Manage",
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 15.sp,fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(25.dp))
        Row {
            Text(text = "latest Transactions", fontFamily = FontFamily.SansSerif)
        }

        LazyColumn {
            items(translist) { transItem ->
                TransectionRow(trans = transItem)
            }
        }
    }
}

// ...
// The rest of the code remains the same

@Composable
fun DashTopBar(){

    Column (
        modifier = Modifier

            .fillMaxWidth()

            ,

        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box (
            modifier = Modifier
                .clip(
                    RoundedCornerShape(size = 10.dp)
                )

                .padding(10.dp)
        ){
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(size = 10.dp)
                    )
                    .fillMaxWidth()
                    .background(
                        color = Color.Black
                    )
                    .padding(10.dp)
            ){
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            text = "S",
                            color =Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        Text(
                            text = "S",
                            color = Color.Green,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .scale(1f, -1f) // Inverted vertically
                                .rotate(degrees = 180f) // Rotated 180 degrees to correct orientation
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "XXXX XXXX XXXX XXXX 5446",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color =Color(255, 191, 0)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                    ){
                        Box (
                            modifier = Modifier
                                .width(150.dp)
                                .background(
                                    color = Color(11, 158, 55)
                                )
                                .padding(1.dp)

                        ){
                            Row {
                                Text(text = "BUDGET",
                                    fontWeight = FontWeight.W400,
                                    color = Color.White
                                )
                                Text(text = "N$ 380",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 15.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(2.dp))
                        Box (
                            modifier = Modifier
                                .width(180.dp)
                                .background(
                                    color = Color(122, 24, 5)
                                )
                                .padding(1.dp)

                        ){
                            Row {
                                Text(text = "SPEND",
                                    fontWeight = FontWeight.W400,
                                    color = Color.White
                                )
                                Text(text = "N$ 380",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 15.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(2.dp))
                        Box (
                            modifier = Modifier
                                .width(210.dp)
                                .background(
                                    color = Color(102, 98, 15)
                                )
                                .padding(1.dp)

                        ){
                            Row {
                                Text(text = "REMAINING",
                                    fontWeight = FontWeight.W400,
                                    color = Color.White
                                )
                                Text(text = "N$ 380",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 15.sp
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = "03/27",color =Color.White)
                        Image(
                            painter = painterResource(id = R.drawable.ic_visa),
                            contentDescription = "Card 18",
                            modifier = Modifier
                                .requiredWidth(60.dp)
                        )
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 0.dp)
                    ){
                        Text(text = "Giideon s v",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TransectionRow(
    modifier: Modifier = Modifier,
    trans: TransectionItem,){
    Surface(
        modifier
            .padding(10.dp)
            // to clip using a shape
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .width(350.dp),
        color = Color(18, 148, 139)) {

        Column(
            modifier
                .clickable { }
                .padding(
                    horizontal = 14.dp,
                    vertical = 10.dp
                ),
            horizontalAlignment = Alignment.Start) {

            Text(text = trans.Description,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                color = Color.White,
            )

            Text(text = trans.ammount.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Yellow,
                fontSize = 17.sp
            )

            // 1. comment it out
            // 2. later on as the last thing
            // 3. now it will not work
            // 4.

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = formatDate(trans.entryDate.time),
                    style = MaterialTheme.typography.titleSmall)
            }


            // and set it to this one
            /*Text(text = note.entryDate.time,
                style = MaterialTheme.typography.titleSmall)

             */



        }


    }

}

@Preview(showBackground = true)
@Composable
fun DashBordActivityPreview() {
    SmartSpendTheme {
        DashBordActivity()
    }
}