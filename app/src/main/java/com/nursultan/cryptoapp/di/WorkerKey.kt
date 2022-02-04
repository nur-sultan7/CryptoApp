package com.nursultan.cryptoapp.di

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val kClass: KClass<out ListenableWorker>)
