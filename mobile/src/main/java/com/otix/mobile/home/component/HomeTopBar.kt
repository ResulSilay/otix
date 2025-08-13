package com.otix.mobile.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.otix.core.ui.component.OtixTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeTopBar(title: String) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                OtixTitle(title = title)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.primary,
                actionIconContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}