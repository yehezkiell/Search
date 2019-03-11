package com.example.yehezkiel.cermati.Model

import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("login")
        open var login: String,

        @SerializedName("avatar_url")
        val avatar_url: String

)