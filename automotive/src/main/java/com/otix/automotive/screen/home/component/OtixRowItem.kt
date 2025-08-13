package com.otix.automotive.screen.home.component

import androidx.car.app.model.CarIcon
import androidx.car.app.model.Row
import androidx.core.graphics.drawable.IconCompat

fun OtixRowItem(
    title: String,
    text: String,
    iconCompat: IconCompat
): Row = OtixRow(title = title, text = text)
    .setImage(
        CarIcon
            .Builder(iconCompat)
            .build()
    )
    .build()

fun OtixRow(title: String, text: String): Row.Builder = Row.Builder()
    .setTitle(title)
    .addText(text)