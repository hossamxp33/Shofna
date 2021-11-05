package com.example.shofna.datalayer


import com.example.shofna.model.*
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.POST

import retrofit2.http.FormUrlEncoded





interface APIServices {





    @GET("items/homepage.json")
    fun GetMainData(): Observable<MainView?>?

    @GET("items/view/{item_id}/1.json")
    fun GetNewsDetails( @Path("item_id") id: Int): Observable<Details?>?

    @FormUrlEncoded
    @POST("users/edit/{userid}.json") ///users/edit/1
    fun editUserData(
        @Path("userid") userid: Int,
        @Field("username") username: String?,
        @Field("mobile") mobile: String?,
        @Field("email") email: String?,
    ): Observable<EditUserData?>?


    @FormUrlEncoded
    @POST("users/add.json")
    fun userregister(
        @Field("username") username: String?,
        @Field("mobile") mobile: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Observable<LoginModel?>?


    @FormUrlEncoded
    @POST("users/changepassword/{id}.json")/*{company_id}*/
    fun ChangePassword(
        @Field("password") password: String,
        @Path("id") user_id: Long):
            Observable<EditUserData>


    @FormUrlEncoded
    @POST("Users/forgotpassword.json")
    fun forgotPassword(@Field("email") password: String):
            Observable<ForgetPasswordData>



    //  users/view/1093.json

    @GET(" users/view/{user_id}.json")
    fun getUserData( @Path("user_id") id: Int):
            Observable<LoginModel>


    @FormUrlEncoded
    @POST("users/token.json")
    abstract fun userlogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<LoginModel>


    @GET("notifications/index.json")/*{company_id}*/
    fun GetNotifications():
            Observable<Notification>



    //https://shofna.codesroots.com/api/notifications/View/1383.json

    @GET("notifications/View/{news_id}.json")
    fun OpenNotifications( @Path("news_id") id: Int):
            Observable<ReciveNotification>


}


