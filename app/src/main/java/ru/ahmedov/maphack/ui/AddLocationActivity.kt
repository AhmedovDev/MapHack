package ru.ahmedov.maphack.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_location.*
import ru.ahmedov.maphack.R

class AddLocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)

        save_add_address.setOnClickListener {
            val intent = Intent(applicationContext, TaskActivity::class.java)
            startActivity(intent)
        }
    }
}