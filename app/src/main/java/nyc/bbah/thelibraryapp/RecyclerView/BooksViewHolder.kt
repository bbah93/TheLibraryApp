package nyc.bbah.thelibraryapp.RecyclerView

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import kotlinx.android.synthetic.main.books_item.view.*
import nyc.bbah.thelibraryapp.model.Book

class BooksViewHolder(itemView: View?) : ViewHolder(itemView) {

    val bookTitle = itemView?.bookTitleView
    val author = itemView?.authorView

    fun onBind(books: Book){

        bookTitle!!.text = books.title
        author!!.text = books.author
    }
}