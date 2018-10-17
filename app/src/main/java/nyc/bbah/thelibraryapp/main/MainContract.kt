package nyc.bbah.thelibraryapp.main

import nyc.bbah.thelibraryapp.model.Book
import retrofit2.Call

interface MainContract {

    //inner interface for call to server used in main
    interface Network{
        fun bookListApiCall(onSuccess: (List<Book>) -> Unit): Call<List<Book>>

        //fun checkout(onSuccess: (Book) -> Book): Call<Book>

        fun delete(id: Int): Call<Unit>

        fun deleteAll(): Call<Unit>

        fun getBook(id: Int): Call<Book>?

        fun addBook(book: Book): Call<Book>
    }

    interface RecyclerOnClickListener {
        fun onItemClick(book: Book)
    }


}