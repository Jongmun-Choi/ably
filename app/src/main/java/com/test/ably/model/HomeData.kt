package com.test.ably.model

import com.google.gson.annotations.SerializedName

data class HomeData(
 @SerializedName("banners") val banners : List<Banner>,
 @SerializedName("goods") val goods : List<Goods>
)