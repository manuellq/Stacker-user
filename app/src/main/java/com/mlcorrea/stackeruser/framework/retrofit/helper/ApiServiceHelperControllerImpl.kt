package com.mlcorrea.stackeruser.framework.retrofit.helper

import com.mlcorrea.stackeruser.framework.retrofit.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by manuel on 27/07/19
 */
class ApiServiceHelperControllerImpl constructor(
    private val baseUrl: String, private val provideOkHttpClient: OkHttpClient
) :
    ApiServiceHelperController {

    override fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideOkHttpClient)
            .build()
    }

    override fun createService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }
}