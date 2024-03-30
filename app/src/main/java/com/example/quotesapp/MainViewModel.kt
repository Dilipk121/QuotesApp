package com.example.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) :ViewModel() {

    private var quoteList : Array<Quotes> = emptyArray() // only array used here not arrayList
    private var index = 0

    init{
        quoteList = loadQuoteFromAssets() // function called
    }

    private fun loadQuoteFromAssets():Array<Quotes>{

        val inputstrem = context.assets.open("quotes.json")
        val size : Int = inputstrem.available()
        val buffer = ByteArray(size)
        inputstrem.read(buffer)
        inputstrem.close()

        val json = String(buffer, Charsets.UTF_8)

        val gson = Gson()

        return  gson.fromJson(json,Array<Quotes>::class.java) //

    }


    fun getQuotes() = quoteList[index]

    fun nextQuotes() = quoteList[++index]

    fun previousQuotes() = quoteList[--index]



}