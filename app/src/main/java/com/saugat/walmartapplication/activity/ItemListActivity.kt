package com.saugat.walmartapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saugat.walmartapplication.adapters.ItemListAdapter
import com.saugat.walmartapplication.databinding.ActivityItemListBinding
import com.saugat.walmartapplication.pojo.Product
import java.lang.reflect.Type

class ItemListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type: Type = object : TypeToken<List<Product?>?>() {}.type

        val productList: ArrayList<Product> = Gson().fromJson(
            intent.getStringExtra("productList"),
            type)

        val adapter = ItemListAdapter(productList)
        binding.recyclerView.hasFixedSize()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}