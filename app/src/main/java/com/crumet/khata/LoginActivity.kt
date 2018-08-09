package com.crumet.khata

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
        text_signup.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

}
