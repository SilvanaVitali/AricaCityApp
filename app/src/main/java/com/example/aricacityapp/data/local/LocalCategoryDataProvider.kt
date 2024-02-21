package com.example.aricacityapp.data.local

import androidx.compose.ui.graphics.Color
import com.example.aricacityapp.R
import com.example.aricacityapp.data.model.Category
import com.example.aricacityapp.data.model.CategoryContent

object LocalCategoryDataProvider {
    val allCategories = listOf(
        CategoryContent(
            icon = R.drawable.baseline_wb_shade_24,
            category = Category.CULTURE,
            name = R.string.category_1,
            color = Color(0xFFfb5607)
        ),
        CategoryContent(
            icon = R.drawable.baseline_local_florist_24,
            category = Category.NATURE,
            name = R.string.category_2,
            color = Color(0xFFff006e)
        ),
        CategoryContent(
            icon = R.drawable.baseline_beach_access_24,
            category = Category.BEACH,
            name = R.string.category_3,
            color = Color(0xFF8338ec)
        ),
        CategoryContent(
            icon = R.drawable.baseline_surfing_24,
            category = Category.SPORT,
            name = R.string.category_4,
            color = Color(0xFF3a86ff)
        )
    )
}