package com.example.aricacityapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val category: Category,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)
