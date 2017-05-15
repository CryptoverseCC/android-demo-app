package io.userfeeds.demo.ranking

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RankingApi {

    @GET("ranking/{context}/{algorithm}")
    fun call(@Path("context") context: String, @Path("algorithm") algorithm: String): Single<RankingResponse>
}
