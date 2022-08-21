package com.alpesh.carsharingapp.viewmodel

import androidx.lifecycle.viewModelScope
import com.alpesh.carsharingapp.data.model.User
import com.alpesh.carsharingapp.data.repo.RegisterRepo
import com.alpesh.carsharingapp.utils.Response
import com.alpesh.carsharingapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val repo: RegisterRepo) : BaseViewModel(repo) {
    fun addUser(user: User): Response {
        var response = Response(504, "User is not Registered");
        viewModelScope.launch {
            response = repo.addUser(user.toHashMap(), user.getMobileNumber());
        }
        return response
    }
}