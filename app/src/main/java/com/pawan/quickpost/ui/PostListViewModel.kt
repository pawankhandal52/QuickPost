package com.pawan.quickpost.ui

import io.reactivex.Observable
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.pawan.quickpost.R
import com.pawan.quickpost.base.BaseViewModel
import com.pawan.quickpost.model.Post
import com.pawan.quickpost.model.PostDao
import com.pawan.quickpost.network.ApiList
import com.pawan.quickpost.ui.post.PostListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by Pawan Kumar Sharma on 14-Sep-19.
 * Fleeca India Pvt Ltd
 * Android Developer
 * android_developer1@fleeca.in
 * +917737947610
 */
class PostListViewModel(private val postDao: PostDao):BaseViewModel() {

    @Inject
    lateinit var postApi: ApiList

    private lateinit var subscription: Disposable
    val loadingBar: MutableLiveData<Int> = MutableLiveData()


    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPost() }

    val postListAdapter: PostListAdapter = PostListAdapter()


    init {
        loadPost()
    }

    private fun loadPost() {
        subscription = Observable.fromCallable { postDao.all }
            .concatMap { dbPostList ->
                if (dbPostList.isEmpty())
                    postApi.getPosts().concatMap { apiPostList ->
                        postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError(it) }
            )
    }

    /*postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{onRetrievePostListStart()}
            .doOnTerminate{onRetrievePostListFinish()}
            .subscribe({
                result->onRetrievePostListSuccess(result)
            },{
                onRetrievePostListError(it)
            })*/



    private fun onRetrievePostListError(it: Throwable) {
        Log.d("ERROR",it.localizedMessage.toString())
        Log.d("ERROR1",it.message)
        errorMessage.value = R.string.post_error
    }

    private fun onRetrievePostListSuccess(postList:List<Post>){
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListFinish() {
        loadingBar.value = View.GONE

    }

    private fun onRetrievePostListStart() {
        loadingBar.value = View.VISIBLE
        errorMessage.value = null
    }


}