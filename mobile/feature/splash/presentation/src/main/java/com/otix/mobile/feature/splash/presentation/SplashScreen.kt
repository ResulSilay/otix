package com.otix.mobile.feature.splash.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.otix.core.navigation.route.Connect
import com.otix.core.navigation.route.Splash
import com.otix.core.resources.alias.Str
import com.otix.core.ui.component.OtixTitle
import com.otix.core.ui.provider.LocalNavigator
import com.otix.core.ui.provider.LocalStrings
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    val navigator = LocalNavigator.current
    val strings = LocalStrings.current

    LaunchedEffect(Unit) {
        delay(timeMillis = 2000)

        navigator.navigate(
            route = Connect,
            popUpToRoute = Splash,
            inclusive = true
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        OtixTitle(
            title = strings[Str.app_title],
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 20F
        )
    }
}
