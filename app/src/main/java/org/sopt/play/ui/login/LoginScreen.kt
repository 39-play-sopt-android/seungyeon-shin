package org.sopt.play.ui.login

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.Gray6
import org.sopt.play.ui.theme.b28
import org.sopt.play.ui.theme.m14

@Composable
fun LoginScreen(
    onLoginClick: (email: String, password: String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6

    val emailError = if (email.isNotEmpty() && !isEmailValid) "올바른 이메일을 입력해주세요." else null
    val passwordError = if (password.isNotEmpty() && !isPasswordValid) "비밀번호는 6자 이상 입력해주세요." else null

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 60.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
    ) {
        Text(
            text = "이메일로 로그인하기",
            style = b28,
            color = Black,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            PlayTextField(
                label = "이메일 주소",
                value = email,
                onValueChange = { email = it },
                placeholder = "abc@email.com",
                errorMessage = emailError,
            )

            PlayTextField(
                label = "비밀번호",
                value = password,
                onValueChange = { password = it },
                placeholder = "6자 이상의 비밀번호",
                errorMessage = passwordError,
                isPassword = true,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            PlayButton(
                text = "로그인",
                enabled = isEmailValid && isPasswordValid,
                onClick = { onLoginClick(email, password) },
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.clickable { onRegisterClick() },
            ) {
                Text(
                    text = "아직 계정이 없으신가요?",
                    style = m14,
                    color = Gray3,
                )
                Text(
                    text = "회원가입하기",
                    style = m14,
                    color = Gray6,

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    PlaySoptTheme {
        LoginScreen(
            onLoginClick = { _, _ -> },
            onRegisterClick = {},
        )
    }
}
