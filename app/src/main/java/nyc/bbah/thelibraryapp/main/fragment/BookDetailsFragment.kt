package nyc.bbah.thelibraryapp.main.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import nyc.bbah.thelibraryapp.R
import nyc.bbah.thelibraryapp.model.Book


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class BookDetailsFragment : Fragment() {
    private var bookTitle: TextView? = null
    var author: TextView? = null
    var publisher: TextView? = null
    var tags: TextView? = null
    var checkoutInfo: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_book_details, container, false)

        bookTitle = rootView.findViewById(R.id.title_textView)
        author = rootView.findViewById(R.id.authorTextView)
        publisher = rootView.findViewById(R.id.publisherTextView)
        tags = rootView.findViewById(R.id.tagsTextView)
        checkoutInfo = rootView.findViewById(R.id.checkoutTextView)
        val checkoutButton: Button = rootView.findViewById(R.id.checkoutButton)

        checkoutButton.setOnClickListener {
            //TODO: Alert dialogue box to grab name for checkout
            //TODO: Make API call to update book with checkout info
        }
        return rootView
    }

    fun setViews(book: Book){
        bookTitle?.text = book.title
        author?.text = book.author
        publisher?.text = book.publisher
        tags?.text = book.category

       //TODO: Put Checkout data here with concatenated data (time stamp + name)
    }

}
