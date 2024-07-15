package com.example.testgrouplazylist.new

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.testgrouplazylist.new.model.Category
import com.example.testgrouplazylist.new.model.Playlist

@Composable
fun CategoriesList(viewModel: CategoriesViewModel = viewModel()) {

    val homeScreenUiState by viewModel.state.collectAsState()

    when (val uiState = homeScreenUiState) {
        is CategoriesUiState.Loading -> HomeScreenLoading()
        is CategoriesUiState.Error -> HomeScreenError(uiState.message) {
            viewModel.retry()
        }

        is CategoriesUiState.Ready -> {
            CategoriesList(categories = uiState.categories)
        }
    }

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(viewModel.categories, key = { it.id }) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun HomeScreenLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Loading...")
    }
}

@Composable
fun HomeScreenError(message: String, onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message)
            Button(onClick = { onRetry() }) {
                Text(text = "Retry")
            }
        }
    }
}


@Composable
fun CategoriesList(categories: List<Category>) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(categories) { category ->
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
            val playlists by category.playlists
            if (playlists.isEmpty()) {
                item {
                    Text(text = "Loading...", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                items(playlists, key = { it.id }) { playlist ->
                    PlaylistItem(playlist = playlist)
                }
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


