package com.mlcorrea.stackeruser.domain.exception

/**
 * Created by manuel on 27/07/19
 */
data class APIError(val error: Int?, val messageError: String?) : RuntimeException(messageError)