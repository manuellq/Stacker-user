package com.mlcorrea.stackeruser.framework.retrofit.helper

import com.mlcorrea.stackeruser.framework.retrofit.service.ApiService
import retrofit2.Retrofit

/**
 * Created by manuel on 27/07/19
 */
interface ApiServiceHelperController {

    fun createRetrofit(): Retrofit

    fun createService(retrofit: Retrofit): ApiService
}