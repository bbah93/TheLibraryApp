package nyc.bbah.thelibraryapp.main.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import nyc.bbah.thelibraryapp.R
import nyc.bbah.thelibraryapp.main.MainCall
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

// TODO: Rename parameter arguments, choose names that match
/**
 * A simple [Fragment] subclass.
 *
 */
class AddBookFragment : Fragment() {

    lateinit var bookTitle: EditText
    lateinit var bookAuthor: EditText
    lateinit var bookPublisher: EditText
    lateinit var bookCategories: EditText
    //var book: Book = Book()
    val bookListFragment: BookListFragment = BookListFragment()




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_add_book, container, false)

        bookTitle = rootView.findViewById(R.id.bookTitle_editText)
        bookAuthor = rootView.findViewById(R.id.author_editText)
        bookPublisher = rootView.findViewById(R.id.publisher_editText)
        bookCategories = rootView.findViewById(R.id.categories_editText)
        val submitButton: Button = rootView.findViewById(R.id.submitButton)
        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")
        val BASE_URL = "http://prolific-interview.herokuapp.com/5bbe13b79f6d520009971394/"

        //TODO: Create Logic for supplying ID's to Book Objects

        submitButton.setOnClickListener {
            //check for empty editText fields
            if (bookTitle.text.toString().isEmpty() || bookAuthor.text.toString().isEmpty() || bookPublisher.text.toString().isEmpty() || bookCategories.text.toString().isEmpty()) {
                Toast.makeText(requireActivity(), "Please Complete Book Form", Toast.LENGTH_LONG).show()
            } else {
                val mainCall: MainCall = MainCall(BooksService.ApiUtils.books_Service)
                val book = Book(bookAuthor.text.toString(), bookTitle.text.toString(),
                        bookCategories.text.toString(), bookPublisher.text.toString(), BASE_URL, 33, dateInString, "")
                mainCall.addBook(book) {
                    fragmentManager?.inTransaction {
                        replace(R.id.fragment_container, bookListFragment )
                    }
                }
            }
        }
        return rootView
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}
