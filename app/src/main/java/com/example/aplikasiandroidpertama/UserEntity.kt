package com.example.aplikasiandroidpertama

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.security.auth.callback.PasswordCallback

@Entity( tableName = "users" )
data class UserEntity (
    @PrimaryKey ( autoGenerate = true )
    val id : Int = 0,

    @ColumnInfo ( name ="first name" )
    val namaDepan : String,
    @ColumnInfo ( name = "last name")
    val namaBelakang : String,
    val username : String,
    val email : String,

    val password : String


)