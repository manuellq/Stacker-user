package com.mlcorrea.stackeruser.framework.di

import androidx.room.Room
import com.mlcorrea.stackeruser.data.storage.PersistentData
import com.mlcorrea.stackeruser.framework.storage.PersistentDataImpl
import com.mlcorrea.stackeruser.framework.storage.database.DatabaseController
import com.mlcorrea.stackeruser.framework.storage.database.DatabaseControllerImpl
import com.mlcorrea.stackeruser.framework.storage.database.room.AppDatabase
import com.mlcorrea.stackeruser.framework.storage.database.room.wrapper.RoomModelWrap
import com.mlcorrea.stackeruser.framework.storage.database.room.wrapper.RoomModelWrapImpl
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by manuel on 27/07/19
 */

private const val DB_NAME = "stackeruser.db"

val databaseModule = module {

    single<PersistentData> { PersistentDataImpl(get(), get()) }
    single<DatabaseController> { DatabaseControllerImpl(get(), get()) }
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    } bind AppDatabase::class
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().userActionsDao() }
    factory<RoomModelWrap> { RoomModelWrapImpl() }

}