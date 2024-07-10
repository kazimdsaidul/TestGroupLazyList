package com.example.testgrouplazylist.new

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    val categories = mutableStateListOf<Category>()


    init {
        callAPIData();
    }

    private fun callAPIData() {
        viewModelScope.launch {
            delay(1000) // Simulate delay for category API call

            // Update categories with fetched data
            val updatedCategories = List(20) { categoryIndex ->
                Category(
                    id = categoryIndex + 1,
                    name = "Category ${categoryIndex + 1}",
                    playlists = mutableStateOf(emptyList()) // Initially empty, to simulate loading state
                )
            }

            categories.clear()
            categories.addAll(updatedCategories)

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
                    categories.find { it.id == category.id }?.playlists?.value = playlists

                }
            }
        }
    }
}