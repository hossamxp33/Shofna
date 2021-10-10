package com.example.shofna.datalayer


import com.example.shofna.model.Details
import com.example.shofna.model.MainView
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Path


interface APIServices {





    @GET("items/homepage.json")
    fun GetMainData(): Observable<MainView?>?

    @GET("items/view/{item_id}/1.json")
    fun GetNewsDetails( @Path("item_id") id: Int): Observable<Details?>?








}


