package com.example.earthquaken_monitor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.json.JSONObject

class MainViewModel:ViewModel() {

    private var _eqList= MutableLiveData<MutableList<Earthquake>>()
    val eqList: LiveData<MutableList<Earthquake>>
        get() = _eqList

    init{
        viewModelScope.launch {
            _eqList.value = fetchEarthquakes()
        }

    }

    private suspend fun fetchEarthquakes(): MutableList<Earthquake> {
        return withContext(Dispatchers.IO) {
            val eqListString =service.getLastHourEarthquakes()
                //mutableListOf<Earthquake>()
//            eqList.add(Earthquake("1", "Buenos Aires", 4.3, 24141125, -102.25546, 28.32656))
//            eqList.add(Earthquake("2", "Lima", 2.9, 24141125, -102.25546, 28.32656))
//            eqList.add(Earthquake("3", "Ciudad de México", 3.2, 24141125, -102.25546, 28.32656))
//            eqList.add(Earthquake("4", "Bogotá", 5.2, 24141125, -102.25546, 28.32656))
//            eqList.add(Earthquake("5", "Caracas", 1.2, 24141125, -102.25546, 28.32656))
//            eqList.add(Earthquake("6", "Madrid", 0.9, 24141125, -102.25546, 28.32656))
//            eqList.add(Earthquake("7", "Medellin", 2.7, 24141125, -102.25546, 28.32656))
//            eqList.add(Earthquake("8", "Brasilia", 3.7, 24141125, -102.25546, 28.32656))
//            eqList.add(Earthquake("9", "Montevideo", 4.5, 24141125, -102.25546, 28.32656))


            val eqList = ParseEqResult(eqListString)

            eqList
        }
    }

    private fun ParseEqResult(eqListString: String): MutableList<Earthquake> {
        val eqJsonObject = JSONObject(eqListString)
        val featuresJsonArray = eqJsonObject.getJSONArray("features")
        val eqList = mutableListOf<Earthquake>()

        for(i in 0 until featuresJsonArray.length()){
            val featuresJsonObject = featuresJsonArray[i] as JSONObject
            val id = featuresJsonObject.getString("id")

            val propertiesJsonObject = featuresJsonObject.getJSONObject("properties")
            val magnitude = propertiesJsonObject.getDouble("mag")
            val place = propertiesJsonObject.getString("place")
            val time = propertiesJsonObject.getLong("time")

            val geometryJsonObject = featuresJsonObject.getJSONObject("geometry")
            val coordinatesJsonArray = geometryJsonObject.getJSONArray("coordinates")
            val longitude = coordinatesJsonArray.getDouble(0)
            val latitude = coordinatesJsonArray.getDouble(1)

            val earthquake = Earthquake(id, place, magnitude, time, longitude, latitude)
            eqList.add(earthquake)
        }
        return eqList
     }

}