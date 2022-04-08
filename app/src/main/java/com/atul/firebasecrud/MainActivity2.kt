package com.atul.firebasecrud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        /*auth = FirebaseAuth.getInstance()
        var currentUser = auth.currentUser

        if (currentUser == null) {
            startActivity(Intent(this, Home::class.java))
            finish()
        }

        var logoutt = findViewById<Button>(R.id.lgout)
        logoutt.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Home::class.java))
            finish()
        }*/
    }
}