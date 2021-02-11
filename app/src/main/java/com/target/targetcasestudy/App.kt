package com.target.targetcasestudy

import android.app.Application
import com.target.targetcasestudy.api.ConnectivityInterceptor
import com.target.targetcasestudy.api.ConnectivityInterceptorImpl
import com.target.targetcasestudy.api.Services
import com.target.targetcasestudy.data.RemoteDataSource
import com.target.targetcasestudy.repo.Repository
import com.target.targetcasestudy.ui.vm.DealListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class App: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { Services(instance()) }
        bind() from singleton { RemoteDataSource(instance()) }
        bind() from singleton { Repository(instance()) }
        bind() from singleton { DealListViewModelFactory(instance()) }
    }
}