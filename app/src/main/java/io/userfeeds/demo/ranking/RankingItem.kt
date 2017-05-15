package io.userfeeds.demo.ranking

import android.os.Parcel
import android.os.Parcelable

data class RankingItem(
        val score: Double,
        val value: String)
    :
        Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeDouble(score)
        dest.writeString(value)
    }

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<RankingItem> {

            override fun createFromParcel(source: Parcel) = RankingItem(
                    source.readDouble(),
                    source.readString()
            )

            override fun newArray(size: Int) = arrayOfNulls<RankingItem>(size)
        }
    }
}
