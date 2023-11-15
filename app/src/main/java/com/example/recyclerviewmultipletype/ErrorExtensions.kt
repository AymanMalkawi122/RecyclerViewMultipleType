package com.example.recyclerviewmultipletype

import android.util.Log
import retrofit2.HttpException
import java.io.IOException

fun Exception.handleError(TAG:String = "tag"): String{
    if (this is IOException) {
        Log.e(TAG, "IOException, you might not have internet connection")
        return "IOException, you might not have internet connection"
    } else if (this is HttpException) {
        Log.e(TAG, "HttpException, unexpected response")
        return "HttpException, unexpected response"
    }
    return "Something went"
}