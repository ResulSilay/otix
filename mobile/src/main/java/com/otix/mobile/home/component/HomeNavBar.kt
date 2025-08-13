package com.otix.mobile.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.otix.core.resources.strings.Strings
import com.otix.mobile.home.tab.getTabs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun HomeNavBar(
    strings: Strings,
    scope: CoroutineScope,
    pagerState: PagerState,
    selectedTabIndex: State<Int>
) {
    Column {
        HorizontalDivider(
            color = Color.White.copy(alpha = 0.1F),
            thickness = 0.3.dp
        )

        NavigationBar(
            contentColor = MaterialTheme.colorScheme.background,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            getTabs().forEachIndexed { index, tab ->
                NavigationBarItem(
                    alwaysShowLabel = false,
                    selected = selectedTabIndex.value == index,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onSurface,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F),
                        indicatorColor = MaterialTheme.colorScheme.background
                    ),
                    icon = {
                        Icon(
                            painter = painterResource(id = tab.iconRes),
                            contentDescription = strings[tab.titleRes]
                        )
                    },
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = index)
                        }
                    }
                )
            }
        }
    }
}