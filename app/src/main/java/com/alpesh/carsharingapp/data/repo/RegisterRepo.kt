package com.alpesh.carsharingapp.data.repo

import android.util.Log
import com.alpesh.carsharingapp.utils.Response
import com.alpesh.carsharingapp.utils.Result
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepo @Inject constructor(database: FirebaseFirestore) : BaseRepo(database) {
    private val TAG = RegisterRepo::class.java.canonicalName

    private val defaultDiapatcher = Dispatchers.IO
    suspend fun addUser(
        userMap: HashMap<String, String>,
        mobileNumber: String
    ): Response {
        var response = Response(504, "User is not Registered");

        withContext(defaultDiapatcher) {
            database.collection("users")
                .document(mobileNumber)
                .set(userMap)
                .addOnSuccessListener {
                    response = Response(200,"User registered successfully")
                    Log.e(TAG, "User Added")
                }
                .addOnFailureListener {
                    Log.e(TAG, "Exception ${it.message}")
                    response = Response(504,"User not registered")
                }
        }
        return response ;
    }
}