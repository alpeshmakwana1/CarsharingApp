package com.alpesh.carsharingapp.data.model

import com.alpesh.carsharingapp.utils.MyApplication

data class Car(
    var carId: String?,
    var carNumber: String,
    var carModel: String,
    var capacity: String,
    var availableSeats: String,
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

    companion object {
        fun toObject(hashMap: MutableMap<String, Any>): Car {
            return Car(
                hashMap.getOrElse("carId") { "" }.toString(),
                hashMap.getOrElse("carNumber") { "" }.toString(),
                hashMap.getOrElse("carModel") { "" }.toString(),
                hashMap.getOrElse("capacity") { 0 }.toString(),
                hashMap.getOrElse("availableSeats") { 0 }.toString(),
                hashMap.getOrElse("sourceStation") { "" }.toString(),
                hashMap.getOrElse("destinationStation") { "" }.toString()
            );
        }
    }
}
