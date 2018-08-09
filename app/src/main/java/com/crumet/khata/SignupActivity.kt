package com.crumet.khata

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        val userSession = UserSession(this)

        text_email.setOnKeyListener { _, _, _ ->
            if (isValidEmail(text_email.text.toString())) {
                text_email.error = null
            }
            false
        }
        text_password.setOnKeyListener { _, _, _ ->
            if (isPasswordValidText(text_password.text.toString())) {
                text_password.error = null
            }
            false
        }
        text_conf_pass.setOnKeyListener { _, _, _ ->
            if (text_password.text.toString() == text_conf_pass.text.toString()) {
                text_conf_pass.error = null
            }
            false
        }
        btn_signup.setOnClickListener {
            val email = text_email.text.toString()
            val pass = text_password.text.toString()
            val confPass = text_conf_pass.text.toString()
            if (!isValidEmail(email)) {
                text_email.error = "invalid email"
            }
            if (!isPasswordValidText(pass)) {
                text_password.error = getString(R.string.error_password)
            }
            if (pass != confPass) {
                text_conf_pass.error = getString(R.string.password_mismatch)
            } else {
                userSession.createUserLoginSession(email, pass)

                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("Registered",true)
                startActivity(intent)
            }
        }

        text_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return if (target == null) false else android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()

    }

    private fun isPasswordValidText(text: CharSequence?): Boolean {
        return text != null && text.length >= 8
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
