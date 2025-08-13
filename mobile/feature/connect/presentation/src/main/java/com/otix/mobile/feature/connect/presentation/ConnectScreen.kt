package com.otix.mobile.feature.connect.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.otix.core.extension.orFalse
import com.otix.core.navigation.route.Home
import com.otix.core.navigation.route.Splash
import com.otix.core.resources.alias.Draw
import com.otix.core.resources.alias.Str
import com.otix.core.ui.component.OtixButton
import com.otix.core.ui.provider.LocalNavigator
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.feature.connect.presentation.receiver.RegisterCarAppStatusReceiver

@Composable
fun ConnectScreen() {
    val navigator = LocalNavigator.current
    val strings = LocalStrings.current

    val carAppIsActive = remember { mutableStateOf<Boolean?>(null) }

    val shouldNavigate = remember(carAppIsActive.value) {
        carAppIsActive.value.orFalse
    }

    RegisterCarAppStatusReceiver { isActive ->
        carAppIsActive.value = isActive
    }

    fun navigateToHome(isDemoAccount: Boolean) {
        navigator.navigate(
            route = Home(
                isDemoAccount = isDemoAccount
            ),
            popUpToRoute = Splash,
            inclusive = true
        )
    }

    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate) {
            navigateToHome(isDemoAccount = false)
        }
    }

    Scaffold(
        bottomBar = {
            ConnectBottomBar(
                demoAccountButtonText = strings[Str.vehicle_demo_account_button_text],
                onDemoAccountClick = {
                    navigateToHome(isDemoAccount = true)
                }
            )
        }
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddings)
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .size(size = 118.dp),
                painter = painterResource(id = Draw.ic_android_auto),
                contentDescription = "connect icon"
            )

            Text(
                modifier = Modifier.padding(all = 8.dp),
                text = strings[Str.vehicle_status_inform_title],
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                modifier = Modifier.padding(all = 8.dp),
                text = strings[Str.vehicle_status_inform_description],
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .size(size = 48.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round
            )
        }
    }
}

@Composable
private fun ConnectBottomBar(demoAccountButtonText: String, onDemoAccountClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OtixButton(
            text = demoAccountButtonText,
            onClick = onDemoAccountClick
        )
    }
}
