package com.example.smartspend.Screens.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme

// ... (other imports)

class ProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartSpendTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "profile") {
                        composable("profile") { ProfileScreen(navController) }
                        composable("profile_details") { ProfileDetailsScreen( navController) }
                        composable("account_details") { AccountDetailsScreen(navController) }
                        composable("settings") { SettingScreen(navController) } // Add this line
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(navController: androidx.navigation.NavHostController ) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ProfileItem(
            icon = Icons.Filled.Edit,
            text = " Profile Details",
            onClick = { navController.navigate("profile_details") }
        )

        ProfileItem(
            icon = Icons.Filled.AccountBox,
            text = "Account Details",
            onClick = { navController.navigate("account_details") }
        )

        ProfileItem(
            icon = Icons.Filled.Settings,
            text = "Settings",
            onClick = { navController.navigate("settings") }
        )

        // Add Logout button here (similar to ProfileItem but with different icon/text)
    }
}

@Composable
fun ProfileItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SmartSpendTheme {
        ProfileScreen(navController = rememberNavController())
    }
}
