package com.nursultan.cryptoapp.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class WorkerFactory
@Inject constructor(
    private val workerProviders: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return when (workerClassName) {
            RefreshDataWorker::class.qualifiedName ->
                workerProviders[RefreshDataWorker::class.java]?.get()
                    ?.create(appContext, workerParameters)
            RefreshCoinDailyInfoWorker::class.qualifiedName ->
                workerProviders[RefreshCoinDailyInfoWorker::class.java]?.get()
                    ?.create(appContext, workerParameters)
            else -> null
        }
    }
}