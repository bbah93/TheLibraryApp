package nyc.bbah.thelibraryapp.main


import android.util.Log
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainCall(private val bookService: BooksService) : MainContract.Network {



    override fun addBook(book: Book): Call<Book> {
        val call: Call<Book> = bookService.addBook(book)

        call.enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                val data : Book? = response.body()
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    override fun getBook(id: Int): Call<Book> {
        val call: Call<Book> = bookService.getABook(id)

        call.enqueue(object : Callback<Book>{
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                val data : Book? = response.body()
                Log.d("GETBOOK CHECK", data.toString())
            }
            override fun onFailure(call: Call<Book>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    override fun deleteAll(): Call<Unit> {
        val call: Call<Unit> = bookService.deleteAllBooks()
        call.enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val data : Unit? = response.body()
            }
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    override fun delete(id: Int): Call<Unit> {
        val call: Call<Unit> = bookService.deleteBook(id)

        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val data : Unit? = response.body()
                Log.d("DELETEBOOK CHECK", response.raw().toString())
                Log.d("DELETEBOOK CHECK 2: ", response.message())
            }
            override fun onFailure(call: Call<Unit>, t: Throwable) {
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
            }
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }


}