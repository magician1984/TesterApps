package idv.bruce.posservicetester.domain.controller

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import idv.bruce.posservicetester.core.data.StateRecord
import idv.bruce.posservicetester.domain.usecase.IUseCase
import idv.bruce.posservicetester.domain.usecase.IUseCaseGetHistories
import idv.bruce.posservicetester.domain.usecase.IUseCaseGetPackageInfo
import idv.bruce.posservicetester.domain.usecase.IUseCaseInitPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseRegisterPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseSendPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseTermPos
import idv.bruce.posservicetester.domain.usecase.IUseCaseUnregisterPos
import kotlin.reflect.KClass

class MockController : IUseCase.IUseCaseProvider {
    companion object {
        private const val TAG = "MockController"
    }

    private val history: SnapshotStateList<StateRecord> = mutableStateListOf()

    init {
        addHistory("MockControllerCreated")
    }

    @Throws(IUseCase.UseCaseNotImplementedException::class)
    override fun <T : IUseCase<*, *>> provideUseCase(useCaseClass: KClass<T>): IUseCase<*, *> {
        val useCase: IUseCase<*, *> = when (useCaseClass) {
            IUseCaseGetPackageInfo::class -> generateUseCaseGetPackageInfo()
            IUseCaseInitPos::class -> generateUseCaseInitPos()
            IUseCaseTermPos::class -> generateUseCaseTermPos()
            IUseCaseRegisterPos::class -> generateUseCaseRegisterPos()
            IUseCaseUnregisterPos::class -> generateUseCaseUnregisterPos()
            IUseCaseSendPos::class -> generateUseCaseSendPos()
            IUseCaseGetHistories::class -> generateUseCaseGetHistories()
            else -> throw IUseCase.UseCaseNotImplementedException(
                useCaseClass.simpleName ?: "Unknown"
            )
        }

        Log.d(TAG, "Provide use case: ${useCaseClass.simpleName}")
        return useCase
    }

    private fun addHistory(data: String) {
        Log.d(TAG, "Add history: $data")
        history.add(StateRecord(System.currentTimeMillis(), true, data))
    }

    private fun generateUseCaseGetPackageInfo(): IUseCaseGetPackageInfo {
        return object : IUseCaseGetPackageInfo {
            override fun invoke(input: Unit) {
                addHistory("GetPackageInfo")
            }
        }
    }

    private fun generateUseCaseInitPos(): IUseCaseInitPos {
        return object : IUseCaseInitPos {
            override fun invoke(input: Unit) {
                addHistory("InitPos")
            }
        }
    }

    private fun generateUseCaseTermPos(): IUseCaseTermPos {
        return object : IUseCaseTermPos {
            override fun invoke(input: Unit) {
                addHistory("TermPos")
            }
        }
    }

    private fun generateUseCaseRegisterPos(): IUseCaseRegisterPos {
        return object : IUseCaseRegisterPos {
            override fun invoke(input: Unit) {
                addHistory("RegisterPos")
            }
        }
    }

    private fun generateUseCaseUnregisterPos(): IUseCaseUnregisterPos {
        return object : IUseCaseUnregisterPos {
            override fun invoke(input: Unit) {
                addHistory("UnregisterPos")
            }
        }
    }

    private fun generateUseCaseSendPos(): IUseCaseSendPos {
        return object : IUseCaseSendPos {
            override fun invoke(input: String) {
                addHistory("Send: $input")
            }
        }
    }

    private fun generateUseCaseGetHistories(): IUseCaseGetHistories {
        return object : IUseCaseGetHistories {
            override fun invoke(input: Unit): List<StateRecord> = history
        }
    }
}