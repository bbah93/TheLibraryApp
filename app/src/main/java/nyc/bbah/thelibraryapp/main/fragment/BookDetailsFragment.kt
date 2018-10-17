package nyc.bbah.thelibraryapp.main.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.checkout_dialog.view.*
import nyc.bbah.thelibraryapp.R
import nyc.bbah.thelibraryapp.main.MainCall
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class BookDetailsFragment : Fragment() {

    lateinit var bookTitle: TextView
    lateinit var author: TextView
    lateinit var publisher: TextView
    lateinit var tags: TextView
    lateinit var checkoutInfo: TextView
    val mainCall: MainCall = MainCall(BooksService.ApiUtils.books_Service)
    val bookListFragment: BookListFragment = BookListFragment()


    companion object {
        fun newInstance(book: Book) : BookDetailsFragment {
            return BookDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, book)
                }
            }
        }
    }
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
        val deleteButton: Button = rootView.findViewById(R.id.deleteButton)
        val book = arguments?.getParcelable<Book>(ARG_PARAM1)
        val id = book!!.id

        setViews(book)

        checkoutButton.setOnClickListener {
            chekoutListener(book)
        }
        deleteButton.setOnClickListener {
            deleteBookListener(book)
        }
        return rootView
    }

    fun setViews(book: Book?){
        bookTitle.text = book?.title
        author.text = book?.author
        publisher.text = book?.publisher
        tags.text = book?.category

       //TODO: Put Checkout data here with concatenated data (time stamp + name)
    }

    fun chekoutListener(book: Book){
        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(activity).inflate(R.layout.checkout_dialog, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(requireActivity())
                .setView(mDialogView)
                .setTitle("Checkout Book")
        //show dialog
        val  mAlertDialog = mBuilder.show()

        mDialogView.dialogDeleteBtn.setOnClickListener {
            //dismiss dialog
            //TODO: Make API call to update book with checkout info
            mAlertDialog.dismiss()
            //get text from EditTexts
            val name = mDialogView.dialogDeleteTextView.text.toString()

        }
        //cancel button click of custom layout
        mDialogView.deleteCancelBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }

    fun deleteBookListener(book: Book){
//Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(activity).inflate(R.layout.deletebook_dialog, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(requireActivity())
                .setView(mDialogView)
        //show dialog
        val  mAlertDialog = mBuilder.show()
        //login button click of custom layout
        mDialogView.dialogDeleteBtn.setOnClickListener {
            //TODO: Make API call to delete book
            //dismiss dialog
            mAlertDialog.dismiss()
            //get text from EditTexts of custom layout
            mainCall.delete(book.id!!)
            Toast.makeText(requireActivity(), "Deleted ${bookTitle.text}", Toast.LENGTH_LONG).show()
            fragmentManager?.inTransaction {
                replace(R.id.fragment_container, bookListFragment )
            }

        }
        //cancel button click of custom layout
        mDialogView.deleteCancelBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}
