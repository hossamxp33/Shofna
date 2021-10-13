package com.example.shofna.datalayer


import com.example.shofna.model.Details
import com.example.shofna.model.LoginModel
import com.example.shofna.model.MainView
import io.reactivex.Observable
import retrofit2.http.*


interface APIServices {





    @GET("items/homepage.json")
    fun GetMainData(): Observable<MainView?>?

    @GET("items/view/{item_id}/1.json")
    fun GetNewsDetails( @Path("item_id") id: Int): Observable<Details?>?


    @FormUrlEncoded
    @POST("users/add.json")
    fun userregister(
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("username") repassowrd: String?
    ): Observable<LoginModel?>?

    @FormUrlEncoded
    @POST("users/token.json")
    abstract fun userlogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<LoginModel>





}


