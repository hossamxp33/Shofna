package com.example.shofna.datalayer


import com.example.shofna.model.MainView
import io.reactivex.Observable

import retrofit2.http.GET


interface APIServices {



    @GET("items/homepage.json")
    fun GetMainData(): Observable<MainView?>?

}


