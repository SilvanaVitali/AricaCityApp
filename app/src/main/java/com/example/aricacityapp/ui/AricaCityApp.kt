package com.example.aricacityapp.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aricacityapp.ui.utils.AricaCityContentScreen
import com.example.aricacityapp.ui.utils.AricaCityContentType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AricaCityApp(
    windowSize: WindowWidthSizeClass,
) {
    val viewModel: AricaCityViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val contentType: AricaCityContentType
    val columns: Int
    val imageSize: Float

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            contentType = AricaCityContentType.LIST_ONLY
            columns = 2
            imageSize = 1F
        }

        WindowWidthSizeClass.Medium -> {
            contentType = AricaCityContentType.LIST_ONLY
            columns = 4
            imageSize = 1/3F
        }

        WindowWidthSizeClass.Expanded -> {
            contentType = AricaCityContentType.LIST_AND_DETAIL
            columns = 4
            imageSize = 1/2F
        }

        else -> {
            contentType = AricaCityContentType.LIST_ONLY
            columns = 2
            imageSize = 1F
        }
    }

    Scaffold(
        topBar = {
            AricaCityHomeTopBar(
                onBackButtonClick = {
                    when (uiState.contentScreen) {
                        AricaCityContentScreen.LIST -> viewModel.navigateToCategoryPage()
                        AricaCityContentScreen.DETAIL -> viewModel.navigateToListPage()
                        else -> {}
                    }
                },
                uiState = uiState
            )
        }
    ) { innerPadding ->
        if (contentType == AricaCityContentType.LIST_AND_DETAIL) {
            if (uiState.contentScreen == AricaCityContentScreen.CATEGORY) {
                AricaCityHomeContent(
                    columns = columns,
                    contentPadding = innerPadding,
                    onClick = {
                        viewModel.updateCurrentCategory(it)
                        viewModel.navigateToListPage()
                    },
                )
            } else {
                AricaCityListAndDetails(
                    places = uiState.placesList,
                    category = uiState.currentPlace.category,
                    place = uiState.currentPlace,
                    imageSize = imageSize,
                    onClick = {
                              viewModel.updateCurrentPlace(it)
                    },
                    onBackPressed = { viewModel.navigateToCategoryPage() },
                    contentPadding = innerPadding)
            }
        } else {
            when (uiState.contentScreen) {
                AricaCityContentScreen.CATEGORY -> {
                    AricaCityHomeContent(
                        columns = columns,
                        contentPadding = innerPadding,
                        onClick = {
                            viewModel.updateCurrentCategory(it)
                            viewModel.navigateToListPage()
                        },
                    )
                }

                AricaCityContentScreen.LIST -> {
                    AricaCityListOnlyContent(
                        places = uiState.placesList,
                        category = uiState.currentPlace.category,
                        contentPadding = innerPadding,
                        onClick = {
                            viewModel.updateCurrentPlace(it)
                            viewModel.navigateToDetailPage()
                        },
                        onBackPressed = {
                            viewModel.navigateToCategoryPage()
                        }
                    )
                }

                AricaCityContentScreen.DETAIL -> {
                    AricaCityDetailContent(
                        selectedPlace = uiState.currentPlace,
                        imageSize = imageSize,
                        contentPadding = innerPadding,
                        onBackPressed = {
                            viewModel.navigateToListPage()
                        }
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AricaCityAppPreview() {
    Surface {
        AricaCityApp(windowSize = WindowWidthSizeClass.Expanded)
    }
}