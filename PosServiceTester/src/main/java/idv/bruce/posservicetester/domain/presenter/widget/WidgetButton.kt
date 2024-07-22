package idv.bruce.posservicetester.domain.presenter.widget

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import idv.bruce.posservicetester.domain.usecase.IUseCase

@Composable
internal fun WidgetButton(
    text: String,
    modifier: Modifier = Modifier,
    useCase: IUseCase.IUseCaseNoIO?,
    enabled: Boolean = true
) {
    var isEnabled by remember {
        mutableStateOf(enabled)
    }

    if(isEnabled != enabled)
        isEnabled = enabled

    Button(
        onClick = { useCase?.invoke(Unit) },
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)
    }
}