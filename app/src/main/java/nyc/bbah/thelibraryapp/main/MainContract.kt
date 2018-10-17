package nyc.bbah.thelibraryapp.main

import nyc.bbah.thelibraryapp.model.Book
import retrofit2.Call

interface MainContract {

    //inner interface for call to server used in main
    interface Network{
        fun bookListApiCall(onSuccess: (List<Book>) -> Unit): Call<List<Book>>

        fun checkout(id: Int, onSuccess: (Book) -> Unit): Call<Book>

        fun delete(id: Int, onSuccess: (Unit) -> Unit): Call<Unit>

        fun deleteAll(onSuccess: (List<Book>) -> Unit): Call<Unit>

        fun getBook(id: Int, onSuccess: (List<Book>) -> Unit): Call<Book>?

        fun addBook(book: Book, onSuccess: (Book) -> Unit): Call<Book>
    }

    interface RecyclerOnClickListener {
        fun onItemClick(book: Book)
    }


}