package com.amaru.sandboxy

import com.fasterxml.jackson.annotation.JsonProperty

open class PrimitiveUser(

    @JsonProperty val email : String,
    @JsonProperty val password : String,
    @JsonProperty val username : String

)