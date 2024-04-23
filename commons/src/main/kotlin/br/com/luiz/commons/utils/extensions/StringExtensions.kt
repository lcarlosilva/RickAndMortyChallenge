package br.com.luiz.commons.utils.extensions

import io.ktor.http.Url

fun String?.getPageFromUrl(param: String): Int? {
    return if (this != null) {
        val uri = Url(this)
        val nextPageQuery = uri.parameters[param]
        nextPageQuery?.toInt()
    } else {
        null
    }
}
