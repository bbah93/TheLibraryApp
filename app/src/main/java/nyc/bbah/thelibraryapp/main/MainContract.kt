package nyc.bbah.thelibraryapp.main

import nyc.bbah.thelibraryapp.model.Book
import retrofit2.Call

interface MainContract {

    //inner interface for call to server used in main
    interface Network{
        fun bookListApiCall(onSuccess: (List<Book>) -> Unit): Call<List<Book>>
        //fun checkoutApiCall(book: Book): Call<Book>
        fun delete(id: Int): Call<Unit>
    }

    interface RecyclerOnClickListener {
        fun onItemClick(book: Book)
    }
}