package nyc.bbah.thelibraryapp.main.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [BookData::class], version = 1)
abstract class BookDataBase : RoomDatabase() {
    abstract fun bookDataDao(): BookDataDao

    companion object {
        private var INSTANCE: BookDataBase? = null

        fun getInstance(context: Context): BookDataBase? {
            if (INSTANCE == null) {
                synchronized(BookDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            BookDataBase::class.java, "bookDataBase")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}