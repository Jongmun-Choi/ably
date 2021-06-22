package com.test.ably.model

import com.google.gson.annotations.SerializedName

data class Goods(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("image") val image : String,
    @SerializedName("actual_price") val actual_price : Int,
    @SerializedName("price") val price : Int,
    @SerializedName("is_new") val is_new : Boolean,
    @SerializedName("sell_count") val sell_count : Int,
    val like: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if(other is Goods) {
            return this.id == other.id && this.name.equals(other.name) && this.image.equals(other.image) &&
                    this.actual_price == other.actual_price && this.price == other.price && this.sell_count == other.sell_count &&
                    this.is_new == other.is_new && this.like == other.like
        }else {
            return false
        }
    }
}