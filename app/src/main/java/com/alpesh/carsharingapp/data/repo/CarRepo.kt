package com.alpesh.carsharingapp.data.repo

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CarRepo @Inject constructor(database: FirebaseFirestore) : BaseRepo(database) {
}