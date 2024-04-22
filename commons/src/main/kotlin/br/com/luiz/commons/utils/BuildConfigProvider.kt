package br.com.luiz.commons.utils

interface BuildConfigProvider {
    val isDebug: Boolean
    val applicationId: String
    val versionName: String
    val urlBase: String
}
