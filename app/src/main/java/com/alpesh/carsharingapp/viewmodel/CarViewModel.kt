package com.alpesh.carsharingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alpesh.carsharingapp.data.model.Car
import com.alpesh.carsharingapp.data.repo.CarRepo
import com.alpesh.carsharingapp.utils.Resource
import com.alpesh.carsharingapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(val repo: CarRepo) : BaseViewModel() {

    private val _carAddingStatus = MutableLiveData<Resource<Response>>()
    val carAddingStatus: LiveData<Resource<Response>> = _carAddingStatus

    fun addCar(car: Car) {
        _carAddingStatus.postValue(Resource.Loading())

        viewModelScope.launch {
            val response = repo.addCar(car.toHashMap(), car.carNumber)

            response.addOnSuccessListener {
                _carAddingStatus.postValue(
                    Resource.Success(
                        Response(200, "Car added successfully")
                    )
                )
            }.addOnFailureListener {
                val errorMessage = it.message ?: "Error while adding car"
                _carAddingStatus.postValue(
                    Resource.Error(
                        errorMessage,
                        Response(504, "Error while adding car")
                    )
                )
            }


        }

    }
}