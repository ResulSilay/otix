package com.otix.mobile.core.ui.scene

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.OverlayScene
import androidx.navigation3.ui.Scene
import androidx.navigation3.ui.SceneStrategy

@OptIn(ExperimentalMaterial3Api::class)
class BottomSheetScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    override val overlaidEntries: List<NavEntry<T>>,
    private val skipPartiallyExpanded: Boolean = true,
    private val confirmValueChange: (SheetValue) -> Boolean = { true },
    private val entry: NavEntry<T>,
    private val properties: ModalBottomSheetProperties,
    private val onBack: (count: Int) -> Unit,
    private val onDragClick: () -> Unit = {},
) : OverlayScene<T> {

    override val entries: List<NavEntry<T>> = listOf(entry)

    override val content: @Composable (() -> Unit)
        get() = {
            val sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = skipPartiallyExpanded,
                confirmValueChange = confirmValueChange
            )

            ModalBottomSheet(
                modifier = Modifier.padding(top = 32.dp),
                sheetState = sheetState,
                onDismissRequest = { onBack(1) },
                properties = properties,
                containerColor = MaterialTheme.colorScheme.background,
                scrimColor = MaterialTheme.colorScheme.background.copy(alpha = 0.75F),
                shape = RoundedCornerShape(size = 8.dp),
                dragHandle = {
                    BottomSheetDefaults.DragHandle(
                        modifier = Modifier.clickable(onClick = onDragClick),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1F)
                    )
                }
            ) {
                entry.Content()
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
data class BottomSheetSceneProperties(
    val skipPartiallyExpanded: Boolean = true,
    val confirmValueChange: (SheetValue) -> Boolean = { true },
    val properties: ModalBottomSheetProperties = ModalBottomSheetProperties(),
    val onDragClick: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
class BottomSheetSceneStrategy<T : Any> : SceneStrategy<T> {

    @Composable
    override fun calculateScene(
        entries: List<NavEntry<T>>,
        onBack: (count: Int) -> Unit,
    ): Scene<T>? {
        val lastEntry = entries.lastOrNull() ?: return null

        val sceneProperties = lastEntry.metadata[BOTTOM_SHEET_KEY] as? BottomSheetSceneProperties
            ?: return null

        return BottomSheetScene(
            key = lastEntry.contentKey,
            previousEntries = entries.dropLast(n = 1),
            overlaidEntries = entries.dropLast(n = 1),
            skipPartiallyExpanded = sceneProperties.skipPartiallyExpanded,
            confirmValueChange = sceneProperties.confirmValueChange,
            entry = lastEntry,
            properties = sceneProperties.properties,
            onBack = onBack,
            onDragClick = sceneProperties.onDragClick
        )
    }

    companion object {

        private const val BOTTOM_SHEET_KEY = "bottom_sheet"

        fun bottomSheet(properties: BottomSheetSceneProperties = BottomSheetSceneProperties()): Map<String, Any> =
            mapOf(BOTTOM_SHEET_KEY to properties)
    }
}