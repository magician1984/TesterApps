package idv.bruce.posservicetester

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable

interface IUiProvider {
    @Composable
    fun Draw(onBackPressedDispatcher: OnBackPressedDispatcher)
}