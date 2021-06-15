package com.demo.listener

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.model.response.*

interface CommonRepository {

    fun getMovieList(): MutableLiveData<GetMovieListResponse>
    fun getMovieDetail(id:Int): MutableLiveData<GetMovieDetailResponse>
    fun getMovieReviews(id:Int): MutableLiveData<GetMovieReviewResponse>
    fun getMovieCredits(id:Int): MutableLiveData<GetMovieCreditsResponse>
    fun getSimilarMovieList(id:Int): MutableLiveData<GetSimilarMovieListResponse>

}