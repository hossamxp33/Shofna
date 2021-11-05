package com.example.shofna.DataRepo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData

import io.reactivex.schedulers.Schedulers
import android.content.Context
import android.provider.ContactsContract
import com.example.shofna.datalayer.APIServices
import com.example.shofna.datalayer.ApiClient
import com.example.shofna.helper.PreferenceHelper
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


    //getUserData
    @SuppressLint("CheckResult")
    fun getUserData(userid: Int, livedata: MutableLiveData<LoginModel>?) {
        getServergetway().getUserData(userid).subscribeOn(Schedulers.io())
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
    //editUserData
    @SuppressLint("CheckResult")
    fun editUserData(userid: Int,username: String?, mobile: String?, email: String?,
        livedata: MutableLiveData<EditUserData>?,
        errorLivedat: MutableLiveData<String>,
        loadingLivedat: MutableLiveData<Boolean>
    ) {
        getServergetway().editUserData(userid, username, mobile, email)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.map { data -> data }
            ?.subscribe(
                { books ->
                    livedata?.postValue(books)
                    loadingLivedat.postValue(false)
                },
                { error ->
                    errorLivedat.postValue(error.toString());loadingLivedat.postValue(false)

                }
            )
    }

    @SuppressLint("CheckResult")
    fun Register(username: String, mobile:  String?, email: String?, password: String,

        livedata: MutableLiveData<LoginModel>?,
        errorLiveData: MutableLiveData<String>,
        loadingLivedata: MutableLiveData<Boolean>
    ) {

        getServergetway().userregister(username ,mobile,email, password)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.map { data -> data }
            ?.subscribe(
                { data ->
                    livedata?.postValue(data)
                    loadingLivedata.postValue(false)

                }, {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }
            )

    }

    @SuppressLint("CheckResult")

    fun ChangePassword(password:String,livedata: MutableLiveData<EditUserData>?) {

        getServergetway().ChangePassword(password, PreferenceHelper.getUserId().toLong())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->

                }
            )
    }

//forgotPassword
@SuppressLint("CheckResult")
    fun forgotPassword(email:String,livedata: MutableLiveData<ForgetPasswordData>?) {

        getServergetway().forgotPassword(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->

                }
            )
    }
    @SuppressLint("CheckResult")
    fun GetNotifications(livedata: MutableLiveData<List<Data>>?) {

        getServergetway().GetNotifications().subscribeOn(Schedulers.io())
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
    //OpenNotifications

    @SuppressLint("CheckResult")
    fun OpenNotifications(news_id: Int, livedata: MutableLiveData<ReciveNotification>?) {

        getServergetway().OpenNotifications(news_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->

                    livedata?.postValue(books)

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

