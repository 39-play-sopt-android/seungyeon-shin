package org.sopt.play.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.sopt.play.MainActivity
import org.sopt.play.presentation.register.RegisterActivity
import org.sopt.play.ui.theme.PlaySoptTheme

class LoginActivity : ComponentActivity() {
    // 회원가입 화면에서 전달받은 계정 정보
    private var registeredEmail = ""
    private var registeredPassword = ""

    private val registerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            registeredEmail = result.data?.getStringExtra(RegisterActivity.EXTRA_EMAIL) ?: ""
            registeredPassword = result.data?.getStringExtra(RegisterActivity.EXTRA_PASSWORD) ?: ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        onLoginClick = ::login,
                        onRegisterClick = {
                            registerLauncher.launch(Intent(this, RegisterActivity::class.java))
                        },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

    private fun login(email: String, password: String) {
        if (email == registeredEmail && password == registeredPassword) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "이메일 또는 비밀번호가 올바르지 않아요.", Toast.LENGTH_SHORT).show()
        }
    }
}
