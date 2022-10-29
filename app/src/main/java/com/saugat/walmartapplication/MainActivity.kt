package com.saugat.walmartapplication

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.saugat.walmartapplication.activity.RegisterActivity
import com.saugat.walmartapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userList.add(User("Saugat", "Pageni", "saugat@gmail.com", "apple"))
        userList.add(User("Sagar", "Basnet", "sagar@gmail.com", "ball"))
        userList.add(User("Ram", "Pandit", "ram@gmail.com", "cat"))
        userList.add(User("Hari", "Sharma", "hari@gmail.com", "dog"))
        userList.add(User("Sujan", "Shrestha", "sujan@gmail.com", "eye"))

        binding.signInButton.setOnClickListener {
            val email = binding.emailET.editableText.toString()
            val password = binding.passwordET.editableText.toString()

            if (email.trim().isEmpty()) Toast.makeText(
                this,
                "Enter a valid username",
                Toast.LENGTH_SHORT
            ).show()
            else if (password.trim().isEmpty()) Toast.makeText(
                this,
                "Enter a valid password",
                Toast.LENGTH_SHORT
            ).show()
            else {
                val user = User("", "", email, password)
                var found = false
                userList.forEach {
                    if (it == user) {
                        found = true
                        startActivity(Intent(
                            this,
                            ShoppingCategory::class.java
                        ).apply {
                            putExtra("username", email)
                        })
                    }
                }

                if (!found) Toast.makeText(
                    this,
                    "Invalid User. Please Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        binding.createAccountButton.setOnClickListener {
            resultLauncher.launch(Intent(this, RegisterActivity::class.java))
        }

        binding.forgotPasswordTV.setOnClickListener {
            val email = binding.emailET.editableText.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter username to change password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var password: String? = ""
            userList.forEach {
                if (it.userName == email) {
                    password = it.password
                    return@forEach
                }
            }

            if (password!!.isEmpty()) {
                Toast.makeText(this, "Username not found!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mailto = "mailto:$email" +
                    "?cc=" + "" +
                    "&subject=" + Uri.encode("Change your Password") +
                    "&body=" + Uri.encode("Your password is: $password")

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)

            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {

            }
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: User = result.data?.extras?.get("user") as User
                userList.add(data)
            }
        }
}