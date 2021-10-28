package com.example.shofna.datalayer


import com.example.shofna.model.*
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


    @GET("notifications/index.json")/*{company_id}*/
    fun GetNotifications():
            Observable<Notification>



    //https://shofna.codesroots.com/api/notifications/View/1383.json

    @GET("notifications/View/{news_id}.json")
    fun OpenNotifications( @Path("news_id") id: Int):
            Observable<ReciveNotification>


}


