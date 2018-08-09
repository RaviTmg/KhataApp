package com.crumet.khata

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val email = text_email.text
        val pass = text_password.text
        val confPass = text_conf_pass.text

        text_email.setOnKeyListener { view, i, keyEvent ->
            if (isValidEmail(email)) {
                text_email.error = null
            }
            false
        }
        text_password.setOnKeyListener { _, _, _ ->
            if (isPasswordValidText(pass)) {
                text_password.error = null
            }
            false
        }
        text_conf_pass.setOnKeyListener { _, _, _ ->
            if (pass == confPass) {
                text_conf_pass.error = null
            }
            false
        }
        btn_signup.setOnClickListener {
            if (!isValidEmail(email)) {
                text_email.error = "invalid email"
            }
            if (!isPasswordValidText(pass)) {
                text_password.error = getString(R.string.error_password)
            }
            if (pass.toString() != confPass.toString()) {
                text_conf_pass.error = getString(R.string.password_mismatch)
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        text_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return if (target == null) false else android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()

    }

    private fun isPasswordValidText(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}
