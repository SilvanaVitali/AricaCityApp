package com.example.aricacityapp.data.local

import com.example.aricacityapp.R
import com.example.aricacityapp.data.model.Category
import com.example.aricacityapp.data.model.Place

object LocalPlaceDataProvider {
    val allPlaces = listOf(
        Place(
            category = Category.CULTURE,
            name = R.string.name_1,
            description = R.string.description_1,
            image = R.drawable.image_1
        ),
        Place(
            category = Category.CULTURE,
            name = R.string.name_2,
            description = R.string.description_2,
            image = R.drawable.image_2
        ),
        Place(
            category = Category.CULTURE,
            name = R.string.name_3,
            description = R.string.description_3,
            image = R.drawable.image_3
        ),
        Place(
            category = Category.CULTURE,
            name = R.string.name_4,
            description = R.string.description_4,
            image = R.drawable.image_4
        ),
        Place(
            category = Category.NATURE,
            name = R.string.name_5,
            description = R.string.description_5,
            image = R.drawable.image_5
        ),
        Place(
            category = Category.NATURE,
            name = R.string.name_6,
            description = R.string.description_6,
            image = R.drawable.image_6
        ),
        Place(
            category = Category.NATURE,
            name = R.string.name_7,
            description = R.string.description_7,
            image = R.drawable.image_7
        ),
        Place(
            category = Category.BEACH,
            name = R.string.name_8,
            description = R.string.description_8,
            image = R.drawable.image_8
        ),
        Place(
            category = Category.BEACH,
            name = R.string.name_9,
            description = R.string.description_9,
            image = R.drawable.image_9
        ),
        Place(
            category = Category.BEACH,
            name = R.string.name_10,
            description = R.string.description_10,
            image = R.drawable.image_10
        ),
        Place(
            category = Category.BEACH,
            name = R.string.name_11,
            description = R.string.description_11,
            image = R.drawable.image_11
        ),
        Place(
            category = Category.SPORT,
            name = R.string.name_12,
            description = R.string.description_12,
            image = R.drawable.image_12
        ),
        Place(
            category = Category.SPORT,
            name = R.string.name_13,
            description = R.string.description_13,
            image = R.drawable.image_13
        ),
        Place(
            category = Category.SPORT,
            name = R.string.name_14,
            description = R.string.description_14,
            image = R.drawable.image_14
        ),
        Place(
            category = Category.SPORT,
            name = R.string.name_15,
            description = R.string.description_15,
            image = R.drawable.image_15
        )
    )
}