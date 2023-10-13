package com.example.mobileapptest.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName

data class ItemFormatImage(
    @SerializedName("ext")
    var ext:String?,
    @SerializedName("url")
    var url:String?,
    @SerializedName("hash")
    var hash:String?,
    @SerializedName("mime")
    var mime:String?,
    @SerializedName("name")
    var name:String?,
    @SerializedName("size")
    var size:Double?,
    @SerializedName("width")
    var width:Int?,
    @SerializedName("height")
    var height:Int?,
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<ItemFormatImage> {
            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun createFromParcel(parcel: Parcel) = ItemFormatImage(parcel)
            override fun newArray(size: Int) = arrayOfNulls<ItemFormatImage>(size)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private constructor(parcel: Parcel) : this(
        ext = parcel.readString(),
        url = parcel.readString(),
        hash = parcel.readString(),
        mime = parcel.readString(),
        name = parcel.readString(),
        size = parcel.readDouble(),
        width = parcel.readInt(),
        height = parcel.readInt(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ext)
        parcel.writeString(url)
        parcel.writeString(hash)
        parcel.writeString(mime)
        parcel.writeString(name)
        parcel.writeDouble(size!!)
        parcel.writeInt(width!!)
        parcel.writeInt(height!!)
    }

    override fun describeContents() = 0
}