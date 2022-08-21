package com.alpesh.carsharingapp.data.repo

import com.alpesh.carsharingapp.utils.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class BaseRepo(protected val database: FirebaseFirestore)