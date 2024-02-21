package com.example.aricacityapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.aricacityapp.data.model.CategoryContent
import com.example.aricacityapp.ui.theme.AricaCityAppTheme

class AricaCityHomeContent {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CategoryOnlyContent(modifier: Modifier = Modifier) {

        Scaffold(
            topBar = {
                AricaCityHomeTopBar(
                    onBackButtonClick = { /*TODO*/ },
                    isShowingListPage = true
                )
            }
        ) { innerPadding ->

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp)
            ) {
                items(allCategories) { category ->
                    CategoryCard(category)

                }
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AricaCityHomeTopBar(
        onBackButtonClick: () -> Unit,
        isShowingListPage: Boolean,
        modifier: Modifier = Modifier,
    ) {
        TopAppBar(
            title = { Text(text = stringResource(R.string.top_bar_categories)) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = modifier
            //TODO distinguir titulos
        )
    }

    @Composable
    private fun CategoryCard(category: CategoryContent, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(10.dp)
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
                    stringResource(category.name),
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
                CategoryOnlyContent()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CategoryCardPreview() {
        AricaCityAppTheme {
            Surface {
                CategoryCard(allCategories[0])
            }
        }
    }
}