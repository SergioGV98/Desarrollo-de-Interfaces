package com.moronlu18.accounts.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "businessprofile")
data class BusinessProfile(@PrimaryKey val id: Int, val name: String="", val address:String="", val phoneNumber: String="")