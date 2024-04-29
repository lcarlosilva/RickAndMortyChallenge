package br.com.luiz.rickandmortychallenge

import android.app.Application
import br.com.luiz.commons.di.commonsModule
import br.com.luiz.data.di.dataModule
import br.com.luiz.domain.di.domainModule
import br.com.luiz.rickandmortychallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(getModules())
        }
    }

    private fun getModules() =
        listOf(
            commonsModule(),
            dataModule(),
            domainModule(),
            appModule(),
        )
}
