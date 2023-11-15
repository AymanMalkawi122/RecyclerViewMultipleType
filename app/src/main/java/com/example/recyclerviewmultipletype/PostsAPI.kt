package com.example.recyclerviewmultipletype

import retrofit2.Response
import retrofit2.http.GET

interface PostsAPI {
    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>
    @GET("/albums/1/photos")
    suspend fun getImages(): Response<List<Image>>
}