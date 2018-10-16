package nyc.bbah.thelibraryapp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import nyc.bbah.thelibraryapp.R
import nyc.bbah.thelibraryapp.RecyclerView.BooksAdapter
import kotlinx.android.synthetic.main.activity_main.main_booklistRV
import nyc.bbah.thelibraryapp.main.fragment.BookDetailsFragment

class MainActivity : AppCompatActivity() {

    val mainCall: MainCall = MainCall()
    val bookDetailsFragment: BookDetailsFragment = BookDetailsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainCall.apiCall {
            val booksAdapter = BooksAdapter(it)
            main_booklistRV.adapter = booksAdapter
            main_booklistRV.layoutManager = LinearLayoutManager(this)
        }

        //call to add fragment
//        supportFragmentManager.inTransaction {
//            remove(fragmentA)
//            add(R.id.frameLayoutContent, fragmentB)
//        }
    }
    //create menu for adding and removing book
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
//    inline extended fragment func
//    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
//        val fragmentTransaction = beginTransaction()
//        fragmentTransaction.func()
//        fragmentTransaction.commit()
//
//    }





}
