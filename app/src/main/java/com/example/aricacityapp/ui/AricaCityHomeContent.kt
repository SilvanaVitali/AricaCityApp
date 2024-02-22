package com.example.aricacityapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aricacityapp.R
import com.example.aricacityapp.data.local.LocalCategoryDataProvider.allCategories
import com.example.aricacityapp.data.model.Category
import com.example.aricacityapp.ui.theme.AricaCityAppTheme
import com.example.aricacityapp.ui.utils.AricaCityContentScreen


@Composable
fun AricaCityHomeContent(
    modifier: Modifier = Modifier,
    columns: Int,
    onClick: (Category) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = contentPadding,
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        items(allCategories) { category ->
            CategoryCard(
                category = category,
                onItemClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AricaCityHomeTopBar(
    onBackButtonClick: () -> Unit,
    uiState: AricaCityUiState,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text =
                if (uiState.contentScreen == AricaCityContentScreen.CATEGORY) {
                    stringResource(R.string.top_bar_categories)
                } else {
                    stringResource(uiState.currentPlace.category.title)
                }
            )
        },
        colors =
            if (uiState.contentScreen == AricaCityContentScreen.CATEGORY) {
                TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.primaryContainer)
            } else {
                TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = uiState.currentPlace.category.color,
                titleContentColor = Color.White)
            },
        navigationIcon = if (uiState.contentScreen != AricaCityContentScreen.CATEGORY) {
            {
                IconButton(onClick = { onBackButtonClick() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.primaryContainer,
                        contentDescription = null
                    )
                }
            }
        } else {
            { Box {} }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryCard(
    category: Category,
    onItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(10.dp),
        onClick = { onItemClick(category) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = category.color)
                .padding(16.dp)
                .aspectRatio(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Icon(
                painter = painterResource(category.icon),
                contentDescription = null,
                modifier = Modifier
                    .weight(3f)
                    .padding(16.dp)
                    .fillMaxWidth(),
                tint = Color.White
            )
            Text(
                stringResource(category.title),
                modifier = Modifier
                    .weight(1f),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryGridCardPreview() {
    AricaCityAppTheme {
        Surface {
            AricaCityHomeContent(columns = 2, onClick = {})
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun CategoryGridCardPreviewExpanded() {
    AricaCityAppTheme {
        Surface {
            AricaCityHomeContent(columns = 4, onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    AricaCityAppTheme {
        Surface {
            CategoryCard(allCategories[0], onItemClick = {})
        }
    }
}
