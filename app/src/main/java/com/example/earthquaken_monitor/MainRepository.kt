package com.example.earthquaken_monitor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository {

     suspend fun fetchEarthquakes(): MutableList<Earthquake> {
        return withContext(Dispatchers.IO) {
            val eqJsonResponse =service.getLastHourEarthquakes()
            val eqList = ParseEqResult(eqJsonResponse)

            eqList
        }
    }

    private fun ParseEqResult(eqJsonResponse: EqJsonResponse): MutableList<Earthquake> {

        val eqList = mutableListOf<Earthquake>()
        val featureList = eqJsonResponse.features

        for (feature in featureList){
            val properties = feature.properties

            val id = feature.id
            val magnitude = properties.magnitude
            val place = properties.place
            val time = properties.time

            val geometry = feature.geometry
            val longitude = geometry.longitude
            val latitude = geometry.latitude

            eqList.add(Earthquake(id, place, magnitude, time, longitude, latitude))
        }

        return eqList
    }
}