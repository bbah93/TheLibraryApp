package nyc.bbah.thelibraryapp.RecyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import nyc.bbah.thelibraryapp.R.*
import nyc.bbah.thelibraryapp.model.Book

class BooksAdapter(val items: ArrayList<Book>, val context: Context) : RecyclerView.Adapter<BooksViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(LayoutInflater.from(context).inflate(layout.books_item, parent, false))
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book: Book = items[position]
        holder.onBind(book)

    }

    override fun getItemCount(): Int {
        return items.size
    }
}