package nyc.bbah.thelibraryapp.RecyclerView


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import nyc.bbah.thelibraryapp.R.layout
import nyc.bbah.thelibraryapp.main.MainContract
import nyc.bbah.thelibraryapp.model.Book

class BooksAdapter(val items: List<Book>?, val clickListener: MainContract.RecyclerOnClickListener) : RecyclerView.Adapter<BooksViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
                LayoutInflater.from(parent.context).inflate(layout.books_item, parent, false),
                clickListener)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = items!![position]
        holder.onBind(book)
    }


    override fun getItemCount(): Int {
        if (items != null) {
            return items.size
        }
        return 0
    }
}
