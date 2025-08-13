package com.otix.wear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.otix.core.resources.strings.Strings
import com.otix.core.ui.provider.LocalStrings
import com.otix.core.ui.theme.OtixTheme
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            val strings = koinInject<Strings>()

            CompositionLocalProvider(
                values = arrayOf(
                    LocalStrings provides strings
                )
            ) {
                WearApp()
            }
        }
    }
}

@Composable
fun WearApp() {
    OtixTheme {
        MainRoot()
    }
}
