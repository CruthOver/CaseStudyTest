package com.example.mobileapptest.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName

data class ImagePromo (
    @SerializedName("id")
    var id:Long?,
    @SerializedName("name")
    var name:String?,
    @SerializedName("alternativeText")
    var alternativeText:String?,
    @SerializedName("caption")
    var caption:String?,
    @SerializedName("width")
    var width:Int?,
    @SerializedName("height")
    var height:Int?,
    @SerializedName("hash")
    var hash:String?,
    @SerializedName("ext")
    var ext:String?,
    @SerializedName("mime")
    var mime:String?,
    @SerializedName("size")
    var size:Double?,
    @SerializedName("url")
    var url:String?,
    @SerializedName("provider")
    var provider:String?,
    @SerializedName("created_at")
    var createdAt:String?,
    @SerializedName("updated_at")
    var updatedAt:String?,
    @SerializedName("formats")
    var formats:FormatImage?
): Parcelable {
    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<ImagePromo> {
            override fun createFromParcel(parcel: Parcel) = ImagePromo(parcel)
            override fun newArray(size: Int) = arrayOfNulls<ImagePromo>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        name = parcel.readString(),
        alternativeText = parcel.readString(),
        caption = parcel.readString(),
        width = parcel.readInt(),
        height = parcel.readInt(),
        hash = parcel.readString(),
        ext = parcel.readString(),
        mime = parcel.readString(),
        size = parcel.readDouble(),
        url = parcel.readString(),
        provider = parcel.readString(),
        createdAt = parcel.readString(),
        updatedAt = parcel.readString(),
        formats = parcel.readParcelable(FormatImage::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id!!)
        parcel.writeString(name)
        parcel.writeString(alternativeText)
        parcel.writeString(caption)
        parcel.writeInt(width!!)
        parcel.writeInt(height!!)
        parcel.writeString(hash)
        parcel.writeString(ext)
        parcel.writeString(mime)
        parcel.writeDouble(size!!)
        parcel.writeString(url)
        parcel.writeString(provider)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeParcelable(formats, flags)
    }

    override fun describeContents() = 0
}

