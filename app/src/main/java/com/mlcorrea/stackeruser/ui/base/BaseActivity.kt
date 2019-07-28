package com.mlcorrea.stackeruser.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.mlcorrea.stackeruser.ui.error.ErrorMessageFactory

/**
 * Created by manuel on 27/07/19
 */
abstract class BaseActivity : AppCompatActivity() {

    /*----------------PUBLIC METHOD--------*/

    /**
     * get the error message
     *
     * @param exception [Exception]
     * @return [String]
     */
    fun getUserMessageError(exception: Exception?): String {
        return ErrorMessageFactory.createFromException(this, exception)
    }
}