package idv.bruce.posservicetester.domain.usecase

import idv.bruce.posservicetester.core.data.StateRecord


interface IUseCaseGetHistories : IUseCase.IUseCaseNoInput<List<StateRecord>>

interface IUseCaseGetPackageInfo : IUseCase.IUseCaseNoIO

interface IUseCaseRegisterPos : IUseCase.IUseCaseNoIO

interface IUseCaseInitPos : IUseCase.IUseCaseNoIO

interface IUseCaseSendPos : IUseCase.IUseCaseNoOutput<String>

interface IUseCaseTermPos : IUseCase.IUseCaseNoIO

interface IUseCaseUnregisterPos : IUseCase.IUseCaseNoIO