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
import androidx.compose.runtime.derivedStateOf
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
import com.example.smartspend.transectionConfirm
import com.example.smartspend.updateCategory
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Instant
import java.util.Date


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

    // Derive the total budget amount from the text field values
    val totalBalance by remember {
        derivedStateOf {
            val transportValue = amount.toIntOrNull() ?: 0
            val baalance = userBalance.toIntOrNull() ?: 0

            baalance - transportValue
        }
    }
    var setbalance : String = userBalance
    setbalance = totalBalance.toString()


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

                Text(text = setbalance,
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
            TextField(value = amount, onValueChange = {amount = it},
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
            TextField(value = bankName, onValueChange = {bankName = it},
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
            TextField(value = accountNumber, onValueChange = {accountNumber = it},
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
            TextField(value = branchCode, onValueChange = {branchCode = it},
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
            TextField(value = reference, onValueChange = {reference = it},
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
            reason = Demo_ExposedDropdownMenuBox()
            Spacer(modifier = Modifier.height(20.dp))
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
            var isValid = true

            //var newBalance : String = ""

            if (amount.isBlank() || bankName.isBlank() || accountNumber.isBlank() || branchCode.isBlank() || reference.isBlank() || reason.isBlank()) {
                showToast(context, "All fields are required")
                isValid = false
            }

            if (isValid) {

                // Update the data in Firestore
                val extransferDocRef = db.collection("transections").document(userEmail).collection("transections").document()
                val userDocRef = db.collection("Users").document(userEmail)

                db.collection("Categories").document(userEmail)
                    .collection("budget")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            if (document.data["category"] == reason) {
                                val budgt = document.data["budget"] as? String
                                val spent = document.data["spent"] as? String

                                val remaining = budgt.toString().toInt() - spent.toString().toInt()

                                if (remaining < amount.toInt()){
                                    Toast.makeText(context, "Insufficient funds to make this transaction, please increase your category spending !!!", Toast.LENGTH_SHORT).show()
                                }
                                else{
                                    userDocRef.update(
                                        mapOf(
                                            "balance" to setbalance,
                                            "spent" to spent.toString().toInt() + amount.toInt()
                                        )
                                    )
                                }
                            }
                        }
                    }
                    .addOnFailureListener {

                    }

                db.collection("Categories").document(userEmail)
                    .collection("budget")
                    .get()


                if (!transectionConfirm( reason, amount, context)) {

                    extransferDocRef.set(
                        mapOf(
                            "amount" to amount,
                            "bankName" to bankName,
                            "accountNumber" to accountNumber,
                            "branchCode" to branchCode,
                            "reference" to reference,
                            "category" to reason,
                            "transType" to "expense",
                            "date" to Date.from(Instant.now())
                        )
                    )
                    val spending:Int = (updateCategory(reason, amount)).toInt()

                    // Update the balance in the user's document
                    userDocRef.update(
                        mapOf(
                            "balance" to setbalance,
                            "spent" to spending
                        )
                    )
                        .addOnSuccessListener {
                            showToast(context, "Account information updated successfully")
                            //startActivity(intent)
                        }
                        .addOnFailureListener { exception ->
                            showToast(
                                context,
                                "Error updating account information: ${exception.message}"
                            )
                        }
                }
                else{
                    Toast.makeText(context, "Insufficient funds to make this transaction, please increase your category spending !!!", Toast.LENGTH_SHORT).show()
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
fun Demo_ExposedDropdownMenuBox():String {
    val context = LocalContext.current
    val reason = arrayOf("transport", "accomodation", "food", "health",
        "education")
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

    return selectedText.toString()
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