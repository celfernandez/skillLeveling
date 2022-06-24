package com.example.chalengejobreadiness.responses.ItemResponse

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Body (
    @SerializedName("title") val title : String,
    @SerializedName("price") val price : Float,
    @SerializedName("secure_thumbnail") val picture :  String,
    @SerializedName("condition") val condition : String,
    @SerializedName("id") val id : String
    //@SerializedName("seller_address") val sellerAddressObject: SellerAddressObject
):Parcelable
