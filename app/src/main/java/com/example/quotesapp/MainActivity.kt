package com.example.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel


    private val qtxt : TextView get() = findViewById(R.id.title)
    private val atxt : TextView get() = findViewById(R.id.author)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        )[MainViewModel::class.java]

    setQuote(mainViewModel.getQuotes())

    }

    fun setQuote(quotes: Quotes){

        qtxt.text = quotes.text
        atxt.text = quotes.author
    }

    fun onPreviousClick(view: View) {
        setQuote(mainViewModel.previousQuotes())
    }
    fun onNextClick(view: View) {
        setQuote(mainViewModel.nextQuotes())
    }
    fun onShareClick(view: View) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuotes().text)

        startActivity(intent)
    }


}