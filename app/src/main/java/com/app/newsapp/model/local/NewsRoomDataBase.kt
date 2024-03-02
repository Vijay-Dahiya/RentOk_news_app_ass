package com.app.newsapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.newsapp.model.remote.Article

@Database(entities = [Article::class], version = 3)
@TypeConverters(Convertor::class)
abstract class NewsRoomDataBase : RoomDatabase() {

    abstract fun getDao(): NewsDao

    companion object {
        private var INSTANCE: NewsRoomDataBase? = null

        fun getDataBaseObject(context: Context): NewsRoomDataBase {
            if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    NewsRoomDataBase::class.java,
                    "song_db"
                )
                builder.fallbackToDestructiveMigration()

                INSTANCE = builder.build()
                return INSTANCE!!
            } else {
                return INSTANCE!!
            }
        }
    }
}
