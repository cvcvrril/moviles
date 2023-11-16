package com.example.recyclerretrofitinesmr.data.model

import com.google.gson.annotations.SerializedName

data class DirectorResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)