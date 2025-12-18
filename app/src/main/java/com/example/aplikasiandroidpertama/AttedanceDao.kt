package com.example.aplikasiandroidpertama

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface AttedanceDao {
    @Insert
    suspend fun insertAttendance(attendance: AttendanceEntity) : Long
}