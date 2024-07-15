package com.example.testgrouplazylist.new

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testgrouplazylist.new.model.Category
import com.example.testgrouplazylist.new.model.Playlist
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val _state = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Loading)
    val state: StateFlow<CategoriesUiState> = _state

    val categories = mutableStateListOf<Category>()


    init {
        callAPIData();
    }

    private fun callAPIData() {
        viewModelScope.launch {
            delay(2000) // Simulate delay for category API call
            // Simulate an error case
//             _state.value = CategoriesUiState.Error("Failed to load categories", null);

            // Update categories with fetched data
            val updatedCategories = List(20) { categoryIndex ->
                Category(
                    id = categoryIndex + 1,
                    name = "Category ${categoryIndex + 1}",
                    playlists = mutableStateOf(emptyList()) // Use MutableState for playlists
                )
            }

            _state.value = CategoriesUiState.Ready(updatedCategories)

            // Simulate playlist API calls for each category
            updatedCategories.forEach { category ->
                launch {
                    delay(2000) // Simulate delay for playlist API call
                    val playlists = List(10) { playlistIndex ->
                        Playlist(
                            id = playlistIndex + 1,
                            name = "Playlist ${playlistIndex + 1}",
                            description = "Description for Playlist ${playlistIndex + 1} in Category ${category.id}",
                            imageUrl = "https://images.toffeelive.com/images/program/300365/logo/web-logo/web-thumb-1717407841-84.png"
                        )
                    }
                    // Update the category with the fetched playlists
                    updatedCategories.find { it.id == category.id }?.playlists?.value = playlists
                    _state.value = CategoriesUiState.Ready(updatedCategories)
                }
            }
        }
    }

    fun retry() {
        _state.value = CategoriesUiState.Loading
        callAPIData()
    }
}