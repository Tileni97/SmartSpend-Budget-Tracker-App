package com.example.smartspend.data

object CategoryRepository {
    private val categories = mutableSetOf<Categories>()

    fun addCategory(category: Categories) {
        categories.add(category)
    }

    fun getAllCategories(): MutableSet<Categories> {
        return categories
    }

    fun getCategoryById(name: String): Categories? {
        return categories.find { it.name == name }
    }

    fun updateCategory(name: String, newCategory: Categories) {

        categories.forEach{
            if(it.name==name) {
                it.name = newCategory.name
                it.amount = newCategory.amount
            }
        }

    }
}