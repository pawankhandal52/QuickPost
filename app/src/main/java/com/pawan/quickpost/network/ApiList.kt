package com.pawan.quickpost.network

import com.pawan.quickpost.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Pawan Kumar Sharma on 14-Sep-19.
 * Fleeca India Pvt Ltd
 * Android Developer
 * android_developer1@fleeca.in
 * +917737947610
 */
interface ApiList {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}