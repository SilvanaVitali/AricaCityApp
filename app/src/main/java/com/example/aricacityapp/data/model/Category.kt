package com.example.aricacityapp.data.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

enum class Category {
    CULTURE, NATURE, BEACH, SPORT
}

data class CategoryContent(
    val icon: Int,
    val category: Category,
    @StringRes val name: Int,
    val color: Color
)