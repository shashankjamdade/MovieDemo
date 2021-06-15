package com.demo.model.response


import com.google.gson.annotations.SerializedName

data class ReviewResultsItem(@SerializedName("author_details")
                       val authorDetails: AuthorDetails,
                       @SerializedName("updated_at")
                       val updatedAt: String = "",
                       @SerializedName("author")
                       val author: String = "",
                       @SerializedName("created_at")
                       val createdAt: String = "",
                       @SerializedName("id")
                       val id: String = "",
                       @SerializedName("content")
                       val content: String = "",
                       @SerializedName("url")
                       val url: String = "")


data class AuthorDetails(@SerializedName("avatar_path")
                         val avatarPath: String = "",
                         @SerializedName("name")
                         val name: String = "",
                         @SerializedName("rating")
                         val rating: Int = 0,
                         @SerializedName("username")
                         val username: String = "")


data class GetMovieReviewResponse(@SerializedName("id")
                                  val id: Int = 0,
                                  @SerializedName("page")
                                  val page: Int = 0,
                                  @SerializedName("total_pages")
                                  val totalPages: Int = 0,
                                  @SerializedName("results")
                                  val results: List<ReviewResultsItem>?,
                                  @SerializedName("total_results")
                                  val totalResults: Int = 0)


