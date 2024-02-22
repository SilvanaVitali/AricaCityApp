package com.example.aricacityapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aricacityapp.R
import com.example.aricacityapp.data.local.LocalPlaceDataProvider
import com.example.aricacityapp.data.model.Category
import com.example.aricacityapp.data.model.Place
import com.example.aricacityapp.ui.theme.AricaCityAppTheme

@Composable
fun AricaCityListOnlyContent(
    places: List<Place>,
    category: Category,
    onClick: (Place) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    BackHandler {
        onBackPressed()
    }
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        val placesByCategory = places.filter { it.category == category }
        items(placesByCategory) { place ->
            if (place.category == category) {
                AricaListItem(place = place, onClick = onClick)
            }
        }
    }
}

@Composable
private fun AricaListItem(
    place: Place,
    onClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        border = BorderStroke(0.dp, Color.Transparent),
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_size))
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(place.image),
                contentDescription = stringResource(place.name),
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
            )
            Spacer(Modifier.size(16.dp))
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = stringResource(place.name),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { onClick(place) },
                    shape = CutCornerShape(
                        topEnd = 50.dp,
                        bottomEnd = 50.dp
                    ),
                    colors = ButtonDefaults.buttonColors(place.category.color),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.more_information),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
fun AricaCityListAndDetails(
    places: List<Place>,
    category: Category,
    place: Place,
    imageSize: Float,
    onClick: (Place) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Row(modifier = modifier.padding(16.dp
    )
    ) {
        AricaCityListOnlyContent(
            places = places,
            category = category,
            onClick =  onClick ,
            onBackPressed = { onBackPressed() },
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding()
            ),
            modifier = Modifier
                .weight(2f)
            )

        AricaCityDetailContent(
            selectedPlace = place,
            imageSize = imageSize,
            onBackPressed = {  },
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding()
            ),
            modifier = Modifier
                .weight(3f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    AricaCityAppTheme {
        Surface {
            AricaListItem(LocalPlaceDataProvider.allPlaces[0], onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListPreview() {
    AricaCityAppTheme {
        Surface {
            AricaCityListOnlyContent(
                places = LocalPlaceDataProvider.allPlaces,
                category = Category.SPORT,
                onClick = {},
                onBackPressed = {}
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun AricaCityExpandedPreview() {
    AricaCityAppTheme {
        Surface {
            AricaCityListAndDetails(
                places = LocalPlaceDataProvider.allPlaces,
                category = Category.SPORT,
                imageSize = 1/2F,
                place = LocalPlaceDataProvider.allPlaces[11],
                onClick = {},
                onBackPressed = {}
            )
        }
    }
}