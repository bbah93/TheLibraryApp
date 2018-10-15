package nyc.bbah.thelibraryapp.main

import android.content.ContentValues.TAG
import android.util.Log
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.Books_Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainCall : Main_Contract.Network{
    var bookList: List<Book>? = null

    //make network call to get List of Books. super from contract
    override fun apiCall(): List<Book>? {
        val bookService: Books_Service?
        bookService = Books_Service.ApiUtils.books_Service

        bookService.getBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                this@MainCall.bookList = response.body()
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
        Log.i(TAG, "bookList size = ${bookList}")
        return bookList

    }
}