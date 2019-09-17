package com.pawan.quickpost.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pawan.quickpost.model.Post
import com.pawan.quickpost.model.PostDao

/**
 * Created by Pawan Kumar Sharma on 16-Sep-19.
 * Fleeca India Pvt Ltd
 * Android Developer
 * android_developer1@fleeca.in
 * +917737947610
 */
@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}