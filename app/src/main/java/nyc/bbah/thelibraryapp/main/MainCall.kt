package nyc.bbah.thelibraryapp.main

import android.content.ContentValues.TAG
import android.util.Log
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainCall(val bookService: BooksService) : MainContract.Network {
    //make network call to get List of Books. super from contract
    override fun apiCall(onSuccess: (List<Book>) -> Unit): Call<List<Book>> {
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