package com.pawan.quickpost.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Pawan Kumar Sharma on 14-Sep-19.
 * Fleeca India Pvt Ltd
 * Android Developer
 * android_developer1@fleeca.in
 * +917737947610
 */
@Entity
data class Post(
    val userId: Int,
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)