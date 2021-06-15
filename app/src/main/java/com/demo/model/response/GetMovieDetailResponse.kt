package com.demo.model.response


import com.google.gson.annotations.SerializedName

data class BelongsToCollection(@SerializedName("backdrop_path")
                               val backdropPath: String = "",
                               @SerializedName("name")
                               val name: String = "",
                               @SerializedName("id")
                               val id: Int = 0,
                               @SerializedName("poster_path")
                               val posterPath:  String = "")


data class SpokenLanguagesItem(@SerializedName("name")
                               val name: String = "",
                               @SerializedName("iso_639_1")
                               val iso: String = "",
                               @SerializedName("english_name")
                               val englishName: String = "")


data class GenresItem(@SerializedName("name")
                      val name: String = "",
                      @SerializedName("id")
                      val id: Int = 0)


data class ProductionCountriesItem(@SerializedName("iso_3166_1")
                                   val iso: String = "",
                                   @SerializedName("name")
                                   val name: String = "")


data class ProductionCompaniesItem(@SerializedName("logo_path")
                                   val logoPath: String = "",
                                   @SerializedName("name")
                                   val name: String = "",
                                   @SerializedName("id")
                                   val id: Int = 0,
                                   @SerializedName("origin_country")
                                   val originCountry: String = "")


data class GetMovieDetailResponse(@SerializedName("original_language")
                                  val originalLanguage: String = "",
                                  @SerializedName("imdb_id")
                                  val imdbId: String = "",
                                  @SerializedName("video")
                                  val video: Boolean = false,
                                  @SerializedName("title")
                                  val title: String = "",
                                  @SerializedName("backdrop_path")
                                  val backdropPath: String = "",
                                  @SerializedName("revenue")
                                  val revenue: Int = 0,
                                  @SerializedName("genres")
                                  val genres: List<GenresItem>?,
                                  @SerializedName("popularity")
                                  val popularity: Double = 0.0,
                                  @SerializedName("production_countries")
                                  val productionCountries: List<ProductionCountriesItem>?,
                                  @SerializedName("id")
                                  val id: Int = 0,
                                  @SerializedName("vote_count")
                                  val voteCount: Int = 0,
                                  @SerializedName("budget")
                                  val budget: Int = 0,
                                  @SerializedName("overview")
                                  val overview: String = "",
                                  @SerializedName("original_title")
                                  val originalTitle: String = "",
                                  @SerializedName("runtime")
                                  val runtime: Int = 0,
                                  @SerializedName("poster_path")
                                  val posterPath: String = "",
                                  @SerializedName("spoken_languages")
                                  val spokenLanguages: List<SpokenLanguagesItem>?,
                                  @SerializedName("production_companies")
                                  val productionCompanies: List<ProductionCompaniesItem>?,
                                  @SerializedName("release_date")
                                  val releaseDate: String = "",
                                  @SerializedName("vote_average")
                                  val voteAverage: Double = 0.0,
                                  @SerializedName("belongs_to_collection")
                                  val belongsToCollection: BelongsToCollection,
                                  @SerializedName("tagline")
                                  val tagline: String = "",
                                  @SerializedName("adult")
                                  val adult: Boolean = false,
                                  @SerializedName("homepage")
                                  val homepage: String = "",
                                  @SerializedName("status")
                                  val status: String = "")


