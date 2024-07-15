package com.example.testgrouplazylist.new.model

import androidx.compose.runtime.MutableState

data class Playlist(
    val id: Int,
    val name: String, val description: String, val imageUrl: String
)

data class Category(
    val name: String,
    val playlists: MutableState<List<Playlist>>,
    val id: Int
)
