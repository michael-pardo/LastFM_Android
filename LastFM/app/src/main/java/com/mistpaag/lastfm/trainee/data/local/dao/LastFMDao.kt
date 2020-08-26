package com.mistpaag.lastfm.trainee.data.local.dao

import androidx.room.*
import com.mistpaag.lastfm.trainee.models.database.TopArtist

@Dao
interface LastFMDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopArtists(areas: List<TopArtist>)

    @Transaction
    @Query("SELECT * FROM topartist where page=:page")
    fun fetchTopArtists(page:Int): List<TopArtist>

    @Query("SELECT * FROM topartist where name LIKE '%'||:name||'%' ;")
    fun searchTopArtists(name:String):List<TopArtist>

}