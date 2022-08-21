package com.alpesh.carsharingapp.data.model

data class Car(
    private val carId: String?,
    private val carNumber: String,
    private val carModel: String,
    private val capacity: Int,
    private val availableSeats: Int,
    private val sourceStation: String,
    private val destinationStation: String
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
