package com.example.aricacityapp.ui

import androidx.lifecycle.ViewModel
import com.example.aricacityapp.data.local.LocalPlaceDataProvider
import com.example.aricacityapp.data.model.Category
import com.example.aricacityapp.data.model.Place
import com.example.aricacityapp.ui.utils.AricaCityContentScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AricaCityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AricaCityUiState())
    val uiState: StateFlow<AricaCityUiState> = _uiState

    init {
        initializateUiState()
    }

    private fun initializateUiState() {
        val placesList: List<Place> = LocalPlaceDataProvider.allPlaces
        _uiState.value = AricaCityUiState(placesList = placesList)
    }

    fun updateCurrentPlace(selectedPlace: Place) {
        _uiState.update {
            it.copy(currentPlace = selectedPlace)
        }
    }

    fun updateCurrentCategory(selectedCategory: Category) {
        val placesList: List<Place> = LocalPlaceDataProvider.allPlaces
        val currentPlace = placesList.find { it.category  == selectedCategory } ?: placesList[0]
        _uiState.update {
            it.copy(currentPlace = currentPlace)
        }
    }

    fun navigateToCategoryPage() {
        _uiState.update {
            it.copy(contentScreen = AricaCityContentScreen.CATEGORY)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(contentScreen = AricaCityContentScreen.LIST)
        }
    }


    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(contentScreen = AricaCityContentScreen.DETAIL)
        }
    }

}