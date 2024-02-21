package com.example.aricacityapp.data.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.aricacityapp.R

enum class Category(override val icon: Int, override val title: Int, override val color: Color) : EnumCategoryContent {
    CULTURE(
        icon = R.drawable.baseline_wb_shade_24,
        title = R.string.category_1,
        color = Color(0xFFfb5607)),
    NATURE(
        icon = R.drawable.baseline_local_florist_24,
        title = R.string.category_2,
        color = Color(0xFFff006e)
    ),
    BEACH(
        icon = R.drawable.baseline_beach_access_24,
        title = R.string.category_3,
        color = Color(0xFF8338ec)
    ),
    SPORT(
        icon = R.drawable.baseline_surfing_24,
        title = R.string.category_4,
        color = Color(0xFF3a86ff)
    )
}

interface EnumCategoryContent {
    val icon: Int
    val title: Int
    val color: Color
}