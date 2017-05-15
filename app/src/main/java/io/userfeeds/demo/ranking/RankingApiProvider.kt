package io.userfeeds.demo.ranking

import io.userfeeds.infrastructure.retrofit.ApiProvider

object RankingApiProvider : ApiProvider<RankingApi>(RankingApi::class)
