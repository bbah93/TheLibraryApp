package nyc.bbah.thelibraryapp.RecyclerView

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import kotlinx.android.synthetic.main.books_item.view.*
import nyc.bbah.thelibraryapp.main.MainContract
import nyc.bbah.thelibraryapp.model.Book

class BooksViewHolder(itemView: View?, val clickListener: MainContract.RecyclerOnClickListener) : ViewHolder(itemView) {

    fun onBind(book: Book){
        itemView?.bookTitleView?.text = book.title
        itemView?.authorView?.text = book.author
        itemView?.setOnClickListener {
           clickListener.onItemClick(book)
        }
    }
}