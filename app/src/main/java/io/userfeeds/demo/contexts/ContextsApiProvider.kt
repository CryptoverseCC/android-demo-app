package io.userfeeds.demo.contexts

import io.userfeeds.infrastructure.retrofit.ApiProvider

object ContextsApiProvider : ApiProvider<ContextsApi>(ContextsApi::class)
