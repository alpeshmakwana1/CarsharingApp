package com.alpesh.carsharingapp.utils

import android.app.Application
import com.alpesh.carsharingapp.data.model.User
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        var loginUser: User =
            User(
                "0000", "Admin", "Admin", "0000000000",
                "xyz@abc.com", "0000-0000-0000-0000", "0000"
            )
    }
}