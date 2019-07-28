package com.mlcorrea.stackeruser.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mlcorrea.stackeruser.ui.error.ErrorMessageFactory

/**
 * Created by manuel on 27/07/19
 */
abstract class BaseActivity : AppCompatActivity() {

    /*----------------PUBLIC METHOD--------*/

    fun addFragment(containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(containerViewId, fragment)
            .commit()
    }

    /**
     * Display a snack bar
     *
     * @param message [String]
     */
    fun showSnackBar(message: String?) {
        if (message?.isEmpty() == true) {
            return
        }
        val view = findViewById<View>(android.R.id.content)
        if (view != null) {
            Snackbar.make(view, message!!, Snackbar.LENGTH_LONG).show()
        }
    }

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