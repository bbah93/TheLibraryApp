package nyc.bbah.thelibraryapp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import nyc.bbah.thelibraryapp.R
import nyc.bbah.thelibraryapp.RecyclerView.BooksAdapter
import nyc.bbah.thelibraryapp.main.fragment.AddBookFragment
import nyc.bbah.thelibraryapp.main.fragment.BookDetailsFragment
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService
import nyc.bbah.thelibraryapp.network.RetrofitClient
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    var call: Call<List<Book>> ?= null
    val mainCall: MainCall = MainCall(BooksService.ApiUtils.books_Service)
    val bookDetailsFragment: BookDetailsFragment = BookDetailsFragment()
    val addBookFragment: AddBookFragment = AddBookFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainCall.apiCall {
            val booksAdapter = BooksAdapter(it, object: MainContract.RecyclerOnClickListener {
                override fun onItemClick(book: Book) {
                    supportFragmentManager.inTransaction {
                        main_booklistRV.visibility = View.INVISIBLE
                        replace(R.id.fragment_container, BookDetailsFragment.newInstance(book))
                                .addToBackStack("Book Details Fragement")

                    }
                }

            })

            main_booklistRV.adapter = booksAdapter
            main_booklistRV.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onStop() {
        super.onStop()
        call?.cancel()
    }

    //create menu for adding and removing book
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //inline extended fragment func
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.add_book -> {
                //TODO: Add Fragment Transaction for AddBookFragment
                supportFragmentManager.inTransaction {
                    main_booklistRV.visibility = View.INVISIBLE
                    replace(R.id.fragment_container, addBookFragment)
                            .addToBackStack("Book Details Fragement")
                }
                true
            }
            R.id.delete_books -> {
                //TODO: Add RetroCall for Delete/clear
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        main_booklistRV.visibility = View.VISIBLE

    }
}
