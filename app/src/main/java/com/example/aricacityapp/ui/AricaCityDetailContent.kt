package com.example.aricacityapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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

class AricaCityDetailContent {

    @Composable
    fun DetailContent(
        selectedPlace: Place,
        contentPadding: PaddingValues = PaddingValues(0.dp),
        modifier: Modifier = Modifier,
    ) {
        val scrollState = rememberScrollState()
        val layoutDirection = LocalLayoutDirection.current
        Box(
            modifier = modifier
                .verticalScroll(state = scrollState)
                .padding(top = contentPadding.calculateTopPadding())
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        bottom = contentPadding.calculateTopPadding(),
                        start = contentPadding.calculateStartPadding(layoutDirection),
                        end = contentPadding.calculateEndPadding(layoutDirection)
                    )
            ) {
                Box {
                    Box {
                        Image(
                            painter = painterResource(id = selectedPlace.image),
                            contentDescription = stringResource(id = selectedPlace.name),
                            alignment = Alignment.TopCenter,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(
                                    radiusX = 10.dp,
                                    radiusY = 10.dp,
                                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp)))
                                .border(BorderStroke(10.dp, selectedPlace.category.color))
                        )
                        Image(
                            painter = painterResource(id = selectedPlace.image),
                            contentDescription = stringResource(id = selectedPlace.name),
                            alignment = Alignment.TopCenter,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 70.dp, end = 70.dp, top = 35.dp)
                                .border(BorderStroke(4.dp, selectedPlace.category.color), MaterialTheme.shapes.small)
                                .clip(MaterialTheme.shapes.small)
                                .shadow(10.dp)
                        )
                    }
                    OutlinedCard(
                        border = BorderStroke(0.dp, Color.Transparent),
                        modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .height(70.dp)
                        .fillMaxWidth(),
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
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DetailContentPreview() {
        AricaCityAppTheme {
            Surface {
                DetailContent(LocalPlaceDataProvider.allPlaces[0])
            }
        }
    }
}