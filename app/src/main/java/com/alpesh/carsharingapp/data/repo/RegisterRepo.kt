package com.alpesh.carsharingapp.data.repo

import android.util.Log
import com.alpesh.carsharingapp.utils.Resource
import com.alpesh.carsharingapp.utils.Response
import com.alpesh.carsharingapp.utils.Result
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepo @Inject constructor(database: FirebaseFirestore) : BaseRepo(database) {
    private val TAG = RegisterRepo::class.java.canonicalName

    fun addUser(
        userMap: HashMap<String, String>,
        mobileNumber: String
    ): Task<Void> {
        var response = Response(504, "User is not Registered");
        var errorMessage: String = "User not registered"

        return database.collection("users")
            .document(mobileNumber)
            .set(userMap)

    }
}