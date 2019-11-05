package com.simpleapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var email :String = "",
    var password: String = "",
    var name: String = "",
    var gender: String = "",
    var phone: String = ""
) : Parcelable {
}