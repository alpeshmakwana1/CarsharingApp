package com.alpesh.carsharingapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alpesh.carsharingapp.data.model.User
import com.alpesh.carsharingapp.data.repo.RegisterRepo
import com.alpesh.carsharingapp.utils.MyApplication
import com.alpesh.carsharingapp.utils.Resource
import com.alpesh.carsharingapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val repo: RegisterRepo) : BaseViewModel() {
    private val TAG = RegisterViewModel::class.java.canonicalName
    private val _userRegistrationStatus = MutableLiveData<Resource<Response>>()
    val userRegistrationStatus: LiveData<Resource<Response>> = _userRegistrationStatus

    fun addUser(user: User) {
//        _userRegistrationStatus.postValue(Resource.Loading())
//
//        viewModelScope.launch(Dispatchers.Main) {
//            val response = repo.addUser(user.toHashMap(), user.getMobileNumber());
//            _userRegistrationStatus.postValue(response)
//        }

        _userRegistrationStatus.postValue(Resource.Loading())

        viewModelScope.launch {
            val querySnapshot = repo.addUser(user.toHashMap(), user.getMobileNumber())
            querySnapshot.addOnSuccessListener {
                Log.e(TAG, "User Added")
                _userRegistrationStatus.postValue(
                    Resource.Success(
                        Response(
                            200,
                            "User registered successfully"
                        )
                    )
                )
            }.addOnFailureListener {
                Log.e(TAG, "Exception ${it.message}")
                var errorMessage = it.message ?: "User not registered"

                _userRegistrationStatus.postValue(
                    Resource.Error(
                        errorMessage,
                        Response(
                            504,
                            "User not registered"
                        )
                    )
                )
            }


        }
    }
}