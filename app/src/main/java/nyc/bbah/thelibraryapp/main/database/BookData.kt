package nyc.bbah.thelibraryapp.main.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "bookData")
data class BookData(@PrimaryKey(autoGenerate = true)
                     val uId: Long?,
                    val author: String?,
                    val title: String?,
                    val category: String?,
                    val publisher: String?,
                    val url: String?,
                    val id: Int?,
                    val lastCheckedOut: String?,
                    val lastCheckoutBy: String?) {

    constructor():this(null,"","","","","",
            0,"","")
}