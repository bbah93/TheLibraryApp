package nyc.bbah.thelibraryapp.RecyclerView

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import kotlinx.android.synthetic.main.books_item.view.*
import nyc.bbah.thelibraryapp.main.MainContract
import nyc.bbah.thelibraryapp.model.Book

class BooksViewHolder(itemView: View?, val clickListener: MainContract.RecyclerOnClickListener) : ViewHolder(itemView) {
    val defaultTitle: String = "The Title That Never Was"
    val defaultAuthor: String = "Oscar Twain"


    fun onBind(book: Book){
        if(itemView.bookTitleView.text == null){
            itemView?.bookTitleView?.text = defaultTitle
        }
        itemView?.bookTitleView?.text = book.title
        if (itemView?.authorView?.text == null){
            itemView?.authorView?.text = defaultAuthor
        }
        itemView?.authorView?.text = book.author
        itemView?.setOnClickListener {
           clickListener.onItemClick(book)
        }
    }
}