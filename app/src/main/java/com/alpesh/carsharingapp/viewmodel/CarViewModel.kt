package com.alpesh.carsharingapp.viewmodel

import com.alpesh.carsharingapp.data.repo.BaseRepo
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CarViewModel @Inject constructor(repo: BaseRepo) : BaseViewModel(repo) {
}