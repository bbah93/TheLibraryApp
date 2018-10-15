package nyc.bbah.thelibraryapp.RecyclerView

import android.content.ContentValues.TAG
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import nyc.bbah.thelibraryapp.R.*
import nyc.bbah.thelibraryapp.model.Book

class BooksAdapter(private val items: List<Book>?, private val context: Context) : RecyclerView.Adapter<BooksViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(LayoutInflater.from(context).inflate(layout.books_item, parent, false))
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.onBind(items!!.get(position))


    }

    override fun getItemCount(): Int {
        //Log.i(TAG, "list size = ${items!!.isEmpty()}")
        if (items != null) {
            return items.size
        }
        return 0
    }
}