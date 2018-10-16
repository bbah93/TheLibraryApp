package nyc.bbah.thelibraryapp.RecyclerView

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.books_item.view.*
import kotlinx.android.synthetic.main.fragment_book_details.view.*
import nyc.bbah.thelibraryapp.model.Book

class BooksViewHolder(itemView: View?) : ViewHolder(itemView) {

    fun onBind(books: Book){
        itemView?.bookTitleView?.text = books.title
        itemView?.authorTextView?.text = books.author
       itemView?.setOnClickListener {

       }
    }


}