package com.amaru.sandboxy

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query

object errorUI {

    var errorMsg : String? = ""
}
interface SignInterface {
    @POST("signIn")
    fun signIn(@Body user: User): Call<Void>
}

interface FetchInterface {
    @GET("getID")
    fun getUserID(@Query ("emailOrUsername") emailOrUsername: String ): Call<String>
}
