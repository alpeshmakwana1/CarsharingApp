package com.alpesh.carsharingapp.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.alpesh.carsharingapp.data.repo.LoginRepo
import com.alpesh.carsharingapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepo) :
    BaseViewModel(loginRepo) {
    fun checkLogin(email : String,password:String): Response {
        var response = Response(504, "Incorrect Email ID or Password");
        viewModelScope.launch {
            response = loginRepo.checkLogin(email,password);
        }
        return response
    }
}