package com.example.shofna.presentaion.homefragment.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField


class BindableString : BaseObservable() {

     var value = ObservableField<Int>();
     var visibility = ObservableField<Int>();
     var downloaded_text_visibility = ObservableField<Int>();

init {
    visibility.set(8)
    downloaded_text_visibility.set(8)

}


}