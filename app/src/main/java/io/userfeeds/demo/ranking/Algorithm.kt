package io.userfeeds.demo.ranking

import android.os.Parcel
import android.os.Parcelable

data class Algorithm(
        val identifier: String,
        val description: String)
    :
        Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(identifier)
        dest.writeString(description)
    }

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<Algorithm> {

            override fun createFromParcel(source: Parcel) = Algorithm(
                    source.readString(),
                    source.readString()
            )

            override fun newArray(size: Int) = arrayOfNulls<Algorithm>(size)
        }
    }
}
