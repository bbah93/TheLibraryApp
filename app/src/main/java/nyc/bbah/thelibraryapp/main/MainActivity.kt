package nyc.bbah.thelibraryapp.main

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.checkout_dialog.view.*
import nyc.bbah.thelibraryapp.R
import nyc.bbah.thelibraryapp.main.fragment.AddBookFragment
import nyc.bbah.thelibraryapp.main.fragment.BookListFragment
import nyc.bbah.thelibraryapp.network.BooksService

class MainActivity : AppCompatActivity() {

    private val addBookFragment: AddBookFragment = AddBookFragment()
    private val bookListFragment: BookListFragment = BookListFragment()
    val mainCall: MainCall = MainCall(BooksService.ApiUtils.books_Service)

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

                    //Inflate the dialog with custom view
                    val mDialogView = LayoutInflater.from(this).inflate(R.layout.deletebook_dialog, null)
                    //AlertDialogBuilder
                    val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                    //show dialog
                    val  mAlertDialog = mBuilder.show()
                    //login button click of custom layout
                    mDialogView.dialogDeleteBtn.setOnClickListener {
                        //TODO: Make API call to delete book
                        //dismiss dialog
                        mAlertDialog.dismiss()
                        //get text from EditTexts of custom layout
                        mainCall.deleteAll({})
                        Toast.makeText(this, "Deleted All", Toast.LENGTH_LONG).show()
                    }
                    //cancel button click of custom layout
                    mDialogView.deleteCancelBtn.setOnClickListener {
                        //dismiss dialog
                        mAlertDialog.dismiss()
                    }
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
