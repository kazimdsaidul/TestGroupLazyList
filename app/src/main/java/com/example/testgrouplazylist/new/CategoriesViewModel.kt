package com.example.testgrouplazylist.new

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CategoriesViewModel : ViewModel() {

    val categories = mutableStateListOf<Category>().apply {
        addAll(List(50) { categoryIndex ->
            Category(
                id = categoryIndex + 1,
                name = "Category ${categoryIndex + 1}",
                playlists = List(10) { playlistIndex ->
                    Playlist(
                        id = playlistIndex + 1,
                        name = "Playlist ${playlistIndex + 1}",
                        description = "Description for Playlist ${playlistIndex + 1} in Category ${categoryIndex + 1}",
                        imageUrl = "https://images.toffeelive.com/images/program/300365/logo/web-logo/web-thumb-1717407841-84.png"
                    )
                }
            )
        })
    }


    init {
        callAPIData();
    }

    private fun callAPIData() {

    }
}