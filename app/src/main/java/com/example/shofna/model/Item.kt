package com.example.shofna.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    var created: String,
    var id: Int,
    var items: List<ItemX>,
    var modified: String,
    var name: String,
    var link :String,
    var photo: String,
    var type: String
)  : Parcelable {

}