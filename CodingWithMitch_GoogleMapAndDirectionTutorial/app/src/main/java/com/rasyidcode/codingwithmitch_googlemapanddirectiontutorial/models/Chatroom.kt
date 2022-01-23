package com.rasyidcode.codingwithmitch_googlemapanddirectiontutorial.models

import android.os.Parcel
import android.os.Parcelable

data class Chatroom(
    var title: String? = null,
    var chatroomId: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(title)
        p0?.writeString(chatroomId)
    }

    override fun toString(): String {
        return "Chatroom{title=$title, chatroom_id=$chatroomId}"
    }

    companion object CREATOR : Parcelable.Creator<Chatroom> {
        override fun createFromParcel(parcel: Parcel): Chatroom {
            return Chatroom(parcel)
        }

        override fun newArray(size: Int): Array<Chatroom?> {
            return arrayOfNulls(size)
        }
    }

}