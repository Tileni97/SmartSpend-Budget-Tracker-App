package com.example.smartspend.Screens.screencomponents

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.AttachFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.data.UserRepository
import com.google.firebase.firestore.FirebaseFirestore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtransfereScreen(navController: NavHostController) {

    val context = LocalContext.current

    // Mutable state variables for each fields
    var amount by remember { mutableStateOf("") }
    var bankName by remember { mutableStateOf("") }
    var accountNumber by remember { mutableStateOf("") }
    var branchCode by remember { mutableStateOf("") }
    var reference by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }
    var userBalance by remember { mutableStateOf("") }

    //Fetch user email from repository
    var userEmail: String = UserRepository.getEmail()

    // Initialize Firebase Firestore
    val db = FirebaseFirestore.getInstance()
    //Fetch balance
    val userDocRef = db.collection("Users").document(userEmail)
    val document = userDocRef.get().addOnSuccessListener { document ->
        var balance = document.data?.get("balance") as? String
        if (balance != null) {
            userBalance = balance.toString()
        }
    }


    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        ExtNavBar("External Transfer", navController)
        Column(
            modifier = Modifier
                .padding(40.dp, 0.dp)
                .clip(RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
                .fillMaxWidth()
                .background(color = Color(0xff009177)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp),
                horizontalArrangement = Arrangement.Center,
            ){

                Text(text = "Balance N$"
                    , fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.White)

                Spacer(modifier = Modifier.height(5.dp))

                Text(text = userBalance,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))
        Column (
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .clip(RoundedCornerShape(size = 10.dp))
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            TextField(value = "amount", onValueChange = {amount = it},
                    colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent),
                    label = {Text(text = "Amount",color = Color(0xff009177))},
                    placeholder = {Text(text = "0.00")},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Money, contentDescription = "")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = "bankName", onValueChange = {bankName = it},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent),
                label = {Text(text = "Bank Name",color = Color(0xff009177))},
                placeholder = {Text(text = "FNB")},
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.AccountBalance, contentDescription = "")
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = "accountNumber", onValueChange = {accountNumber = it},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent),
                label = {Text(text = "Account Number",color = Color(0xff009177))},
                placeholder = {Text(text = "484885938563958035793")},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.AccountTree, contentDescription = "")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = "branchCode", onValueChange = {branchCode = it},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent),
                label = {Text(text = "Branch Code",color = Color(0xff009177))},
                placeholder = {Text(text = "53472")},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Code, contentDescription = "")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = "reference", onValueChange = {reference = it},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent),
                label = {Text(text = "Reference",color = Color(0xff009177))},
                placeholder = {Text(text = "e.g Name,Number.")},
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.AttachFile, contentDescription = "")
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))
            Demo_ExposedDropdownMenuBox()
            Spacer(modifier = Modifier.height(20.dp))
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
            var isValid = true

            var newBalance : String = ""

            if (amount.isBlank()) {
                showToast(context, "Please enter Account Number")
                isValid = false
            }else{
                newBalance = (userBalance.toDouble() - amount.toDouble()).toString()
            }

            if (bankName.isBlank()) {
                showToast(context, "Please enter Card Number")
                isValid = false
            }

            if (accountNumber.isBlank()) {
                showToast(context, "Please enter Exp Month")
                isValid = false
            }

            if (branchCode.isBlank()) {
                showToast(context, "Please enter Exp Year")
                isValid = false
            }

            if (reference.isBlank()) {
                showToast(context, "Please enter CVV")
                isValid = false
            }
            if (reason.isBlank()) {
                showToast(context, "Please enter CVV")
                isValid = false
            }
            if (isValid) {
                // Update the data in Firestore
                val extransferDocRef = db.collection("Extransferes").document(userEmail)
                val userDocRef = db.collection("Users").document(userEmail)
                extransferDocRef.set(
                    mapOf(
                        "amount" to amount,
                        "bankName" to bankName,
                        "accountNumber" to accountNumber,
                        "branchCode" to branchCode,
                        "reference" to reference,
                        "reason" to reason
                    )
                )
                userDocRef.set(
                    mapOf(
                        "balance" to newBalance,
                    )
                )
                    .addOnSuccessListener {
                        showToast(context, "Account information updated successfully")
                        //startActivity(intent)
                    }
                    .addOnFailureListener { exception ->
                        showToast(context, "Error updating account information: ${exception.message}")
                    }
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(40.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff009177),
                contentColor = Color.White
            )
        ) {
            Text(text = "Transfer", fontWeight = FontWeight.W700)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val reason = arrayOf("Interest", "Dividend", "Annuity", "Pension",
        "Agents Commission","Ips Use","VAT","P.A.Y.E","Medical Aid","Salary","Insurance","Loan", "Other")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(reason[0]) }

    //Fetch User Email
    var userEmail: String = UserRepository.getEmail()

    // Initialize Firebase Firestore
    val db = FirebaseFirestore.getInstance()

    Box(
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            Text(
                text = "Reason",
                color = Color(0xff009177)
            )
            TextField(
                value = selectedText,
                onValueChange = {selectedText = it},
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
                reason.forEach { item ->
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
fun ExtNavBar(name:String, navController: NavHostController){
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
                    .clickable { navController.popBackStack() }

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

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}