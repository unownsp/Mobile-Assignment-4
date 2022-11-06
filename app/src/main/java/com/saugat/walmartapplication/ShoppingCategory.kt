package com.saugat.walmartapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.gson.Gson
import com.saugat.walmartapplication.activity.ItemListActivity
import com.saugat.walmartapplication.databinding.ActivityShoppingCategoryBinding
import com.saugat.walmartapplication.pojo.Product

class ShoppingCategory : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ss: String = intent.getStringExtra("username").toString()
        binding.welcomeText.text = "Welcome: $ss"

        binding.beautyLayout.setOnClickListener {
            showToast("Beauty")
        }
        binding.clothingLayout.setOnClickListener {
            showToast("Clothing")
        }
        binding.electronicLayout.setOnClickListener {
            val electronicItemList = ArrayList<Product>()
            val product1 = Product(
                "Mobile",
                999.99,
                "White",
                "https://www.cnet.com/a/img/resize/8b89d8f9fcd1d0cab11d6d44f568d24e7217ce3c/hub/2022/08/09/66fa7e7a-8809-4589-a07f-84e506026e26/cnet-iphone-12.png?auto=webp&width=1200",
                "Mbl1",
                "A mobile phone that works perfectly."
            )

            val product2 = Product(
                "Television",
                676.50,
                "Black",
                "https://www.cnet.com/a/img/resize/8b89d8f9fcd1d0cab11d6d44f568d24e7217ce3c/hub/2022/08/09/66fa7e7a-8809-4589-a07f-84e506026e26/cnet-iphone-12.png?auto=webp&width=1200",
                "Tv1",
                "A television that you can watch."
            )

            val product3 = Product(
                "Laptop",
                1300.00,
                "Blue",
                "https://www.cnet.com/a/img/resize/8b89d8f9fcd1d0cab11d6d44f568d24e7217ce3c/hub/2022/08/09/66fa7e7a-8809-4589-a07f-84e506026e26/cnet-iphone-12.png?auto=webp&width=1200",
                "Laptop1",
                "Very thin laptop. Suitable for travellers."
            )
            val product4 = Product(
                "Mouse",
                12.00,
                "Red",
                "https://www.cnet.com/a/img/resize/8b89d8f9fcd1d0cab11d6d44f568d24e7217ce3c/hub/2022/08/09/66fa7e7a-8809-4589-a07f-84e506026e26/cnet-iphone-12.png?auto=webp&width=1200",
                "Mou1",
                "Best mouse that points to a point on the screen."
            )

            electronicItemList.add(product1)
            electronicItemList.add(product2)
            electronicItemList.add(product3)
            electronicItemList.add(product4)

            val intent = Intent(this, ItemListActivity::class.java)
            intent.putExtra("productList", Gson().toJson(electronicItemList))
            startActivity(intent)
        }
        binding.foodLayout.setOnClickListener {
            showToast("Food")
        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(
            this,
            "You have chosen the $msg category of shopping",
            Toast.LENGTH_SHORT
        )
            .show()
    }
}