package com.atul.firebasecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth= FirebaseAuth.getInstance()
        var currentUser=auth.currentUser

        if(currentUser==null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        var logout = findViewById<Button>(R.id.idLogout)
        logout.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }

    //add SHA-256, SHA1 code
}