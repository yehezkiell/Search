package com.example.yehezkiel.cermati.Network

import com.example.yehezkiel.cermati.Model.UsersX
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Yehezkiel on 1/25/2019.
 */
interface RetrofitInstance {

    @GET("search/users")
    fun getUser(@Query("q") q:String,
                @Query("per_page") per_page:Int)
    : Observable<UsersX>

}