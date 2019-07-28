package com.mlcorrea.stackeruser

import android.app.Application
import android.content.IntentFilter
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.mlcorrea.stackeruser.framework.di.*
import com.mlcorrea.stackeruser.framework.receiver.NetworkStatusReceiver
import com.mlcorrea.stackeruser.framework.timber.LifeTreeTimber
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by manuel on 27/07/19
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        injectApplicationComponent()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        initTimber()
        strictMode()
        initializeStetho()
        initReceiver()
    }

    private fun initReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED")
        registerReceiver(NetworkStatusReceiver(), intentFilter)
    }

    private fun injectApplicationComponent() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    apiModule,
                    dataModule,
                    appModule,
                    fragmentScope,
                    databaseModule,
                    activityScope
                )
            )
        }
    }

    /**
     * We log everything in debug mode and we plant a tree for Release
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            val formatElementPlant = "%s:%s"
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        formatElementPlant, super.createStackElementTag(element),
                        element.lineNumber
                    )
                }
            })
        } else {
            Timber.plant(LifeTreeTimber())
        }
    }

    /**
     * This is only used in debug mode
     */
    private fun initializeStetho() {
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )
    }

    /**
     * this is only for testing purposes and it only should be used in debug mode, it check ARN error
     * and others things
     */
    private fun strictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
        }
    }
}