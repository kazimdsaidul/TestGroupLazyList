package com.example.testgrouplazylist.new

import com.example.testgrouplazylist.new.model.Category

sealed class CategoriesUiState {
    object Loading : CategoriesUiState()
    data class Error(val message: String, val onRetry: (() -> Unit)? = null) : CategoriesUiState()
    data class Ready(val categories: List<Category>) : CategoriesUiState()
}