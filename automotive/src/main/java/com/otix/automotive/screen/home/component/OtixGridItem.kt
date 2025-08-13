package com.otix.automotive.screen.home.component

import androidx.car.app.model.CarIcon
import androidx.car.app.model.GridItem
import androidx.core.graphics.drawable.IconCompat

fun OtixGridItem(title: String, text: String, iconCompat: IconCompat): GridItem =
    GridItem.Builder()
        .setImage(
            CarIcon
                .Builder(iconCompat)
                .build(),
            GridItem.IMAGE_TYPE_LARGE
        )
        .setTitle(title)
        .setText(text)
        .build()