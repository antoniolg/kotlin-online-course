package com.antonioleiva.myplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.antonioleiva.myplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MediaAdapter(getItems()) { toast(it.title) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
    }
}