package com.example.recyclerretrofitinesmr.domain

import android.os.Parcel
import android.os.Parcelable
import com.example.recyclerretrofitinesmr.utils.Constants
import java.time.LocalDate



data class Director (
    val id: Int,
    var nombre: String,
    val nacimiento: LocalDate,
    var isSelected: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: Constants.EMPTY,
        LocalDate.parse(parcel.readString() ?: Constants.EMPTY),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(nacimiento.toString())
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Director> {
        override fun createFromParcel(parcel: Parcel): Director {
            return Director(parcel)
        }

        override fun newArray(size: Int): Array<Director?> {
            return arrayOfNulls(size)
        }
    }
}