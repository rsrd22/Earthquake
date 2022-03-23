package com.example.earthquaken_monitor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "earthquakes")
data class Earthquake(@PrimaryKey val id: String, val place:String, val magnitude:Double, val time: Long,
                      val longitude: Double, val latitude: Double) {
}