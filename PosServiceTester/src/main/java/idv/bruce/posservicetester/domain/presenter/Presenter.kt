package idv.bruce.posservicetester.domain.presenter

import android.util.Log
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import idv.bruce.posservicetester.IUiProvider
import idv.bruce.posservicetester.core.data.StateRecord
import idv.bruce.posservicetester.core.theme.PT1TesterTheme
import idv.bruce.posservicetester.domain.presenter.page.ControlPanel
import idv.bruce.posservicetester.domain.presenter.page.HistoryPage
import idv.bruce.posservicetester.domain.presenter.page.PT1TopBar
import idv.bruce.posservicetester.domain.usecase.IUseCase
import idv.bruce.posservicetester.domain.usecase.IUseCaseGetHistories
import idv.bruce.posservicetester.domain.usecase.IUseCaseGetPackageInfo
import idv.bruce.posservicetester.domain.usecase.IUseCaseInitPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseRegisterPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseSendPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseTermPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseUnregisterPos
import kotlin.reflect.KClass

class Presenter : IUseCase.IUseCaseConsumer, IUiProvider {
    companion object {
        private const val TAG = "Presenter"
    }

    private var useCaseProvider: IUseCase.IUseCaseProvider? = null

    override fun bindUseCaseProvider(provider: IUseCase.IUseCaseProvider) {
        this.useCaseProvider = provider
    }

    private inline fun <reified T : IUseCase<*, *>> requestUseCase(useCaseClass: KClass<T>): T? {
        try {
            val useCase: IUseCase<*, *>? = useCaseProvider?.provideUseCase(useCaseClass)
            return useCase as? T
        } catch (e: IUseCase.UseCaseNotImplementedException) {
            Log.e(TAG, "Request use case failed: ${e.message}")
            return null
        }
    }

    @Composable
    override fun Draw(onBackPressedDispatcher: OnBackPressedDispatcher) {
        val records: List<StateRecord> =
            requestUseCase(IUseCaseGetHistories::class)?.invoke()?.asReversed() ?: emptyList()

        var enabled: Boolean by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = records) {
            records.lastOrNull()?.let {
                if (enabled != it.isConnected)
                    enabled = it.isConnected
            }
        }

        PT1TesterTheme {
            Scaffold(topBar = {
                PT1TopBar(title = "PosServiceTester", dispatcher = onBackPressedDispatcher)
            }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ControlPanel(
                        modifier = Modifier.wrapContentHeight(),
                        enable = enabled,
                        useCaseGetPackageInfo = requestUseCase(IUseCaseGetPackageInfo::class),
                        useCaseInitPos = requestUseCase(IUseCaseInitPos::class),
                        useCaseRegisterPos = requestUseCase(IUseCaseRegisterPos::class),
                        useCaseUnregisterPos = requestUseCase(IUseCaseUnregisterPos::class),
                        useCaseTermPos = requestUseCase(IUseCaseTermPos::class),
                        useCaseSendPos = requestUseCase(IUseCaseSendPos::class)
                    )
                    HorizontalDivider()
                    HistoryPage(
                        modifier = Modifier.weight(1f),
                        list = records
                    )
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    Presenter().Draw(onBackPressedDispatcher = OnBackPressedDispatcher())
}