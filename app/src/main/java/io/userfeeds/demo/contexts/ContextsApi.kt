package io.userfeeds.demo.contexts

import io.reactivex.Single
import retrofit2.http.GET

interface ContextsApi {

    @GET("contexts")
    fun call(): Single<Map<String, ContextFromApi>>
}
