package com.example.aplikasiandroidpertama

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT* FROM users ORDER BY id DESC")
    fun getAllUsers(): List<UserEntity>
}