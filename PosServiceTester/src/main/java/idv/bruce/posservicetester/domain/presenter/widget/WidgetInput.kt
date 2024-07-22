package idv.bruce.posservicetester.domain.presenter.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import idv.bruce.posservicetester.core.theme.PT1TesterTheme
import idv.bruce.posservicetester.domain.usecase.IUseCase

@Composable
fun WidgetInput(
    modifier: Modifier = Modifier,
    useCase: IUseCase.IUseCaseNoOutput<String>?,
    enabled: Boolean = true
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var msg by remember {
            mutableStateOf("")
        }

        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(value = msg, onValueChange = { value: String ->
            msg = value
        }, modifier = Modifier.weight(1f))

        Button(onClick = {
            keyboardController?.hide()
            if (msg.isEmpty()) return@Button
            useCase?.invoke(msg)
        }, enabled = enabled) {
            Text(text = "Send")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun Preview() {
    val useCase: IUseCase.IUseCaseNoOutput<String> = object : IUseCase.IUseCaseNoOutput<String> {
        override fun invoke(input: String) {
            TODO("Not yet implemented")
        }
    }

    PT1TesterTheme {
        WidgetInput(modifier = Modifier, useCase = useCase)
    }
}