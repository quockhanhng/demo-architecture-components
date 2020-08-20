package com.khanhnq.demo_architecture_components.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khanhnq.demo_architecture_components.data.model.Launch
import com.khanhnq.demo_architecture_components.utils.getDistinct

@Dao
interface LaunchDao {

    @Query("SELECT * FROM launch")
    fun getAllLaunches(): LiveData<List<Launch>>

    @Query("SELECT * FROM launch WHERE id = :id")
    fun getLaunch(id: String): LiveData<Launch>

    // Avoid false positive notifications for observable queries 
    // https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#5e38
    fun getDistinctLaunchById(id: String): LiveData<Launch> = getLaunch(id).getDistinct()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(launches: List<Launch>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(launch: Launch)
}
