package com.app.newsapp.model.local

import androidx.room.TypeConverter
import com.app.newsapp.model.remote.Source

class Convertor {

    @TypeConverter
    fun sourceChange(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun returnSource(name: String): Source {
        return Source(name)
    }
}