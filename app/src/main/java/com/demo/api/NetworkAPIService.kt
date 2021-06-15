package com.demo.api

import com.demo.model.response.*
import retrofit2.Response
import retrofit2.http.*

interface NetworkAPIService {

    @GET(ApiName.GET_MOVIE_LIST)
    suspend fun getMovieList(
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Response<GetMovieListResponse>

    @GET("{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int,
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Response<GetMovieDetailResponse>

    @GET("{id}${ApiName.GET_REVIEWS}")
    suspend fun getReviews(
        @Path("id") movieId: Int,
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Response<GetMovieReviewResponse>

    @GET("{id}${ApiName.GET_CREDITS}")
    suspend fun getCredits(
        @Path("id") movieId: Int,
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Response<GetMovieCreditsResponse>

    @GET("{id}${ApiName.GET_SIMILAR_MOVIES}")
    suspend fun getSimilarMovieList(
        @Path("id") movieId: Int,
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Response<GetSimilarMovieListResponse>


}