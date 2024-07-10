package com.example.testgrouplazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.testgrouplazylist.new.CategoriesList
import com.example.testgrouplazylist.ui.theme.TestGroupLazyListTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestGroupLazyListTheme {
                CategoriesList()
            }
        }
    }
}

