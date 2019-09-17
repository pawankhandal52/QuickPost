package com.pawan.quickpost.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Pawan Kumar Sharma on 16-Sep-19.
 * Fleeca India Pvt Ltd
 * Android Developer
 * android_developer1@fleeca.in
 * +917737947610
 */

@Dao
interface PostDao {
    @get:Query("SELECT * FROM post")
    val all: List<Post>

    @Insert
    fun insertAll(vararg posts: Post)

}