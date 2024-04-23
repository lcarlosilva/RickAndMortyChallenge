package br.com.luiz.rickandmortychallenge

import android.app.Application
import br.com.luiz.commons.di.commonsModule
import br.com.luiz.data.di.dataModule
import br.com.luiz.rickandmortychallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(getModules())
        }
    }

    private fun getModules() =
        listOf(
            commonsModule(),
            dataModule(),
            appModule(),
        )
}
