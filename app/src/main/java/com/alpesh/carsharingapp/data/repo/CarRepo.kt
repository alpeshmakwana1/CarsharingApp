package com.alpesh.carsharingapp.data.repo

import android.util.Log
import com.alpesh.carsharingapp.utils.Resource
import com.alpesh.carsharingapp.utils.Response
import com.alpesh.carsharingapp.utils.safeCall
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarRepo @Inject constructor(database: FirebaseFirestore) : BaseRepo(database) {
    private val collection = database.collection("cars")
    private val TAG = CarRepo::class.java.canonicalName
    suspend fun addCar(carHashMap: HashMap<String, String>, carNumber: String): Task<Void> {
        return withContext(Dispatchers.IO) {
            collection
                .document(carNumber)
                .set(carHashMap)
        }
    }

    suspend fun getAllCar() : Task<QuerySnapshot> {
        return withContext(Dispatchers.IO){
            collection.get()
        }
    }
}