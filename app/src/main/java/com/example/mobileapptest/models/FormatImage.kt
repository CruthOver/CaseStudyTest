package com.example.mobileapptest.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName

data class FormatImage(
    @SerializedName("small")
    var small:ItemFormatImage?,
    @SerializedName("medium")
    var medium:ItemFormatImage?,
    @SerializedName("thumbnail")
    var thumbnail:ItemFormatImage?,
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<FormatImage> {
            override fun createFromParcel(parcel: Parcel) = FormatImage(parcel)
            override fun newArray(size: Int) = arrayOfNulls<FormatImage>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        small = parcel.readParcelable(ItemFormatImage::class.java.classLoader),
        medium = parcel.readParcelable(ItemFormatImage::class.java.classLoader),
        thumbnail = parcel.readParcelable(ItemFormatImage::class.java.classLoader),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(small, flags)
        parcel.writeParcelable(medium, flags)
        parcel.writeParcelable(thumbnail, flags)
    }

    override fun describeContents() = 0
}