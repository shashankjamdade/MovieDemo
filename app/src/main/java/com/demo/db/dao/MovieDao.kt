package com.demo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo.model.response.MovieResultsItem

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieResultsItem")
    fun loadMovieList(): LiveData<List<MovieResultsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieResultsItem)

    @Update
    fun updateMovie(movie: MovieResultsItem)
}
