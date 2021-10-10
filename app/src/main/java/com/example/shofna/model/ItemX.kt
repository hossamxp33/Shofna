package com.example.shofna.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemX(
    var description: String,
    var id: Int,
    var ishowen: Int,
    var name: String,
    var created: String,
    var photo: String,
    var price: Int,
    var subcategory_id: Int
): Parcelable {

}