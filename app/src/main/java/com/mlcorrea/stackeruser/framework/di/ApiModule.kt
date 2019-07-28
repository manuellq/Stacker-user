package com.mlcorrea.stackeruser.framework.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.mlcorrea.stackeruser.BuildConfig
import com.mlcorrea.stackeruser.domain.exception.HttpNoInternetConnectionException
import com.mlcorrea.stackeruser.framework.network.NetworkController
import com.mlcorrea.stackeruser.framework.network.NetworkControllerImpl
import com.mlcorrea.stackeruser.framework.retrofit.apimanager.ApiManager
import com.mlcorrea.stackeruser.framework.retrofit.apimanager.ApiManagerImpl
import com.mlcorrea.stackeruser.framework.retrofit.helper.ApiServiceHelperController
import com.mlcorrea.stackeruser.framework.retrofit.helper.ApiServiceHelperControllerImpl
import com.mlcorrea.stackeruser.util.UtilsSecurityNetwork
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by manuel on 27/07/19
 */

private const val HTTP_LOGIN_INTERCEPTOR_QUALIFIER = "httpLoggingInterceptor"
private const val STETHO_INTERCEPTOR_QUALIFIER = "stethoInterceptor"
private const val CONNECTIVITY_INTERCEPTOR_QUALIFIER = "NoConnectivityConnection"
private const val API_URL_QUALIFIER = "api_url"

val apiModule = module {

    single<ApiManager> { ApiManagerImpl(get()) }

    factory<ApiServiceHelperController> { ApiServiceHelperControllerImpl(get(named(API_URL_QUALIFIER)), get()) }

    single {
        UtilsSecurityNetwork
            .getOkHttpClient(
                get(named(STETHO_INTERCEPTOR_QUALIFIER)),
                get(named(HTTP_LOGIN_INTERCEPTOR_QUALIFIER)),
                get(named(CONNECTIVITY_INTERCEPTOR_QUALIFIER)),
                get(named(IS_DEBUG_QUALIFIER))
            )
    }

    factory(named(API_URL_QUALIFIER)) { BuildConfig.BASE_URL }


    single(named(HTTP_LOGIN_INTERCEPTOR_QUALIFIER)) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (get(named(IS_DEBUG_QUALIFIER))) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        httpLoggingInterceptor
    }

    single(named(STETHO_INTERCEPTOR_QUALIFIER)) { StethoInterceptor() }

    single<NetworkController> { NetworkControllerImpl(get()) } bind NetworkController::class

    factory(named(CONNECTIVITY_INTERCEPTOR_QUALIFIER)) {
        Interceptor { chain: Interceptor.Chain ->
            if (!get<NetworkController>().isConnected) {
                throw HttpNoInternetConnectionException()
            }
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
    }

}