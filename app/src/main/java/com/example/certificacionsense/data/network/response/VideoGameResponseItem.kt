package com.example.certificacionsense.data.network.response

import com.google.gson.annotations.SerializedName

data class VideoGameResponseItem(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("released") val released: String,
    @SerializedName("background_image") val backgroundImage: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("metacritic") val metacritic: Int

)