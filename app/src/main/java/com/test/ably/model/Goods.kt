package com.test.ably.model

import com.google.gson.annotations.SerializedName

data class Goods(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("image") var image : String,
    @SerializedName("actual_price") var actual_price : Int,
    @SerializedName("price") var price : Int,
    @SerializedName("is_new") var is_new : Boolean,
    @SerializedName("sell_count") var sell_count : Int
)