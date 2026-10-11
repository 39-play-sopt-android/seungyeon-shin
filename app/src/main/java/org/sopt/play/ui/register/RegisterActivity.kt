package org.sopt.play.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.sopt.play.ui.theme.PlaySoptTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterScreen(
                        onRegisterClick = ::finishWithResult,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

    private fun finishWithResult(email: String, password: String) {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_EMAIL, email)
            putExtra(EXTRA_PASSWORD, password)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    companion object {
        const val EXTRA_EMAIL = "email"
        const val EXTRA_PASSWORD = "password"
    }
}
