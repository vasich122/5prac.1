package com.example.a4prac

import android.content.Context
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { createDatabase(get()) }  // Передаем контекст
    single { get<AppDataBase>().postsDao() }  // Получаем DAO из базы данных
    single { createRetrofit() }
    single { get<Retrofit>().create(MainApi::class.java) }
}

// Создание базы данных
fun createDatabase(context: Context): AppDataBase {
    return Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "your_database_name"
    ).build()
}

// Создание Retrofit
fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
