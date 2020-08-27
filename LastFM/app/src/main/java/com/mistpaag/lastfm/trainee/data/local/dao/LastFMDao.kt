package com.mistpaag.lastfm.trainee.data.local.dao

import androidx.room.*
import com.mistpaag.lastfm.trainee.models.database.TopTrack
import com.mistpaag.lastfm.trainee.models.database.TopArtist

@Dao
interface LastFMDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopArtists(topArtists: List<TopArtist>)

    @Transaction
    @Query("SELECT * FROM topartist where page=:page;")
    fun fetchTopArtists(page:Int): List<TopArtist>

    @Query("SELECT * FROM topartist where name LIKE '%'||:name||'%' ;")
    fun searchTopArtists(name:String):List<TopArtist>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopTracks(topTracks: List<TopTrack>)

    @Transaction
    @Query("SELECT * FROM toptrack where page=:page;")
    fun fetchTopTracks(page:Int): List<TopTrack>

    @Query("SELECT * FROM toptrack where name LIKE '%'||:name||'%' ;")
    fun searchTopTracks(name:String):List<TopTrack>


    @Query("SELECT * FROM toptrack where name=:name ;")
    fun searchTopTrack(name:String): TopTrack

}