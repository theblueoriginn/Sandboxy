package com.amaru.sandboxy

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
interface InfoInterface {
    @GET("getUsername")
    fun getUserName(@Query("userID") userID: Int): Call<UsernameResponse> // Change to Int
}
data class UsernameResponse @JsonCreator constructor(
    @JsonProperty("username") val username: String
)
