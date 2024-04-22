package br.com.luiz.rickandmortychallenge.utils

import br.com.luiz.commons.utils.BuildConfigProvider
import br.com.luiz.rickandmortychallenge.BuildConfig

class AppBuildConfigProvider : BuildConfigProvider {
    override val isDebug: Boolean
        get() = BuildConfig.DEBUG
    override val applicationId: String
        get() = BuildConfig.APPLICATION_ID
    override val versionName: String
        get() = BuildConfig.VERSION_NAME
    override val urlBase: String
        get() = BuildConfig.API_RICK_MORTY
}
