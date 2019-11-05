package com.simpleapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
    var images: String = "",
    var listImage : List<String> = emptyList()
): Parcelable {}