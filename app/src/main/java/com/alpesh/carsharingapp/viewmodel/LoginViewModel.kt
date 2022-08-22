package com.alpesh.carsharingapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alpesh.carsharingapp.data.model.User
import com.alpesh.carsharingapp.data.repo.LoginRepo
import com.alpesh.carsharingapp.utils.MyApplication
import com.alpesh.carsharingapp.utils.Resource
import com.alpesh.carsharingapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepo) :
    BaseViewModel() {
    private val TAG = RegisterViewModel::class.java.canonicalName
    private val _userLoginStatus = MutableLiveData<Resource<Response>>()
    val userLoginStatus: LiveData<Resource<Response>> = _userLoginStatus

    fun checkLogin(email: String, password: String) {

        _userLoginStatus.postValue(Resource.Loading())

        viewModelScope.launch {
            val querySnapshot = loginRepo.checkLogin(email, password)

            querySnapshot.addOnSuccessListener {
                if (it != null && !it.isEmpty) {
                    for (document in it) {
                        MyApplication.loginUser = User.toObject(document.data)
                    }
                    _userLoginStatus.postValue(Resource.Success(Response(200, "Login Success")))
                } else {
                    _userLoginStatus.postValue(Resource.Success(Response(200, "User not found")))
                }

            }.addOnFailureListener {
                Log.e(TAG, "Exception ${it.message}")
                var errorMessage = it.message ?: "Error while Login"
                Resource.Error(
                    errorMessage,
                    Response(
                        504,
                        "Error while Login"
                    )
                )
            }
        }
    }
}