package com.example.shofna.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    var created: String?=null,
    var id: Int?=null,
    var items: List<ItemX>?=null,
    var modified: String?=null,
    var name: String?=null,
    var link :String?=null,
    var photo: String?=null,
    var type: String?=null,
    var description: String?=null,
    var ishowen: Int?=null,
    var price: Int?=null,
    var subcategory_id: Int?=null,
)  : Parcelable {

}