package mende273.foody.core.data.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import mende273.foody.core.data.Database
import mende273.foody.core.data.source.local.LocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { sqlDriverFactory(androidContext()) }
    single { createDatabase(driver = get()) }
    single { LocalDataSource(database = get()) }
}

fun sqlDriverFactory(context: Context): SqlDriver {
    return AndroidSqliteDriver(Database.Schema, context, "foody.db")
}

fun createDatabase(driver: SqlDriver): Database {
    return Database(driver)
}
