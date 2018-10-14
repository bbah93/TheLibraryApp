package nyc.bbah.thelibraryapp.model

data class Books(val author: String,
                 val title: String,
                 val category: String?,
                 val publisher: String,
                 val url: String,
                 val id: Int,
                 val lastCheckedOut: String,
                 val lastCheckoutBy: String?)
