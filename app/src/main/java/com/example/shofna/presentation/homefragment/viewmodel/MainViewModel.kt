package com.example.shofna.presentation.homefragment.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shofna.DataRepo.DataRepo
import com.example.shofna.model.MainView

import io.reactivex.disposables.CompositeDisposable


class MainViewModel : ViewModel(){

    var DateRepoCompnay: DataRepo = DataRepo()
    var mCompositeDisposable = CompositeDisposable()

    var errorLivedat: MutableLiveData<String> = MutableLiveData()

    var loadingLivedat: MutableLiveData<Boolean> = MutableLiveData()

    var MainDataLD: MutableLiveData<MainView>? = null
    var ItemIndex = MutableLiveData<Int>()
    var visibility = ObservableField<Int>();

    init {
        errorLivedat = MutableLiveData()
        loadingLivedat = MutableLiveData()
        visibility.set(8)

        MainDataLD = MutableLiveData()
        ItemIndex = MutableLiveData<Int>()
    }


    fun GetMainData(context: Context) {
        DateRepoCompnay.GetMainData(context,MainDataLD)

    }

    fun SwtichingCategories(index: Int) {
        ItemIndex.postValue(index)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
        mCompositeDisposable.clear()

    }
}