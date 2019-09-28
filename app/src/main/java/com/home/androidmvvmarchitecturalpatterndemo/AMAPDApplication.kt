package com.home.androidmvvmarchitecturalpatterndemo

import android.app.Application
import com.home.androidmvvmarchitecturalpatterndemo.model.network.MainApi
import com.home.androidmvvmarchitecturalpatterndemo.model.network.NetworkConnectionInterceptor
import com.home.androidmvvmarchitecturalpatterndemo.model.repositories.MainRepository
import com.home.androidmvvmarchitecturalpatterndemo.viewmodel.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * 繼承KodeinAware並綁定依賴
 */
class AMAPDApplication : Application(), KodeinAware {

    /**
     * 實例化Application級的kodein, 通過DSL綁定module
     */
    override val kodein = Kodein.lazy {

        // 導入預設的android組件
        import(androidXModule(this@AMAPDApplication))

        // 綁定或者導入自定義依賴
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MainApi(instance()) }
        bind() from singleton { MainRepository(instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
    }
}