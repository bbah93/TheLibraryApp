package nyc.bbah.thelibraryapp.main

import com.nhaarman.mockitokotlin2.*
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainCallTest {

    val bookService = mock<BooksService>()
    val mainCall = MainCall(bookService)

    @Test
    fun `should return call from retrofit`() {
        val mockCall = mock<Call<List<Book>>>()
        val onSuccess = mock<(List<Book>) -> Unit>()
        whenever(bookService.getBooks()).thenReturn(mockCall)

        val returnedCall = mainCall.apiCall(onSuccess)
        assert(returnedCall == mockCall)
    }

    @Test
    fun `should call through to get books`() {
        val bookList = listOf<Book>()
        val response = mock<Response<List<Book>>> {
            on { body() } doReturn bookList
        }
        val argumentCaptor = argumentCaptor<Callback<List<Book>>>()
        val mockCall = mock<Call<List<Book>>>()
        val onSuccess = mock<(List<Book>) -> Unit>()
        whenever(bookService.getBooks()).thenReturn(mockCall)

        mainCall.apiCall(onSuccess)

        verify(mockCall).enqueue(argumentCaptor.capture())
        argumentCaptor.firstValue.onResponse(mockCall, response)
        verify(mockCall).enqueue(any())
        verify(onSuccess).invoke(bookList)
    }
}