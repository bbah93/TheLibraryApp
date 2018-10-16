package nyc.bbah.thelibraryapp.main.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_book_list.view.*
import nyc.bbah.thelibraryapp.R
import nyc.bbah.thelibraryapp.RecyclerView.BooksAdapter
import nyc.bbah.thelibraryapp.main.MainActivity
import nyc.bbah.thelibraryapp.main.MainCall
import nyc.bbah.thelibraryapp.main.MainContract
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService
import retrofit2.Call

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class BookListFragment : Fragment() {
//    var call: Call<List<Book>>?= null
    val mainCall: MainCall = MainCall(BooksService.ApiUtils.books_Service)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_book_list, container, false)

        return rootView
    }

    //inline extended fragment func
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainCall.apiCall {
            val booksAdapter = BooksAdapter(it, object: MainContract.RecyclerOnClickListener {
                override fun onItemClick(book: Book) {
                    fragmentManager?.inTransaction {
                        replace(R.id.fragment_container, BookDetailsFragment.newInstance(book))
                                .addToBackStack("Book Details Fragement")
                    }
                }
            })
            main_booklistRV.layoutManager = LinearLayoutManager(requireActivity())
            main_booklistRV.adapter = booksAdapter
        }
    }

}
