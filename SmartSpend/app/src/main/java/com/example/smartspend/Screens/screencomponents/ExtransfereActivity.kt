package com.example.smartspend.Screens.screencomponents

import android.widget.ScrollView
import android.widget.Toast
import androidx.compose.animation.expandHorizontally
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.rounded.Business
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtransfereScreen(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        ExtNavBar("External Transfer")
        Spacer(modifier = Modifier.height(20.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            TextField(value = "", onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent),
                    label = {Text(text = "Amount")},
                    placeholder = {Text(text = "1000")},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.AttachMoney, contentDescription = "")
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = "", onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent),
                label = {Text(text = "Bank Name")},
                placeholder = {Text(text = "FNB")},
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Business, contentDescription = "")
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = "", onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent),
                label = {Text(text = "Account Number")},
                placeholder = {Text(text = "484885938563958035793")},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.AccountTree, contentDescription = "")
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = "", onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent),
                label = {Text(text = "Branch Code")},
                placeholder = {Text(text = "53472")},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Code, contentDescription = "")
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))
            Demo_ExposedDropdownMenuBox()
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = { }) {
                Text(text = "Transfer")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte",
        "Mocha")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                coffeeDrinks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ExtNavBar(name:String){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff009177)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .padding(5.dp)
                    .clickable {}

            ){
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.White)
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
            }
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    //.background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .clickable {}

            ){
                Icon(imageVector = Icons.Outlined.HelpOutline, contentDescription = "",tint = Color.White)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ExtransfereScreenPreview() {
    val navControllerOne = rememberNavController()
    ExtransfereScreen(navControllerOne)

}