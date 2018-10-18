package nyc.bbah.thelibraryapp.network

import nyc.bbah.thelibraryapp.model.Book
import retrofit2.Call
import retrofit2.http.*

interface BooksService {

    @GET("books")
    fun getBooks(): Call<List<Book>>

    @GET("books/{id}")
    fun getABook(@Path("id") bookId: Int): Call<Book>

    @POST("books")
    fun addBook(@Body book: Book): Call<Book>

    @PUT("books/{id}")
    fun updateBook(@Path("id") bookId: Int, @Body book: Book): Call<Book>

    @DELETE("books/{id}")
    fun deleteBook(@Path("id") id: Int?): Call<Unit>

    @DELETE("clean")
    fun deleteAllBooks(): Call<Unit>

    object ApiUtils {

        private const val BASE_URL = "http://prolific-interview.herokuapp.com/5bbe13b79f6d520009971394/"

        val books_Service: BooksService
            get() = RetrofitClient.getClient(BASE_URL)!!.create(BooksService::class.java)
    }

}