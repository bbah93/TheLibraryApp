package nyc.bbah.thelibraryapp.main


import android.util.Log
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainCall(private val bookService: BooksService) : MainContract.Network {


    override fun checkout(id: Int, onSuccess: (Book) -> Unit): Call<Book> {
        val book : Book = Book("", "", "", "", "", 22, "", "")
        val call: Call<Book> = bookService.updateBook(id, book)
        call.enqueue(object : Callback<Book>{
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                val data : Book? = response.body()
                data?.let(onSuccess)
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    override fun delete(id: Int, onSuccess: (Unit) -> Unit): Call<Unit> {
        val call: Call<Unit> = bookService.deleteBook(id)

        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val data : Unit? = response.body()
                data?.let(onSuccess)
            }
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    override fun addBook(book: Book, onSuccess: (Book) -> Unit): Call<Book> {
        val call: Call<Book> = bookService.addBook(book)
        call.enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                val data : Book? = response.body()
                data?.let(onSuccess)
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    override fun getBook(id: Int, onSuccess: (List<Book>) -> Unit): Call<Book> {
        val call: Call<Book> = bookService.getABook(id)

        call.enqueue(object : Callback<Book>{
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                val data : Book? = response.body()
                Log.d("GET BOOK CHECK: ", data.toString())

            }
            override fun onFailure(call: Call<Book>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    //make network call to get List of Books. super from contract
    override fun bookListApiCall(onSuccess: (List<Book>) -> Unit): Call<List<Book>> {
        val call = bookService.getBooks()

        call.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                val data = response.body()
                data?.let(onSuccess)
                Log.d("DELETE CHECK: ", response.raw().toString())
            }
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    override fun deleteAll(onSuccess: (Unit) -> Unit): Call<Unit> {
        val call: Call<Unit> = bookService.deleteAllBooks()

        call.enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            val data: Unit? = response.body()
                data?.let(onSuccess)
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return call
    }


}