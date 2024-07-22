package idv.bruce.posservicetester

import android.content.Context
import idv.bruce.posservicetester.domain.controller.Controller
import idv.bruce.posservicetester.domain.controller.MockController
import idv.bruce.posservicetester.domain.presenter.Presenter
import idv.bruce.posservicetester.domain.usecase.IUseCase
import java.lang.ref.WeakReference

class Container(context: Context) {
    private val contextRef : WeakReference<Context> = WeakReference(context)

    private lateinit var controller : IUseCase.IUseCaseProvider

    private lateinit var presenter : IUseCase.IUseCaseConsumer

    val uiProvider : IUiProvider
        get() = presenter as IUiProvider

    fun onActivityCreated() {
        try {
            // Try to find and instantiate the Controller class
            val controllerClass = Class.forName("idv.bruce.posservicetester.domain.controller.Controller")
            val constructor = controllerClass.getConstructor(Context::class.java)
            controller = constructor.newInstance(contextRef.get()!!) as IUseCase.IUseCaseProvider
        } catch (e: ClassNotFoundException) {
            // If Controller class is not found, use MockController
            controller = MockController()
        }

        presenter = Presenter()

        presenter.bindUseCaseProvider(controller)
    }
}