package com.example.recyclerretrofitinesmr.domain

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

data class Pelicula (
    val id: Int,
    val titulo: String,
    val fecha: LocalDate,
    val idDirector: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        LocalDate.parse(parcel.readString() ?: ""),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(titulo)
        parcel.writeString(fecha.toString())
        parcel.writeInt(idDirector)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pelicula> {
        override fun createFromParcel(parcel: Parcel): Pelicula {
            return Pelicula(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula?> {
            return arrayOfNulls(size)
        }
    }
}
