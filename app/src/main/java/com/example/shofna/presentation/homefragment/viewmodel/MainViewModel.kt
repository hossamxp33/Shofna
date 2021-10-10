package com.example.shofna.presentation.homefragment.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shofna.DataRepo.DataRepo
import com.example.shofna.model.Details
import com.example.shofna.model.MainView

import io.reactivex.disposables.CompositeDisposable


class MainViewModel : ViewModel(){

    var DateRepoCompnay: DataRepo = DataRepo()
    var mCompositeDisposable = CompositeDisposable()

    var errorLivedat: MutableLiveData<String> = MutableLiveData()

    var loadingLivedat: MutableLiveData<Boolean> = MutableLiveData()

    var MainDataLD: MutableLiveData<MainView>? = null
    var DetailsDataLD: MutableLiveData<Details>? = null


    var ItemIndex = MutableLiveData<Int>()
    var title = MutableLiveData<String>()

    var visibility = ObservableField<Int>();

    init {
        errorLivedat = MutableLiveData()
        loadingLivedat = MutableLiveData()
        visibility.set(8)

        MainDataLD = MutableLiveData()

        DetailsDataLD= MutableLiveData()

        ItemIndex = MutableLiveData<Int>()
        title = MutableLiveData<String>()
    }


    fun GetMainData(context: Context) {
        DateRepoCompnay.GetMainData(context,MainDataLD)

    }


    fun GetNewsDetails(item_id:Int) {
        DateRepoCompnay.GetNewsDetails(item_id,DetailsDataLD)

    }



    fun SwtichingCategories(index: Int) {
        ItemIndex.postValue(index)
    }

    fun SetRelatedIndex(index: Int) {
        ItemIndex.postValue(index)
    }
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
        mCompositeDisposable.clear()

    }
}