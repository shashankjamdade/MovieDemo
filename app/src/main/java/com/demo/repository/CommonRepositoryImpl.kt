package com.demo.repository

import androidx.lifecycle.MutableLiveData
import com.demo.api.NetworkAPIService
import com.demo.listener.CommonRepository
import com.demo.model.response.*
import com.demo.util.Constants
import kotlinx.coroutines.*
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(val apiService: NetworkAPIService):
    CommonRepository {

    override fun getMovieList(): MutableLiveData<GetMovieListResponse> {
        val data = MutableLiveData<GetMovieListResponse>()
        val errorOnAPI = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService?.getMovieList(Constants.LANGUAGE, Constants.API_KEY)
                if (response?.isSuccessful!!) {
                    data.postValue(response?.body())
                } else {
                    errorOnAPI.postValue("Something went wrong::${response.message()}")
                }

            } catch (e: Exception) {
                errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
            }
        }
        return data;
    }

    override fun getMovieDetail(id: Int): MutableLiveData<GetMovieDetailResponse> {
        val data = MutableLiveData<GetMovieDetailResponse>()
        val errorOnAPI = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService?.getMovieDetail(id, Constants.LANGUAGE, Constants.API_KEY)
                if (response?.isSuccessful!!) {
                    data.postValue(response?.body())
                } else {
                    errorOnAPI.postValue("Something went wrong::${response.message()}")
                }

            } catch (e: Exception) {
                errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
            }
        }
        return data;
    }

    override fun getMovieReviews(id: Int): MutableLiveData<GetMovieReviewResponse> {
        val data = MutableLiveData<GetMovieReviewResponse>()
        val errorOnAPI = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService?.getReviews(id, Constants.LANGUAGE, Constants.API_KEY)
                if (response?.isSuccessful!!) {
                    data.postValue(response?.body())
                } else {
                    errorOnAPI.postValue("Something went wrong::${response.message()}")
                }

            } catch (e: Exception) {
                errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
            }
        }
        return data;
    }

    override fun getMovieCredits(id: Int): MutableLiveData<GetMovieCreditsResponse> {
        val data = MutableLiveData<GetMovieCreditsResponse>()
        val errorOnAPI = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService?.getCredits(id, Constants.LANGUAGE, Constants.API_KEY)
                if (response?.isSuccessful!!) {
                    data.postValue(response?.body())
                } else {
                    errorOnAPI.postValue("Something went wrong::${response.message()}")
                }

            } catch (e: Exception) {
                errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
            }
        }
        return data;
    }

    override fun getSimilarMovieList(id: Int): MutableLiveData<GetSimilarMovieListResponse> {
        val data = MutableLiveData<GetSimilarMovieListResponse>()
        val errorOnAPI = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService?.getSimilarMovieList(id, Constants.LANGUAGE, Constants.API_KEY)
                if (response?.isSuccessful!!) {
                    data.postValue(response?.body())
                } else {
                    errorOnAPI.postValue("Something went wrong::${response.message()}")
                }

            } catch (e: Exception) {
                errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
            }
        }
        return data;
    }

}