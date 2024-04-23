package br.com.luiz.data.mapper

import br.com.luiz.data.model.ApiResponse
import br.com.luiz.domain.model.Response

fun <T, R> ApiResponse<T>.toEntity(mapper: (T) -> R): Response<R> {
    return Response(
        count = this.info.count,
        pages = this.info.pages,
        next = this.info.next,
        prev = this.info.prev,
        results = this.results.map(mapper),
    )
}
