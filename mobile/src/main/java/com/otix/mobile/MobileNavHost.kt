package com.otix.mobile

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.otix.core.navigation.navigator.Navigator
import com.otix.core.navigation.route.Connect
import com.otix.core.navigation.route.FuelCapacity
import com.otix.core.navigation.route.Home
import com.otix.core.navigation.route.Splash
import com.otix.core.navigation.route.TripDetail
import com.otix.core.navigation.route.TripMap
import com.otix.core.resources.strings.Strings
import com.otix.core.ui.provider.LocalNavigator
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.core.ui.scene.BottomSheetSceneProperties
import com.otix.mobile.core.ui.scene.BottomSheetSceneStrategy
import com.otix.mobile.empty.EmptyScreen
import com.otix.mobile.feature.connect.presentation.ConnectScreen
import com.otix.mobile.feature.fuel.presentation.screen.capacity.FuelCapacityRoot
import com.otix.mobile.feature.splash.presentation.SplashScreen
import com.otix.mobile.feature.trip.presentation.screen.detail.TripDetailRoot
import com.otix.mobile.feature.trip.presentation.screen.map.TripMapRoot
import com.otix.mobile.home.HomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileNavHost() {
    val navigator = koinInject<Navigator>()
    val strings = koinInject<Strings>()
    val backStack = rememberNavBackStack(Splash)

    LaunchedEffect(Unit) {
        navigator.setBackStack(stack = backStack)
    }

    CompositionLocalProvider(
        values = arrayOf(
            LocalNavigator provides navigator,
            LocalStrings provides strings
        )
    ) {
        NavDisplay(
            backStack = backStack,
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            onBack = {
                navigator.onBack()
            },
            sceneStrategy = remember {
                BottomSheetSceneStrategy<Any>()
            },
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(durationMillis = 300)
                ) togetherWith fadeOut(
                    animationSpec = tween(durationMillis = 300)
                )
            },
            entryProvider = entryProvider(
                fallback = { key ->
                    NavEntry(key = key) {
                        EmptyScreen(key = key)
                    }
                }
            ) {
                entry<Splash> {
                    SplashScreen()
                }

                entry<Connect> {
                    ConnectScreen()
                }

                entry<Home> { key ->
                    HomeScreen(isDemoAccount = key.isDemoAccount)
                }

                entry<TripDetail>(metadata = BottomSheetSceneStrategy.bottomSheet()) { key ->
                    TripDetailRoot(
                        viewModel = koinViewModel(
                            parameters = { parametersOf(key) }
                        )
                    )
                }

                entry<TripMap>(
                    metadata = BottomSheetSceneStrategy.bottomSheet(
                        properties = BottomSheetSceneProperties(
                            confirmValueChange = { false },
                            onDragClick = navigator::onBack
                        )
                    )
                ) { key ->
                    TripMapRoot(
                        viewModel = koinViewModel(
                            parameters = { parametersOf(key) }
                        )
                    )
                }

                entry<FuelCapacity>(metadata = BottomSheetSceneStrategy.bottomSheet()) { key ->
                    FuelCapacityRoot(
                        viewModel = koinViewModel(
                            parameters = { parametersOf(key) }
                        )
                    )
                }
            }
        )
    }
}
