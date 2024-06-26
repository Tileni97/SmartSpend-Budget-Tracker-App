package com.example.smartspend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.rounded.AddCard
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.Screens.screencomponents.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class BudgetSetUpScreen: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartSpendTheme {
                SetBarColor(color = Color(0xff009177))
                Surface {
                    Column (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BudgetSetUp()
                    }
                }
            }
        }
    }
    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetSetUp() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            text = "Hi There !!!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Let's start with setting up your account.",
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column (
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Column (
                modifier = Modifier
                    .padding(10.dp, 10.dp)
                    .clip(RoundedCornerShape(size = 10.dp))
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                TextField(value = "", onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Account Number")},
                    placeholder = {Text(text = "4848653456755435")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Business, contentDescription = "")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(value = "", onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Card Number")},
                    placeholder = {Text(text = "64274623874246824")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.AddCard, contentDescription = "")
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(value = "", onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Card Number")},
                    placeholder = {Text(text = "64274623874246824")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.AddCard, contentDescription = "")
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(value = "", onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Card Number")},
                    placeholder = {Text(text = "64274623874246824")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.AddCard, contentDescription = "")
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(value = "", onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Card Number")},
                    placeholder = {Text(text = "64274623874246824")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.AddCard, contentDescription = "")
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(value = "", onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Card Number")},
                    placeholder = {Text(text = "64274623874246824")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.AddCard, contentDescription = "")
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(value = "", onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Card Number")},
                    placeholder = {Text(text = "64274623874246824")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.AddCard, contentDescription = "")
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(20.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff009177),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Transfer", fontWeight = FontWeight.W700)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetSetUpPreview() {
    BudgetSetUp()
}