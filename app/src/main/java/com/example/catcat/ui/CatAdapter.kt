package com.example.catcat.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.AsyncImagePainter
import com.example.catcat.model.CatImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatScreen(viewModel: CatViewModel) {
    val catList = viewModel.cats.collectAsState().value
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    LazyColumn(
        state = listState,
        flingBehavior = flingBehavior,
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(catList) { index, cat ->
            CatItem(cat)

            if (index == catList.lastIndex - 3) {
                LaunchedEffect(key1 = index) {
                    viewModel.loadMoreCats()
                }
            }
        }
    }
}


@Composable
fun CatItem(cat: CatImage) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val painter = rememberAsyncImagePainter(model = cat.url)
    val state = painter.state

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )

        if (state is AsyncImagePainter.State.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "chargement ⌛",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
