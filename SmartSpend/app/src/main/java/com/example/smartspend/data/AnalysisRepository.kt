package com.example.smartspend.data

import androidx.compose.runtime.mutableStateOf

object AnalysisRepository {
    private var analysis = mutableSetOf<Categories>()
    private var overallPercentage:String = mutableSetOf("").toString()

    fun addCategory(analysis: Categories) {
        this.analysis.add(analysis)
    }

    fun getAllCategories(): MutableSet<Categories> {
        return analysis
    }

    fun getCategoryById(name: String): Categories? {
        return analysis.find { it.name == name }
    }

    fun updateCategory(name: String, newCategory: Categories) {

        this.analysis.forEach{
            if(it.name==name) {
                it.name = newCategory.name
                it.amount = newCategory.amount
            }
        }

    }

    fun getOverAllPercentage():String{
        return overallPercentage
    }

    fun setOverAllPercentage(percentage:String){
        overallPercentage = percentage
    }

}