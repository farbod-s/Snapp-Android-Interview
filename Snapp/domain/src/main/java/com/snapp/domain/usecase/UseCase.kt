package com.snapp.domain.usecase

import com.snapp.domain.executor.PostExecutionThread
import com.snapp.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Abstract class for a UseCase that returns an instance of a [Flowable].
 */
abstract class UseCase<Data, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    /**
     * Builds a [Flowable] which will be used when the current [UseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: Params? = null): Flowable<Data>

    /**
     * Executes the current use case.
     */
    open fun execute(observer: DisposableSubscriber<Data>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler) as Flowable<Data>
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
