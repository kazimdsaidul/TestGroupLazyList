package com.example.testgrouplazylist.new

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun CategoriesList(viewModel: CategoriesViewModel = viewModel()) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(viewModel.categories) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = category.name,
            style = MaterialTheme.typography.headlineSmall
        )
        LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
            items(category.playlists) { playlist ->
                PlaylistItem(playlist = playlist)
            }
        }
    }
}

@Composable
fun PlaylistItem(playlist: Playlist) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        AsyncImage(
            model = playlist.imageUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = playlist.name)
        Text(text = playlist.description, style = MaterialTheme.typography.labelSmall)
    }
}

data class Playlist(
    val id: Int,
    val name: String, val description: String, val imageUrl: String
) {


}

data class Category(
    val name: String,
    val playlists: List<Playlist>,
    val id: Int
)
