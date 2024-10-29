package com.example.homeworkex1

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: CustomAdapter
    private val dataList = listOf(
        student("Le Van Toan","20225160"),
        student("Nguyen Van Truong","20225233"),
        student("Dang Thai Tuan","20225235"),
        student("Pham Viet Hoa","20225128"),
        student("Aoy","20225224")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        adapter = CustomAdapter(dataList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = if (newText.isNullOrEmpty() || newText.length < 2) {
                    dataList
                } else {
                    dataList.filter { it.name.contains(newText, ignoreCase = true) || it.mssv.contains(newText, ignoreCase = true) }
                }
                adapter.updateList(filteredList)
                return true
            }
        })
    }
}
