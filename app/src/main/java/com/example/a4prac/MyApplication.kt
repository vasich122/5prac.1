package com.example.a4prac

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Установка контекста приложения
            androidContext(this@MyApplication)
            // Подключение модулей
            modules(appModule)
        }
    }
}
