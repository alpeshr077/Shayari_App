package com.alpesh.shayari_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpesh.shayari_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var shayarilist = ArrayList<ShayriModel>()
    lateinit var dbHelper: ExternalDatabase
    lateinit var adapter: ShayariAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter.setshayari(shayarilist)

        binding.rcvListShayari.layoutManager = LinearLayoutManager(this)
        binding.rcvListShayari.adapter = adapter

    }
}