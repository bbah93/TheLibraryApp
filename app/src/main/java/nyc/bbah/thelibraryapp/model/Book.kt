package nyc.bbah.thelibraryapp.model

import android.os.Parcel
import android.os.Parcelable

data class Book(val author: String?,
                val title: String?,
                val category: String?,
                val publisher: String?,
                val url: String?,
                val id: Int?,
                val lastCheckedOut: String?,
                val lastCheckoutBy: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(category)
        parcel.writeString(publisher)
        parcel.writeString(url)
        parcel.writeValue(id)
        parcel.writeString(lastCheckedOut)
        parcel.writeString(lastCheckoutBy)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}

