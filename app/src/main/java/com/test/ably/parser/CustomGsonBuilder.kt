package com.test.ably.parser

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.test.ably.model.Banner
import com.test.ably.model.Goods
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

class CustomGsonBuilder {

    fun getCustomConverter() : Converter.Factory {
        return GsonConverterFactory.create(getGsonBuilder())
    }

    fun getGsonBuilder() : Gson {
        val builder = GsonBuilder()

        builder.registerTypeAdapter(object : TypeToken<MutableMap<String, List<Any>>>() {}.type, JsonDeserializer<MutableMap<String, List<Any>>> { json, _, _ ->
            val resultData = mutableMapOf<String, List<Any>>()
            val bannerList = arrayListOf<Banner>()
            val goodsList = arrayListOf<Goods>()
            if(json.asJsonObject.isJsonNull == false) {
                if(json.asJsonObject["banners"].isJsonNull == false) {
                    var banners = json.asJsonObject["banners"].asJsonArray
                    for (banner in banners) {
                        bannerList.add(
                            getGsonBuilder().fromJson(
                                banner.asJsonObject.toString(),
                                Banner::class.java
                            )
                        )
                    }
                }
                if(json.asJsonObject["goods"].isJsonNull == false) {
                    var goods = json.asJsonObject["goods"].asJsonArray
                    for (item in goods) {
                        bannerList.add(
                            getGsonBuilder().fromJson(
                                item.asJsonObject.toString(),
                                Banner::class.java
                            )
                        )
                    }
                }
            }
            resultData
        })

        builder.registerTypeAdapter(Banner::class.java, JsonDeserializer( {json, _, _ ->
            var json_data = json.asJsonObject
            Banner(json_data.optInt("id"), json_data.opt("image"))
        }))

//        builder.registerTypeAdapter(Goods::class.java, JsonDeserializer( {json, _, _ ->
//            var json_data = json.asJsonObject
//            Goods(json_data.optInt("id"), json_data.opt("name"), json_data.opt("image"),
//                json_data.optInt("actual_price"), json_data)
//        }))
        return builder.create()
    }

    private fun JsonObject.opt(key: String, fallBack: String = ""): String {
        return if (!this.has(key) || this[key].isJsonNull) {
            fallBack
        } else {
            this[key].asString
        }
    }

    private fun JsonObject.optInt(key: String, fallBack: Int = 0): Int {
        return if (!this.has(key) || this[key].isJsonNull) {
            fallBack
        } else {
            try {
                this[key].asInt
            } catch (e: NumberFormatException) {
                0
            }
        }
    }
}