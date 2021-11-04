package com.example.shofna.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemX(
    var name: String?=null,
    var description: String?=null,
    var id: Int?=null,
    var ishowen: Int?=null,
    var link : String?=null,
    var created: String?=null,
    var photo: String?=null,
    var price: Int?=null,
    var subcategory_id: Int?=null
): Parcelable {

}