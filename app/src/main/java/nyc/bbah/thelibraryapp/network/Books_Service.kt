package nyc.bbah.thelibraryapp.network

import nyc.bbah.thelibraryapp.model.Book
import retrofit2.Call
import retrofit2.http.*

interface Books_Service {

    @GET("books")
    fun getBooks(): Call<List<Book>>

    @GET("books/{id}")
    fun getABook(@Field("id") bookId: Int): Call<Book>

    @POST("books")
    fun addBook(@Field("author") author: String, @Field("title") title: String,
                @Field("publisher") publisher: String, @Field("categories") categories: String): Call<Unit>

    @PUT("books/{id}")
    fun updateBook(@Field("id") bookId: Int, @Field("lastCheckoutBy") lastCheckoutBy: String): Call<Unit>

    @DELETE("books/{id}")
    fun deleteBook(@Field("id") id: Int): Call<Unit>

    @DELETE("clean")
    fun deleteAllBooks(): Call<Unit>



}