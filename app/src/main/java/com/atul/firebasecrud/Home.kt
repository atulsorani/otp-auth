package com.atul.firebasecrud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    //add SHA-256, SHA1 code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        var currentUser = auth.currentUser

        if (currentUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        var logout = findViewById<Button>(R.id.idLogout)
        logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        var tabone = findViewById<Button>(R.id.tab1)
        var tabtwo = findViewById<Button>(R.id.tab2)

        var layoutSignup = findViewById<LinearLayout>(R.id.lyoutsignup)
        var layoutLogin = findViewById<LinearLayout>(R.id.lyoutlogin)

        var emails = findViewById<EditText>(R.id.email)
        var pass = findViewById<EditText>(R.id.password)
        var cpass = findViewById<EditText>(R.id.repassword)

        var lgemails = findViewById<EditText>(R.id.lgemail)
        var lgpass = findViewById<EditText>(R.id.lgpassword)

        //Swap Frame
        tabone.setOnClickListener {
            layoutLogin.isInvisible = true
            layoutSignup.isInvisible = false
        }

        tabtwo.setOnClickListener {
            layoutLogin.isInvisible = false
            layoutSignup.isInvisible = true
        }

        //SignUp Code Here
        var btnsignup = findViewById<Button>(R.id.signup)
        btnsignup.setOnClickListener {
            val email = emails.text.toString().trim()
            val password = pass.text.toString().trim()
            val cpassword = cpass.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && cpassword.isNotEmpty()) {

                if (password.equals(cpassword)) {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            layoutLogin.isInvisible = false
                            layoutSignup.isInvisible = true
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Empty field are not allows", Toast.LENGTH_SHORT).show()
            }
        }

        //LogIn code
        val loginbtn = findViewById<Button>(R.id.login)
        loginbtn.setOnClickListener {
            val loginemail = lgemails.text.toString().trim()
            val loginPass = lgpass.text.toString().trim()

            if (loginemail.isNotEmpty() && loginPass.isNotEmpty()) {
                auth.signInWithEmailAndPassword(loginemail, loginPass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity2::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty fields are not allows", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}