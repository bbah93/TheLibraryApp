package nyc.bbah.thelibraryapp.main

import nyc.bbah.thelibraryapp.model.Book

interface Main_Contract {

    //inner interface for call to server used in main
    interface Network{
        fun apiCall(): List<Book>?
    }

}