package com.example.ebobrovnichiy.weatherapp.db

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Relation
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["login"])
data class User(
        val userId:Int,
        @field:SerializedName("login")
        val login: String,
        @field:SerializedName("avatar_url")
        val avatarUrl: String?,
        @field:SerializedName("name")
        val name: String?,
        @field:SerializedName("company")
        val company: String?,
        @field:SerializedName("repos_url")
        val reposUrl: String?,
        @field:SerializedName("blog")
        val blog: String?,
        @Embedded
        val owners: Owner
)