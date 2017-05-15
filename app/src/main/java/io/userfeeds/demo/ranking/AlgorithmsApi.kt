package io.userfeeds.demo.ranking

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AlgorithmsApi {

    @GET("ranking/{context}/")
    fun call(@Path("context") context: String): Single<AlgorithmsResponse>
}
