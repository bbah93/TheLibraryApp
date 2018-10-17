package nyc.bbah.thelibraryapp.main

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import nyc.bbah.thelibraryapp.R
import nyc.bbah.thelibraryapp.main.fragment.AddBookFragment
import nyc.bbah.thelibraryapp.main.fragment.BookListFragment

class MainActivity : AppCompatActivity() {

    private val addBookFragment: AddBookFragment = AddBookFragment()
    private val bookListFragment: BookListFragment = BookListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.inTransaction {
            replace(R.id.fragment_container, bookListFragment)
        }
    }
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
    //inline extended fragment func
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

}
