package org.sopt.play

import android.app.Activity
import android.content.Intent
import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.Button
import org.sopt.play.ui.component.TextField
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.b28

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6
    val isPasswordMatch = password == passwordConfirm

    val emailError = if (email.isNotEmpty() && !isEmailValid) "올바른 이메일을 입력해주세요." else null
    val passwordError = if (password.isNotEmpty() && !isPasswordValid) "비밀번호는 6자 이상 입력해주세요." else null
    val passwordConfirmError = if (passwordConfirm.isNotEmpty() && !isPasswordMatch) "비밀번호와 동일하게 입력해주세요." else null

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 60.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "이메일로 회원가입",
                style = b28,
                color = Black,
                modifier = Modifier.fillMaxWidth(),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                TextField(
                    label = "이름",
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "홍길동",
                )

                TextField(
                    label = "이메일 주소",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "abc@email.com",
                    errorMessage = emailError,
                )

                TextField(
                    label = "비밀번호",
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "6자 이상의 비밀번호",
                    errorMessage = passwordError,
                    isPassword = true,
                )

                TextField(
                    label = "비밀번호 확인",
                    value = passwordConfirm,
                    onValueChange = { passwordConfirm = it },
                    placeholder = "6자 이상의 비밀번호",
                    errorMessage = passwordConfirmError,
                    isPassword = true,
                )
            }

            Button(
                text = "회원가입",
                enabled = name.isNotBlank() && isEmailValid && isPasswordValid && isPasswordMatch,
                onClick = {
                    val resultIntent = Intent().apply {
                        putExtra("email", email)
                        putExtra("password", password)
                    }
                    val activity = context as Activity
                    activity.setResult(Activity.RESULT_OK, resultIntent)
                    activity.finish()
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    PlaySoptTheme {
        RegisterScreen()
    }
}
