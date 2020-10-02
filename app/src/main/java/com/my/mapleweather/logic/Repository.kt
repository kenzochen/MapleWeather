package com.my.mapleweather.logic

import androidx.lifecycle.liveData
import com.my.mapleweather.logic.model.Place
import com.my.mapleweather.logic.network.MapleNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse =MapleNetwork.searchPlace(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.place
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e :Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}