package com.alejandro8a.androidTemplate.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val AVATAR_DB = "avatar-database"

val DatabaseModule = module {
//    single {
//        //TODO remove fallbackToDestructiveMigration when this goes to production
//        Room.databaseBuilder(androidContext(), AvatarDatabase::class.java, AVATAR_DB).fallbackToDestructiveMigration().build()
//    }

}