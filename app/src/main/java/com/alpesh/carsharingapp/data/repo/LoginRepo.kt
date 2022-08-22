package com.alpesh.carsharingapp.data.repo

import android.util.Log
import com.alpesh.carsharingapp.data.model.User
import com.alpesh.carsharingapp.utils.MyApplication
import com.alpesh.carsharingapp.utils.Resource
import com.alpesh.carsharingapp.utils.Response
import com.alpesh.carsharingapp.utils.safeCall
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepo @Inject constructor(database: FirebaseFirestore) : BaseRepo(database) {
    private val TAG = LoginRepo::class.java.canonicalName


    suspend fun checkLogin(email: String, password: String): Task<QuerySnapshot> {
        return withContext(Dispatchers.IO) {
            database.collection("users")
                .whereEqualTo("emailId", email)
                .whereEqualTo("password", password)
                .get()

        }

    }
}