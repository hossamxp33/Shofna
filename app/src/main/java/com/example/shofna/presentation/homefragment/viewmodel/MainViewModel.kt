package com.example.shofna.presentation.homefragment.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shofna.DataRepo.DataRepo
import com.example.shofna.model.*

import io.reactivex.disposables.CompositeDisposable


class MainViewModel : ViewModel(){

    var DateRepoCompnay: DataRepo = DataRepo()
    var mCompositeDisposable = CompositeDisposable()

    var errorLivedat: MutableLiveData<String> = MutableLiveData()

    var loadingLivedat: MutableLiveData<Boolean> = MutableLiveData()

    var MainDataLD: MutableLiveData<MainView>? = null
    var DetailsDataLD: MutableLiveData<Details>? = null
    var LoginDataLD: MutableLiveData<LoginData>? = null
    var RegisterDataLD: MutableLiveData<LoginData>? = null
    var NotificationLD : MutableLiveData<List<Data>>? = null


    var ItemIndex = MutableLiveData<Int>()
    var title = MutableLiveData<String>()

    var visibility = ObservableField<Int>();

    init {
        errorLivedat = MutableLiveData()
        loadingLivedat = MutableLiveData()
        visibility.set(8)

        MainDataLD = MutableLiveData()

        DetailsDataLD= MutableLiveData()
        LoginDataLD= MutableLiveData()
        RegisterDataLD = MutableLiveData()
        NotificationLD= MutableLiveData()
        ItemIndex = MutableLiveData<Int>()
        title = MutableLiveData<String>()
    }


    fun GetMainData(context: Context) {
        DateRepoCompnay.GetMainData(context,MainDataLD)

    }


    fun GetNewsDetails(item_id:Int) {
        DateRepoCompnay.GetNewsDetails(item_id,DetailsDataLD)

    }

    fun GetLoginData(username:String,password:String) {

        DateRepoCompnay.Login(username,password,LoginDataLD,errorLivedat,loadingLivedat)

    }


    fun GetRegisterData(username:String,password:String,repassword:String) {

        DateRepoCompnay.Register(username,password,repassword,LoginDataLD,errorLivedat,loadingLivedat)

    }

    fun GetNotifications(){
        DateRepoCompnay.GetNotifications(NotificationLD)

    }

    fun addUser(user: User) {

            DateRepoCompnay.registerUser(user)

    }





    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
        mCompositeDisposable.clear()

    }
}