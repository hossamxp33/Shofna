package com.example.shofna.DataRepo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData

import io.reactivex.schedulers.Schedulers
import android.content.Context
import com.example.shofna.datalayer.APIServices
import com.example.shofna.datalayer.ApiClient
import com.example.shofna.model.MainView
import io.reactivex.android.schedulers.AndroidSchedulers


class DataRepo {

    @SuppressLint("CheckResult")
    fun GetMainData(context: Context, livedata: MutableLiveData<MainView>?) {
        getServergetway().GetMainData()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
           ?.map { data -> data }
            ?.subscribe(
                { books ->
                livedata?.postValue(books)

                },
                { error ->

                }
            )
    }






    fun getServergetway(): APIServices {
        return ApiClient.client!!.create(APIServices::class.java)
    }
}

