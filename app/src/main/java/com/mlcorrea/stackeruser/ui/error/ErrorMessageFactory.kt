package com.mlcorrea.stackeruser.ui.error

import android.content.Context
import com.mlcorrea.stackeruser.R
import com.mlcorrea.stackeruser.domain.exception.APIError
import com.mlcorrea.stackeruser.domain.exception.HttpNoInternetConnectionException

/**
 * Created by manuel on 27/07/19
 */
interface ErrorMessageFactory {

    companion object Factory {

        fun createFromException(context: Context, exception: Exception?): String {
            return when (exception) {
                is HttpNoInternetConnectionException ->
                    context.getString(R.string.exception_message_no_connection)
                is APIError -> {
                    exception.messageError ?: context.getString(R.string.exception_generic_message)
                }
                else -> context.getString(R.string.exception_generic_message)
            }
        }
    }
}

fun ErrorMessageFactory.Factory.fromException(context: Context, exception: Exception?) =
    this.createFromException(context, exception)