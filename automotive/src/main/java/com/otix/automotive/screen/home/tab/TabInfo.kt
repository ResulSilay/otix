package com.otix.automotive.screen.home.tab

import androidx.annotation.DrawableRes

internal data class TabInfo(
    val id: String,
    val title: String,
    @DrawableRes val iconRes: Int
)