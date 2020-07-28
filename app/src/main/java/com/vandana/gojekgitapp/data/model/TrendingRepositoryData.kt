package com.vandana.gojekgitapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// data class to store trending git repository data coming from url

data class TrendingRepositoryData (

    @SerializedName("username")
    @Expose
    var username: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null,
    @SerializedName("repo")
    @Expose
    var repo: Repo? = null,

    @SerializedName("sponsorUrl")
    @Expose
    var  sponsorUrl:String? =null
)
{
    inner class Repo {
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("url")
        @Expose
        var url: String? = null

    }
}
