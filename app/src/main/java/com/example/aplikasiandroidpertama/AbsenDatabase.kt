package com.example.aplikasiandroidpertama

import android.content.Context
import android.provider.CalendarContract
import androidx.activity.contextaware.ContextAware
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database ( entities = [
    UserEntity :: class,
    AttendanceEntity ::class
], version = 3,
    exportSchema = false)

abstract class AbsenDatabase : RoomDatabase() {
    abstract fun UserDao() : UserDao
    abstract fun attendanceDao(): AttedanceDao

            companion object {
                @Volatile
                private var INSTANCE : AbsenDatabase? = null

                fun getDatabase ( context : Context ) : AbsenDatabase{
                    return INSTANCE ?: synchronized (this) {
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            AbsenDatabase::class.java,
                            "Aplikasiabsen"
                        ).addMigrations(MIGRATION_2_3).build()
                        INSTANCE = instance
                        instance
                    }
                }
            }
}