package com.khanhnq.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khanhnq.data.entity.LaunchEntity
import com.khanhnq.data.utils.getDistinct

@Dao
interface LaunchDao {

    @Query("SELECT * FROM launch ORDER BY name")
    fun getAllLaunches(): LiveData<List<LaunchEntity>>

    @Query("SELECT * FROM launch WHERE id = :id")
    fun getLaunch(id: String): LiveData<LaunchEntity>

    // Avoid false positive notifications for observable queries 
    // https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#5e38
    fun getDistinctLaunchById(id: String): LiveData<LaunchEntity> = getLaunch(id).getDistinct()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(launchEntities: List<LaunchEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(launchEntity: LaunchEntity)
}
