package nyc.bbah.thelibraryapp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_book_list.*
import nyc.bbah.thelibraryapp.R
import nyc.bbah.thelibraryapp.RecyclerView.BooksAdapter
import nyc.bbah.thelibraryapp.main.fragment.AddBookFragment
import nyc.bbah.thelibraryapp.main.fragment.BookDetailsFragment
import nyc.bbah.thelibraryapp.main.fragment.BookListFragment
import nyc.bbah.thelibraryapp.model.Book
import nyc.bbah.thelibraryapp.network.BooksService
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    val addBookFragment: AddBookFragment = AddBookFragment()
    val bookListFragment: BookListFragment = BookListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //replaceFragment(bookListFragment)

//        supportFragmentManager.inTransaction {
//            replace(R.id.fragment_container, bookListFragment)
//        }
        replaceFragment(bookListFragment)

    }

//    override fun onStop() {
//        super.onStop()
//        call?.cancel()
//    }

    //create menu for adding and removing book
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.add_book -> {
                supportFragmentManager.inTransaction {
//                    main_booklistRV.visibility = View.INVISIBLE
                    replace(R.id.fragment_container, addBookFragment)
                            .addToBackStack("AddBook Fragment")
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
//    override fun onBackPressed() {
//        super.onBackPressed()
//        main_booklistRV.visibility = View.VISIBLE
//    }

    //Fragment Transactions
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    //inline extended fragment func
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

}
