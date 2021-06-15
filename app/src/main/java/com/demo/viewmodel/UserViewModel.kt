package com.demo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.demo.listener.CommonRepository
import com.demo.model.response.*

class UserViewModel @ViewModelInject constructor(
    val userRepositoryImpl: CommonRepository
) : ViewModel() {


    fun getMovieList(): LiveData<GetMovieListResponse> {
        return userRepositoryImpl.getMovieList();
    }

    fun getMovieDetail(id: Int): LiveData<GetMovieDetailResponse> {
        return userRepositoryImpl.getMovieDetail(id);
    }

    fun getMovieReviews(id: Int): LiveData<GetMovieReviewResponse> {
        return userRepositoryImpl.getMovieReviews(id);
    }

    fun getMovieCredits(id: Int): LiveData<GetMovieCreditsResponse> {
        return userRepositoryImpl.getMovieCredits(id);
    }

    fun getSimilarMovieList(id: Int): LiveData<GetSimilarMovieListResponse> {
        return userRepositoryImpl.getSimilarMovieList(id);
    }


}