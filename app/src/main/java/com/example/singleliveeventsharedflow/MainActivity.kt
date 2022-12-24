package com.example.singleliveeventsharedflow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.example.singleliveeventsharedflow.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val vm by viewModels<MainVieModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = findViewById<Button>(R.id.SingleLiveEvent) as Button
        val text = findViewById<EditText>(R.id.myText)

        button.setOnClickListener {
            vm.onAuctionToUpload(text.text.toString())
        }

       binding.ChangeActivityToChanelFlow.setOnClickListener {
            toSecondActivity()
        }

        initObservers()


    }

    fun toSecondActivity() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }




    private fun initObservers() {
        vm.getUploadAuction().observe(
            this
        ) {
            it?.let {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()


            }
        }
    }






}