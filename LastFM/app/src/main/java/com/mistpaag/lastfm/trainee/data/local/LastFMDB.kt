package com.mistpaag.lastfm.trainee.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mistpaag.lastfm.trainee.data.local.dao.LastFMDao
import com.mistpaag.lastfm.trainee.models.database.TopTrack
import com.mistpaag.lastfm.trainee.models.database.TopArtist

@Database(entities = [
    TopArtist::class,
    TopTrack::class
], version = 1, exportSchema = false)
abstract class LastFMDB : RoomDatabase() {
    abstract val lastFMDao: LastFMDao
}