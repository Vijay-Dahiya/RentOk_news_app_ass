package com.app.newsapp.di

import android.content.Context
import androidx.room.Room
import com.app.newsapp.model.local.NewsDao
import com.app.newsapp.model.local.NewsRoomDataBase
import com.app.newsapp.model.remote.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Singleton
    @Provides
    fun providesApiService(): ApiClient {
        val builder = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun providesRoomDataBase(@ApplicationContext context: Context): NewsRoomDataBase {
        val builder = Room.databaseBuilder(
            context,
            NewsRoomDataBase::class.java,
            "news_db"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun providesDataToDb(db: NewsRoomDataBase): NewsDao {
        return db.getDao()
    }
}