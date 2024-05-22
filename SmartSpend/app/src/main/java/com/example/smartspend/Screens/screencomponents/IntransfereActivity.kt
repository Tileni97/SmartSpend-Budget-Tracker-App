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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Money
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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.smartspend.navigation.Routes
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Instant
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntransfereScreen(navController: NavHostController) {


    val context = LocalContext.current

    val reasonElements = arrayOf("select reason", "transport", "accomodation", "food", "health",
        "education")

    // Mutable state variables for each fields
    var amount by remember { mutableStateOf("") }
    var accountNumber by remember { mutableStateOf("") }
    var reference by remember { mutableStateOf("") }
    var userBalance by remember { mutableStateOf("") }
    var recipientName by remember { mutableStateOf("") }
    var recipientEmail by remember { mutableStateOf("") }
    var recipientBalance by remember { mutableStateOf("") }
    var recipientFirstName by remember { mutableStateOf("") }
    var selectedText by remember { mutableStateOf(reasonElements[0]) }
    var newBalance by remember { mutableStateOf("") }
    var recipientNewBalance by remember { mutableStateOf("") }
    var remainingBudget by remember { mutableStateOf("") }
    var loggedInAccNo by remember { mutableStateOf("") }


    //Fetch user email from repository
    var userEmail: String = UserRepository.getEmail()

    // Initialize Firebase Firestore
    val db = FirebaseFirestore.getInstance()

    // Fetch balance
    val userDocRef = db.collection("Users").document(userEmail)
    userDocRef.get().addOnSuccessListener { document ->
        var balance = document.data?.get("balance") as? String
        var accountNo = document.data?.get("accountNumber") as? String
        userBalance = balance.toString() // Update userBalance with the fetched balance
        loggedInAccNo = accountNo.toString()

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



// Fetch user data based on the entered account number
    LaunchedEffect(accountNumber) {
        if (accountNumber.isNotEmpty()) {
            db.collection("Users")
                .whereEqualTo("accountNumber", accountNumber)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.isEmpty) {
                        recipientName = "Account number does not match any valid account number"
                    }
                    else if(!querySnapshot.isEmpty && accountNumber == loggedInAccNo){
                        recipientName = "You cannot transfer to yourself"
                    }
                    else {
                        val document = querySnapshot.documents.first()
                        val firstName = document.getString("firstName") ?: ""
                        val lastName = document.getString("lastName") ?: ""
                        val balance = document.getString("balance") ?: ""
                        val email = document.getString("username") ?: ""
                        recipientName = "$firstName $lastName".trim()
                        recipientBalance = balance
                        recipientFirstName = firstName
                        recipientEmail = email
                    }
                }
                .addOnFailureListener { exception ->
                    //showToast(context, "Error fetching user data: ${exception.message}")
                }
        } else {
            recipientName = ""
        }
    }


        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            InNavBar("Internal Transfer", navController)
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

                TextField(
                    value = accountNumber, onValueChange = { accountNumber = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    ),
                    label = { Text(text = "Account Number", color = Color(0xff009177)) },
                    placeholder = { Text(text = "Enter account number") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.AccountTree, contentDescription = "")
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
                //Demo_ExposedDropdownMenuBox()

                ///////////////////////////////

                var expanded by remember { mutableStateOf(false) }

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
                            reasonElements.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedText = item
                                        expanded = false
                                        //Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                    }
                                )
                            }
                        }
                    }
                }

                //////////////////////////////

                Spacer(modifier = Modifier.height(20.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = recipientName,
                color = if (recipientName.startsWith("Account number") || recipientName.startsWith("You cannot")) Color.Red else Color.Green,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = {
                             //////////////////////////////////////////////////

                var isValid = true

                if (amount.isBlank() || accountNumber.isBlank() || reference.isBlank() || selectedText.equals("select reason")) {
                    showToast(context, "All fields are required")
                    isValid = false
                }

                if (isValid) {

                    if (!recipientName.startsWith("Account number") || !recipientName.startsWith("You cannot")) {

                        newBalance = (userBalance.toInt() - amount.toInt()).toString()
                        recipientNewBalance =
                            (recipientBalance.toInt() + amount.toInt()).toString()

                        // Update the data in Firestore
                        val extransferDocRef = db.collection("transections").document(userEmail)
                            .collection("transections").document()
                        val userDocRef = db.collection("Users").document(userEmail)


                        //calculate remaining budget for the selected category
                        val categoryDocRef = db.collection("Categories")
                            .document(userEmail)
                            .collection("budget")
                            .document(selectedText.trim())

                        categoryDocRef.get()
                            .addOnSuccessListener { document ->
                                if (document.exists()) {
                                    val spent = document.getString("spent")
                                    val budget = document.getString("budget")
                                    if (spent != null && budget != null) {
                                        remainingBudget =
                                            (budget.toInt() - spent.toInt()).toString()
                                        //showToast(context, remainingBudget)
                                        if (remainingBudget.toInt() > amount.toInt()) {
                                            val newSpent = spent.toInt() + amount.toInt()
                                            db.collection("Categories").document(userEmail)
                                                .collection("budget").document(selectedText.trim())
                                                .update("spent", newSpent.toString())
                                                .addOnSuccessListener {
                                                    db.collection("transections")
                                                        .document(userEmail)
                                                        .collection("transections").document()
                                                        .set(
                                                            hashMapOf(
                                                                "amount" to amount,
                                                                "accountNumber" to accountNumber.toString(),
                                                                "reference" to reference.toString(),
                                                                "category" to selectedText.trim()
                                                                    .toString(),
                                                                "date" to "${Date.from(
                                                                    Instant.now()
                                                                )}",
                                                                "transType" to "expense"
                                                            )
                                                        )
                                                        .addOnSuccessListener {
                                                            db.collection("Users")
                                                                .document(userEmail)
                                                                .get()
                                                                .addOnSuccessListener { document ->
                                                                    val balance =
                                                                        document.data?.get("balance") as? String
                                                                    val spentBalance =
                                                                        document.data?.get("spent") as? String
                                                                    if (balance != null && spentBalance != null) {
                                                                        val newSpentBalance =
                                                                            spentBalance.toInt() + amount.toInt()

                                                                        db.collection("Users")
                                                                            .document(userEmail)
                                                                            .update(
                                                                                mapOf(
                                                                                    "balance" to newBalance,
                                                                                    "spent" to newSpentBalance.toString()
                                                                                )
                                                                            )
                                                                            .addOnSuccessListener {
                                                                                db.collection("transections")
                                                                                    .document(
                                                                                        recipientEmail
                                                                                    )
                                                                                    .collection("transections")
                                                                                    .document()
                                                                                    .set(
                                                                                        hashMapOf(
                                                                                            "amount" to amount,
                                                                                            "accountNumber" to accountNumber.toString(),
                                                                                            "reference" to reference.toString(),
                                                                                            "category" to selectedText.trim()
                                                                                                .toString(),
                                                                                            "date" to "${Date.from(
                                                                                                Instant.now()
                                                                                            )}",
                                                                                            "transType" to "income"
                                                                                        )
                                                                                    )
                                                                                    .addOnSuccessListener {
                                                                                        db.collection(
                                                                                            "Users"
                                                                                        ).document(
                                                                                            recipientEmail
                                                                                        )
                                                                                            .update(
                                                                                                mapOf(
                                                                                                    "balance" to recipientNewBalance.toString()
                                                                                                )
                                                                                            )
                                                                                            .addOnSuccessListener {
                                                                                                db.collection(
                                                                                                    "Notifications"
                                                                                                )
                                                                                                    .document(
                                                                                                        recipientEmail
                                                                                                    )
                                                                                                    .collection(
                                                                                                        "notifications"
                                                                                                    )
                                                                                                    .document()
                                                                                                    .set(
                                                                                                        hashMapOf(
                                                                                                            "amount" to amount,
                                                                                                            "description" to "Your account has been credited with N$$amount \nFrom: ${UserRepository.getEmail()} \nFor: $selectedText.\n" +
                                                                                                                    "Reference: $reference\n Date: ${
                                                                                                                        Date.from(
                                                                                                                            Instant.now()
                                                                                                                        )
                                                                                                                    }\n" +
                                                                                                                    "your initial balance: N$$recipientBalance , your available balance: N$$recipientNewBalance",
                                                                                                            "date" to "${Date.from(
                                                                                                                Instant.now()
                                                                                                            )}",
                                                                                                            "transType" to "income"
                                                                                                        )
                                                                                                    )
                                                                                                    .addOnSuccessListener {
                                                                                                        db.collection("Notifications").document(userEmail).collection("notifications").document()
                                                                                                            .set(
                                                                                                                hashMapOf(
                                                                                                                    "amount" to amount,
                                                                                                                    "description" to "You have successfully transferred N$$amount \n To: ${recipientName} \nFor: $selectedText.\n" +
                                                                                                                            "Reference: $reference\n Date: ${
                                                                                                                                Date.from(
                                                                                                                                    Instant.now()
                                                                                                                                )
                                                                                                                            }\n" +
                                                                                                                            "your initial balance: N$$userBalance , your new balance: N$$newBalance",
                                                                                                                    "date" to "${Date.from(
                                                                                                                        Instant.now()
                                                                                                                    )}",
                                                                                                                    "transType" to "expense"
                                                                                                                )
                                                                                                            )
                                                                                                            .addOnSuccessListener {
                                                                                                                showToast(
                                                                                                                    context,
                                                                                                                    "Transaction successful!"
                                                                                                                )
                                                                                                                navController.popBackStack()
                                                                                                            }
                                                                                                            .addOnFailureListener {
                                                                                                                /*showToast(
                                                                                                                    context,
                                                                                                                    "Error adding transaction"
                                                                                                                )*/
                                                                                                            }
                                                                                                    }
                                                                                                    .addOnFailureListener {
                                                                                                        /*Toast.makeText(
                                                                                                            context,
                                                                                                            "Error adding transaction",
                                                                                                            Toast.LENGTH_SHORT
                                                                                                        )
                                                                                                            .show()*/
                                                                                                    }
                                                                                            }
                                                                                            .addOnFailureListener {
                                                                                                /*Toast.makeText(
                                                                                                    context,
                                                                                                    "Error updating recipient amount balance",
                                                                                                    Toast.LENGTH_SHORT
                                                                                                )
                                                                                                    .show()*/
                                                                                            }
                                                                                    }
                                                                                    .addOnFailureListener {
                                                                                        /*Toast.makeText(
                                                                                            context,
                                                                                            "Error adding recipient transaction",
                                                                                            Toast.LENGTH_SHORT
                                                                                        ).show()*/
                                                                                    }
                                                                            }
                                                                            .addOnFailureListener {
                                                                                /*Toast.makeText(
                                                                                    context,
                                                                                    "Error updating spent amount",
                                                                                    Toast.LENGTH_SHORT
                                                                                ).show()*/
                                                                            }
                                                                    } else {
                                                                        /*Toast.makeText(
                                                                            context,
                                                                            "Error retrieving balance",
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()*/
                                                                    }
                                                                }
                                                                .addOnFailureListener {
                                                                    /*Toast.makeText(
                                                                        context,
                                                                        "Error adding sender transaction",
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()*/
                                                                }
                                                        }
                                                        .addOnFailureListener {
                                                           /*showToast(
                                                                context,
                                                                "Error adding document: ${it.message}"
                                                            )*/
                                                        }
                                                }
                                                .addOnFailureListener {
                                                    /*showToast(
                                                        context,
                                                        "Error updating document: ${it.message}"
                                                    )*/
                                                }
                                        } else {
                                            showToast(
                                                context,
                                                "Insufficient Budget Balance, remaining budget on $selectedText is $remainingBudget"
                                            )
                                        }
                                    } else {
                                        /*showToast(
                                            context,
                                            "Error: Spent or budget field is missing in the document."
                                        )*/
                                    }
                                } else {
                                    /*showToast(
                                        context,
                                        "Document not found for the selected category."
                                    )*/
                                }
                            }
                            .addOnFailureListener { exception ->
                               // showToast(context, "Error fetching document: ${exception.message}")
                            }


                    }
                    else{
                        showToast(context, "Please enter a valid account number")
                    }

                }
                else{
                    showToast(context, "All fields are required")
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff009177),
                    contentColor = Color.White
                ),
                enabled = recipientName.isNotEmpty() &&
                        !recipientName.startsWith("Account number") &&
                        !recipientName.startsWith("You cannot")// Disable button if account number is invalid
            ) {
                Text(text = "Transfer", fontWeight = FontWeight.W700)

            }
        }
    }


@Composable
fun InNavBar(name:String, navController: NavHostController){
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
                    .clickable {
                        navController.popBackStack()
                    }

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
                    .clickable {navController.navigate(Routes.HelpScreen.routes)}

            ){
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "",tint = Color.White)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun IntransfereScreenPreview() {
    val navControllerOne = rememberNavController()
    IntransfereScreen(navControllerOne)

}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}