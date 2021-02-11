package com.target.targetcasestudy.api.response


import com.google.gson.annotations.SerializedName

data class Product(
    val aisle: String,
    val description: String,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("regular_price")
    val regularPrice: RegularPrice,
    @SerializedName("sale_price")
    val salePrice: SalePrice,
    val title: String
)