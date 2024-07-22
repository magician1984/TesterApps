package idv.bruce.posservicetester.domain.usecase

import kotlin.reflect.KClass

interface IUseCase<I, O> {
    operator fun invoke(input: I): O

    interface IUseCaseProvider {
        fun <T : IUseCase<*, *>> provideUseCase(useCaseClass: KClass<T>): IUseCase<*, *>
    }

    interface IUseCaseConsumer {
        fun bindUseCaseProvider(provider: IUseCaseProvider)
    }

    interface IUseCaseNoInput<O> : IUseCase<Unit, O> {
        operator fun invoke(): O = invoke(Unit)
    }

    interface IUseCaseNoOutput<I> : IUseCase<I, Unit>

    interface IUseCaseNoIO : IUseCase<Unit, Unit> {
        operator fun invoke() = invoke(Unit)
    }

    class UseCaseNotImplementedException(clsName: String) :
        RuntimeException("UseCase not implemented: $clsName")
}