package com.demo.model.response


import com.google.gson.annotations.SerializedName

data class GetMovieCreditsResponse(@SerializedName("cast")
                                   val cast: List<CastItem>?,
                                   @SerializedName("id")
                                   val id: Int = 0,
                                   @SerializedName("crew")
                                   val crew: List<CrewItem>?)


data class CrewItem(@SerializedName("gender")
                    val gender: Int = 0,
                    @SerializedName("credit_id")
                    val creditId: String = "",
                    @SerializedName("known_for_department")
                    val knownForDepartment: String = "",
                    @SerializedName("original_name")
                    val originalName: String = "",
                    @SerializedName("popularity")
                    val popularity: Double = 0.0,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("profile_path")
                    val profilePath: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("adult")
                    val adult: Boolean = false,
                    @SerializedName("department")
                    val department: String = "",
                    @SerializedName("job")
                    val job: String = "")


data class CastItem(@SerializedName("cast_id")
                    val castId: Int = 0,
                    @SerializedName("character")
                    val character: String = "",
                    @SerializedName("gender")
                    val gender: Int = 0,
                    @SerializedName("credit_id")
                    val creditId: String = "",
                    @SerializedName("known_for_department")
                    val knownForDepartment: String = "",
                    @SerializedName("original_name")
                    val originalName: String = "",
                    @SerializedName("popularity")
                    val popularity: Double = 0.0,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("profile_path")
                    val profilePath: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("adult")
                    val adult: Boolean = false,
                    @SerializedName("order")
                    val order: Int = 0)


