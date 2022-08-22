package com.alpesh.carsharingapp.data.model

import com.alpesh.carsharingapp.utils.MyApplication

data class Car(
    var carId: String?,
    var carNumber: String,
    var carModel: String,
    var capacity: Int,
    var availableSeats: Int,
    var sourceStation: String,
    var destinationStation: String,
    var addedBy: String = MyApplication.loginUser.getUserId()
) : BaseModel() {
    fun toHashMap(): HashMap<String, String> {
        var hashMap = HashMap<String, String>();

        hashMap.apply {
            put("carNumber", carNumber)
            put("carModel", carModel)
            put("capacity", capacity.toString())
            put("availableSeats", availableSeats.toString())
            put("sourceStation", sourceStation)
            put("destinationStation", destinationStation)
            put("addedBy", addedBy)
        }

        return hashMap;
    }

    fun toObject(hashMap: HashMap<String, String>): Car {
        return Car(
            hashMap.getOrElse("carId") { "" },
            hashMap.getOrElse("carNumber") { "" },
            hashMap.getOrElse("carModel") { "" },
            hashMap.getOrDefault("capacity", { 0 }) as Int,
            hashMap.getOrDefault("availableSeats", { 0 }) as Int,
            hashMap.getOrElse("sourceStation") { "" },
            hashMap.getOrElse("destinationStation") { "" }
        );
    }
}
