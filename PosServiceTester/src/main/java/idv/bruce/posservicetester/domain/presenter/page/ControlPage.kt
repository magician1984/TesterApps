package idv.bruce.posservicetester.domain.presenter.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import idv.bruce.posservicetester.core.theme.PT1TesterTheme
import idv.bruce.posservicetester.domain.presenter.widget.WidgetButton
import idv.bruce.posservicetester.domain.presenter.widget.WidgetInput
import idv.bruce.posservicetester.domain.usecase.IUseCase
import idv.bruce.posservicetester.domain.usecase.IUseCaseGetPackageInfo
import idv.bruce.posservicetester.domain.usecase.IUseCaseInitPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseRegisterPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseSendPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseTermPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseUnregisterPos

@Composable
internal fun ControlPanel(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    useCaseGetPackageInfo: IUseCaseGetPackageInfo? = null,
    useCaseInitPos: IUseCaseInitPos? = null,
    useCaseRegisterPos: IUseCaseRegisterPos? = null,
    useCaseTermPos: IUseCaseTermPos? = null,
    useCaseUnregisterPos: IUseCaseUnregisterPos? = null,
    useCaseSendPos: IUseCaseSendPos? = null
) {
    Column(modifier = modifier) {
        WidgetButton(
            modifier = Modifier.fillMaxWidth(),
            text = "GetInfo",
            useCase = useCaseGetPackageInfo as? IUseCase.IUseCaseNoIO,
            enabled = enable
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            WidgetButton(
                modifier = Modifier.weight(1f),
                text = "InitPos",
                useCase = useCaseInitPos as? IUseCase.IUseCaseNoIO,
                enabled = enable
            )
            WidgetButton(
                modifier = Modifier.weight(1f),
                text = "Register",
                useCase = useCaseRegisterPos as? IUseCase.IUseCaseNoIO,
                enabled = enable
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            WidgetButton(
                modifier = Modifier.weight(1f),
                text = "TermPos",
                useCase = useCaseTermPos as? IUseCase.IUseCaseNoIO,
                enabled = enable
            )
            WidgetButton(
                modifier = Modifier.weight(1f),
                text = "Unregister",
                useCase = useCaseUnregisterPos as? IUseCase.IUseCaseNoIO,
                enabled = enable
            )
        }
        WidgetInput(useCase = useCaseSendPos as? IUseCase.IUseCaseNoOutput<String>)
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    PT1TesterTheme {
        ControlPanel(modifier = Modifier)
    }
}