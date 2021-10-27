package com.example.shofna.DataRepo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData

import io.reactivex.schedulers.Schedulers
import android.content.Context
import android.util.Log
import com.example.shofna.datalayer.APIServices
import com.example.shofna.datalayer.ApiClient
import com.example.shofna.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


class DataRepo {
    private val mCompositeDisposable = CompositeDisposable()
    var registerMutableLiveData = MutableLiveData<LoginModel>()

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


    @SuppressLint("CheckResult")
    fun GetNewsDetails(item_id: Int, livedata: MutableLiveData<Details>?) {
        getServergetway().GetNewsDetails(item_id)?.subscribeOn(Schedulers.io())
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

    @SuppressLint("CheckResult")
    fun Login(
        username: String,
        password: String,
        livedata: MutableLiveData<LoginData>?,
        errorLiveData: MutableLiveData<String>,
        loadingLivedata: MutableLiveData<Boolean>
    ) {

        getServergetway().userlogin(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { data ->
                    livedata?.postValue(data.data)
                    loadingLivedata.postValue(false)

                }, {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }
            )

    }

    @SuppressLint("CheckResult")
    fun Register(
        username: String,
        password: String,
        repassword: String,
        livedata: MutableLiveData<LoginData>?,
        errorLiveData: MutableLiveData<String>,
        loadingLivedata: MutableLiveData<Boolean>
    ) {

        getServergetway().userregister(username, password,repassword)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.map { data -> data }
            ?.subscribe(
                { data ->
                    livedata?.postValue(data.data)
                    loadingLivedata.postValue(false)

                }, {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }
            )

    }

     fun registerUser(user: User) {
        getServergetway().userregister(
            user.username,
            user.password,
            user.repassword
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.let {
                mCompositeDisposable.add(
                it
                    ?.subscribe({ register: LoginModel -> postDataResponseForRegister(register) } as ((LoginModel?) -> Unit)?) { throwable: Throwable ->
                        error(
                            throwable
                        )
                    })
            }
    }


    @SuppressLint("CheckResult")
    fun GetNotifications(livedata: MutableLiveData<List<Data>>?) {

        getServergetway().GetNotifications() .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->

                    livedata?.postValue(books.data)

                },
                {
                }
            )

    }
    private fun postDataResponseForRegister(register: LoginModel) {
        registerMutableLiveData.postValue(register)
    }

    fun getServergetway(): APIServices {
        return ApiClient.client!!.create(APIServices::class.java)
    }
}

