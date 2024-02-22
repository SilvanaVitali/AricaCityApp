package com.example.aricacityapp.ui

import com.example.aricacityapp.data.local.LocalPlaceDataProvider
import com.example.aricacityapp.data.model.Place
import com.example.aricacityapp.ui.utils.AricaCityContentScreen

data class AricaCityUiState(
    val placesList: List<Place> = emptyList(),
    val currentPlace: Place = LocalPlaceDataProvider.allPlaces[0],
    val contentScreen: AricaCityContentScreen = AricaCityContentScreen.CATEGORY
)
