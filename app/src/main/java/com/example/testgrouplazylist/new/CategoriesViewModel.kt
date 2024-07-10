package com.example.testgrouplazylist.new

import androidx.lifecycle.ViewModel

class CategoriesViewModel : ViewModel() {
    val categories = listOf(
        Category(
            id = 1,
            name = "Pop Music",
            playlists = listOf(
                Playlist(
                    1,
                    "Top Hits",
                    "Today's most popular songs",
                    "https://images.toffeelive.com/images/program/300365/logo/web-logo/web-thumb-1717407841-84.png"
                ),
                Playlist(
                    1,
                    "Top Hits",
                    "Today's most popular songs",
                    "https://images.toffeelive.com/images/program/300365/logo/web-logo/web-thumb-1717407841-84.png"
                ),
                Playlist(
                    1,
                    "Top Hits",
                    "Today's most popular songs",
                    "https://images.toffeelive.com/images/program/300365/logo/web-logo/web-thumb-1717407841-84.png"
                ),
            )
        ),
        Category(
            id = 1,
            name = "Rock Music",
            playlists = listOf(
                Playlist(
                    1,
                    "Rock Anthems",
                    "Epic rock anthems",
                    "https://images.toffeelive.com/images/program/300365/logo/web-logo/web-thumb-1717407841-84.png"
                ),
            )
        )
    )

    init {
        callAPIData();
    }

    private fun callAPIData() {

    }
}