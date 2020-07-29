package com.vandana.gojekgitapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// data class to store trending git repository data coming from url

data class TrendingRepositoryData(

    @SerializedName("author")
    @Expose
    var author: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("avatar")
    @Expose
    var avatar:String? = null,
    @SerializedName("url")
    @Expose
    var url:String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("language")
    @Expose
    var language:String? = null,
    @SerializedName("languageColor")
    @Expose
    var languageColor:String? = null,
    @SerializedName("stars")
    @Expose
    var stars:Int = 0,
    @SerializedName("forks")
    @Expose
    var forks: Int =0,
    @SerializedName("currentPeriodStars")
    @Expose
    var currentPeriodStars: Int =0,
    @SerializedName("builtBy")
    @Expose
    var builtBy:List<BuiltBy>? = null
)
{
    data class BuiltBy (
        @SerializedName("href")
        @Expose
         var href: String? = null,

        @SerializedName("avatar")
        @Expose
         var avatar: String? = null,

        @SerializedName("username")
        @Expose
         var username: String? = null

    )
}
