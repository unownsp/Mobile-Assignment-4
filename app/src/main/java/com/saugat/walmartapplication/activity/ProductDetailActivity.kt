package com.saugat.walmartapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.saugat.walmartapplication.R
import com.saugat.walmartapplication.databinding.ActivityItemListBinding
import com.saugat.walmartapplication.databinding.ActivityProductDetailBinding
import com.saugat.walmartapplication.pojo.Product

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product: Product = Gson().fromJson(
            intent.getStringExtra("product"),
            Product::class.java)

        Glide.with(this).load(product.image).into(binding.imgLogo)
        binding.tvTitle.text = product.title
        binding.tvColor.text = "Color: " + product.color
        binding.tvItemId.text = "Item Id: " + product.itemId
        binding.tvDescription.text = product.desc

    }
}