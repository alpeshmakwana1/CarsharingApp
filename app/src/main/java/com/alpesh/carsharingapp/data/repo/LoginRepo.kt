package com.alpesh.carsharingapp.data.repo

import android.util.Log
import com.alpesh.carsharingapp.utils.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepo @Inject constructor(database: FirebaseFirestore) : BaseRepo(database) {
    private val TAG = LoginRepo::class.java.canonicalName

    suspend fun checkLogin(email: String, password: String): Response {
        var response = Response(504, "User is not Registered");

        withContext(Dispatchers.IO) {
            database.collection("users")
                .whereEqualTo("emailId", email)
                .whereEqualTo("password", password)
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        for (userdata in it)
                            Log.e(TAG, "user = ${userdata.data}")

                        response = Response(200, "Login success");
                    }
                }
                .addOnFailureListener {
                    Log.e(TAG, "user = ${it.message}")
                }
        }
        return response;
    }
}