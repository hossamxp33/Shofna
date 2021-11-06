package com.example.shofna.presentation.homefragment.viewmodel

import android.content.Context
import android.text.Editable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shofna.DataRepo.DataRepo
import com.example.shofna.model.*
import com.example.shofna.presentation.registerActivity.loginfragment.ForgotPasswordActivity

import io.reactivex.disposables.CompositeDisposable


class MainViewModel : ViewModel(){

    var DateRepoCompnay: DataRepo = DataRepo()
    var mCompositeDisposable = CompositeDisposable()

    var processVisibility = MutableLiveData(false)
    var errorLivedat: MutableLiveData<String> = MutableLiveData()

    var loadingLivedat: MutableLiveData<Boolean> = MutableLiveData()

    var MainDataLD: MutableLiveData<MainView>? = null
    var DetailsDataLD: MutableLiveData<Details>? = null

    var LoginDataLD: MutableLiveData<LoginData>? = null

    var EditDataLD: MutableLiveData<EditUserData>? = null

    var ForgotPasswordLD: MutableLiveData<ForgetPasswordData>? = null

    var RegisterDataLD: MutableLiveData<LoginModel>? = null

    var NotificationLD : MutableLiveData<List<Data>>? = null

    var oPenNotificationLD : MutableLiveData<ReciveNotification>? = null

    var ItemIndex = MutableLiveData<Int>()

    var title = MutableLiveData<String>()

    var visibility = ObservableField<Int>();

    init {

        processVisibility = MutableLiveData(false)
        errorLivedat = MutableLiveData()
        loadingLivedat = MutableLiveData()
        visibility.set(8)
        MainDataLD = MutableLiveData()
        DetailsDataLD= MutableLiveData()
        LoginDataLD= MutableLiveData()
        EditDataLD= MutableLiveData()
        ForgotPasswordLD= MutableLiveData()
        RegisterDataLD = MutableLiveData()
        NotificationLD= MutableLiveData()
        oPenNotificationLD = MutableLiveData()
        ItemIndex = MutableLiveData<Int>()
        title = MutableLiveData<String>()
    }


    fun GetMainData(context: Context) {
        DateRepoCompnay.GetMainData(context,MainDataLD)

    }

//getUserData
fun getUserData(userid:Int) {
    DateRepoCompnay.getUserData(userid,RegisterDataLD)

}

    fun GetNewsDetails(item_id:Int) {
        DateRepoCompnay.GetNewsDetails(item_id,DetailsDataLD)

    }

    fun GetLoginData(username:String,password:String) {

        DateRepoCompnay.Login(username,password,LoginDataLD,errorLivedat,loadingLivedat)

    }

    //editUserData

    fun editUserData(userid: Int, username: String?, mobile: String?, email: String?) {

        DateRepoCompnay.editUserData(userid,username,mobile,email,EditDataLD,errorLivedat,loadingLivedat)

    }

    fun GetRegisterData(username: String,
                        mobile: String?,
                        email: String?,
                        password: String) {

        DateRepoCompnay.Register(username,mobile,email,password,RegisterDataLD,errorLivedat,loadingLivedat)

    }

    fun ChangePassword(password:String){
        DateRepoCompnay.ChangePassword(password,EditDataLD)
    }

    fun forgotPassword(email: String?){
        DateRepoCompnay.forgotPassword(email!!,ForgotPasswordLD)
    }
    fun GetNotifications(){
        DateRepoCompnay.GetNotifications(NotificationLD)

    }

    //OpenNotifications
    fun OpenNotifications(news_id:Int){
        DateRepoCompnay.OpenNotifications(news_id,oPenNotificationLD)

    }







    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
        mCompositeDisposable.clear()

    }
}