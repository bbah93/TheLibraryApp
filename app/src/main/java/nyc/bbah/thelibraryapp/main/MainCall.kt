package nyc.bbah.thelibraryapp.main

import android.content.ContentValues.TAG
import android.util.Log
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainCall : MainContract.Network{
    //make network call to get List of Books. super from contract
    override fun apiCall(onSuccess: (List<Book>) -> Unit): Call<List<Book>> {
        val bookService = BooksService.ApiUtils.books_Service
        val call = bookService.getBooks()

        bookService.getBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                val data = response.body()
                data?.let(onSuccess)
                //console log for response data
                Log.d(TAG, "onResponse =${response.raw()}")
                Log.d(TAG, "bookID = ${response.body()?.get(0)?.id}")
                Log.d(TAG, "bookList size = ${response.body()?.size}")

            }
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
               t.printStackTrace()
                Log.i(TAG, "onFailure: " + t.toString())
            }

        })
        return call
    }
}