package com.example.smartspend.navigation

sealed class Routes (val routes:String){

    data object Dashbord : Routes("dashbord")
    data object Setting : Routes("setting")
    data object Profile : Routes("profile")
    data object HomeScreen : Routes("homeScreen")
    data object TransferScreen : Routes("transfer")
    data object PayScreen : Routes("pay")
    data object AddBudget : Routes("addBudget")
    data object Analysis : Routes("analysis")
    data object Transactions : Routes("transactions")
    data object Extransfere : Routes("external")
    data object Intransfere : Routes("internal")
    data object AccountSetUp : Routes("accountSetUp")
    data object BudgetSetUp : Routes("budgetSetUp")

}