package com.blueberryprojects.cryptowatch.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun ImageSvg(modifier: Modifier = Modifier, url: String?) {
    url?.let {
        Box(modifier = modifier) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null
            )
        }
    }
}