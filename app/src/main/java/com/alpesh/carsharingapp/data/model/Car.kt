package com.alpesh.carsharingapp.data.model

data class Car(
    private val carNumber: String,
    private val carModel: String,
    private val capacity: Int,
    private val availableSeats: Int,
    private val sourceStation: String,
    private val destinationStation: String
)
