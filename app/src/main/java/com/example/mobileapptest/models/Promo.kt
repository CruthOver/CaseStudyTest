package com.example.mobileapptest.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName

data class Promo(
    @SerializedName("id")
    var id:Long?,
    @SerializedName("nama")
    var name:String?,
    @SerializedName("desc")
    var desc:String?,
    @SerializedName("latitude")
    var latitude:String?,
    @SerializedName("longitude")
    var longitude:String?,
    @SerializedName("alt")
    var alt:String?,
    @SerializedName("lokasi")
    var location:String?,
    @SerializedName("count")
    var count:Int?,
    @SerializedName("img")
    var image:ImagePromo?,
    @SerializedName("published_at")
    var publishedAt:String?,
    @SerializedName("created_at")
    var createdAt:String?,
    @SerializedName("updated_at")
    var updatedAt:String?,
) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Promo> {
            override fun createFromParcel(parcel: Parcel) = Promo(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Promo>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        name = parcel.readString(),
        desc = parcel.readString(),
        latitude = parcel.readString(),
        longitude = parcel.readString(),
        alt = parcel.readString(),
        location = parcel.readString(),
        count = parcel.readInt(),
        publishedAt = parcel.readString(),
        createdAt = parcel.readString(),
        updatedAt = parcel.readString(),
        image = parcel.readParcelable(ImagePromo::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id!!)
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(alt)
        parcel.writeString(location)
        parcel.writeInt(count!!)
        parcel.writeString(publishedAt)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeParcelable(image, flags)
    }

    override fun describeContents() = 0
}