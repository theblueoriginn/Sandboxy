package com.amaru.sandboxy

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("username") val username: String
)
data class VerifyRequestBody(val email: String, val verifCode: String)