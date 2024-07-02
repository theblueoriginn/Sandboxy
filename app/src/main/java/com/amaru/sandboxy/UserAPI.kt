package com.amaru.sandboxy

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("/addUser")
    fun addUser(@Body user: User): Call<Void>
}
interface VerifyAPI {
    @POST("/verify")
    fun verify(@Body requestBody: VerifyRequestBody): Call<Void>
}