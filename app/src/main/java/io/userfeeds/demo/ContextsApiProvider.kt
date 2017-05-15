package io.userfeeds.demo

import io.userfeeds.infrastructure.retrofit.ApiProvider

object ContextsApiProvider : ApiProvider<ContextsApi>(ContextsApi::class)
