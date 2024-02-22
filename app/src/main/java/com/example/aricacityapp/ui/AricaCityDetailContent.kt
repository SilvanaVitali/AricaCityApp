package com.example.aricacityapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aricacityapp.data.local.LocalPlaceDataProvider
import com.example.aricacityapp.data.model.Place
import com.example.aricacityapp.ui.theme.AricaCityAppTheme

@Composable
fun AricaCityDetailContent(
    selectedPlace: Place,
    imageSize: Float,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        if(imageSize == 1/3F) {
            Row(
                modifier = Modifier
                    .padding(
                        bottom = contentPadding.calculateTopPadding(),
                        start = contentPadding.calculateStartPadding(layoutDirection),
                        end = contentPadding.calculateEndPadding(layoutDirection)
                    )
            ) {
                DetailContentScreen(selectedPlace = selectedPlace, imageSize = imageSize)
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(
                        bottom = contentPadding.calculateTopPadding(),
                        start = contentPadding.calculateStartPadding(layoutDirection),
                        end = contentPadding.calculateEndPadding(layoutDirection)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DetailContentScreen(selectedPlace = selectedPlace, imageSize = imageSize)
            }
        }
    }
}

@Composable
private fun ImagePlace(selectedPlace: Place, imageSize: Float) {
    Box {
        Image(
            painter = painterResource(id = selectedPlace.image),
            contentDescription = stringResource(id = selectedPlace.name),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize(imageSize)
                .border(BorderStroke(10.dp, selectedPlace.category.color)),
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
        )
        Image(
            painter = painterResource(id = selectedPlace.image),
            contentDescription = stringResource(id = selectedPlace.name),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize(imageSize)
                .padding(start = 70.dp, end = 70.dp, top = 35.dp)
                .border(
                    BorderStroke(4.dp, selectedPlace.category.color),
                    MaterialTheme.shapes.small
                )
                .clip(MaterialTheme.shapes.small)
                .shadow(10.dp)
        )
    }
}

@Composable
private fun DetailContentScreen(selectedPlace: Place, imageSize: Float) {
    Box {
        ImagePlace(selectedPlace, imageSize)
        OutlinedCard(
            border = BorderStroke(0.dp, Color.Transparent),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(70.dp)
                .fillMaxWidth(imageSize),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        ) {
            Text(
                text = stringResource(id = selectedPlace.name),
                style = MaterialTheme.typography.titleLarge,

                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
    Text(
        text = stringResource(id = selectedPlace.description),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .padding(20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    AricaCityAppTheme {
        Surface {
            AricaCityDetailContent(
                selectedPlace = LocalPlaceDataProvider.allPlaces[0],
                imageSize = 1F,
                onBackPressed = {}
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun DetailContentPreviewMedium() {
    AricaCityAppTheme {
        Surface {
            AricaCityDetailContent(
                selectedPlace = LocalPlaceDataProvider.allPlaces[0],
                imageSize = 1/3F,
                onBackPressed = {}
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun DetailContentPreviewExpanded() {
    AricaCityAppTheme {
        Surface {
            AricaCityDetailContent(
                selectedPlace = LocalPlaceDataProvider.allPlaces[0],
                imageSize = 1/2F,
                onBackPressed = {}
            )
        }
    }
}
