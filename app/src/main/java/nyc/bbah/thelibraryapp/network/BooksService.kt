package nyc.bbah.thelibraryapp.network

import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.model.UpdateBookBody
import retrofit2.Call
import retrofit2.http.*

interface BooksService {

    @GET("books")
    fun getBooks(): Call<List<Book>>

    @GET("books/{id}")
    fun getABook(@Field("id") bookId: Int): Call<Book>

    @POST("books/{id}")
    fun addBook(@Path("id")id: Int): Call<Unit>

    @PUT("books/{id}")
    fun updateBook(@Path("id") bookId: Int, @Body updateBookBody: UpdateBookBody): Call<Book>

    @DELETE("books/{id}")
    fun deleteBook(@Path("id") id: Int): Call<Unit>

    @DELETE("clean")
    fun deleteAllBooks(): Call<Unit>

    object ApiUtils {

        private const val BASE_URL = "http://prolific-interview.herokuapp.com/5bbe13b79f6d520009971394/"

        val books_Service: BooksService
            get() = RetrofitClient.getClient(BASE_URL)!!.create(BooksService::class.java)
    }

}