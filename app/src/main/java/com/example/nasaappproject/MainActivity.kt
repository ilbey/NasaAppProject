package com.example.nasaappproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.drawable.ColorDrawable
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var marsViewModel : MarsViewModel
    private lateinit var marsAdapter : MarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        marsViewModel = ViewModelProviders.of(this).get(MarsViewModel::class.java)
        setUI()
    }

    private fun setUI() {
        setActionBar()
        getDataFromAPI()
    }
    private fun setActionBar(){
        val actionbar: ActionBar? = supportActionBar
        actionbar?.title = "Nasa App"
        actionbar?.elevation = 4.toFloat()
        actionbar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(this,
                    //R.color.colorPrimary
                    R.color.black
                ))
        )
    }

    private fun getDataFromAPI() {
        marsViewModel.data.observe(this, Observer { data ->
        marsAdapter = MarsAdapter(photos = data.photos, this)


        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = marsAdapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        var menuItem = menu!!.findItem(R.id.searchView)
        var searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                marsAdapter!!.filter.filter(newText)

                return true
            }
        })

        return true
    }

}