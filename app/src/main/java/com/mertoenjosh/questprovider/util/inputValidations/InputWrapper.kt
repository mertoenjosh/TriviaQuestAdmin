package com.mertoenjosh.questprovider.util.inputValidations

import android.os.Parcel
import android.os.Parcelable


data class InputWrapper(
    val value: String = "",
    val errorId: Int? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(value)
        parcel.writeValue(errorId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InputWrapper> {
        override fun createFromParcel(parcel: Parcel): InputWrapper {
            return InputWrapper(parcel)
        }

        override fun newArray(size: Int): Array<InputWrapper?> {
            return arrayOfNulls(size)
        }
    }
}