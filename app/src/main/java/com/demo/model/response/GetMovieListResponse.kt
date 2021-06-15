package com.demo.model.response


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class MovieResultsItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @SerializedName("original_title")
    val originalTitle: String? = "",
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("vote_count")
    val voteCount: Int = 0
) : Parcelable


@Parcelize
data class Dates(
    @SerializedName("maximum")
    val maximum: String? = "",
    @SerializedName("minimum")
    val minimum: String? = ""
) : Parcelable

@Parcelize
data class GetMovieListResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("results")
    val results: List<MovieResultsItem>?,
    @SerializedName("total_results")
    val totalResults: Int = 0
) : Parcelable


