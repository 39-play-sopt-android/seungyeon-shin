package org.sopt.play.presentation.register

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.presentation.login.LoginScreen
import org.sopt.play.presentation.login.LoginViewModel
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.b28

@Composable
fun RegisterRoute(
    onRegisterClick: (name: String, email: String, password: String, passwordConfirm: String) -> Unit,
    viewModel: RegisterViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    RegisterScreen(
        name = uiState.name,
        email = uiState.email,
        password = uiState.password,
        passwordConfirm = uiState.passwordConfirm,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onPasswordConfirmChange = viewModel::onPasswordConfirmChange,
        onRegisterClick = onRegisterClick,

        )
}
@Composable
fun RegisterScreen(
    name: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onRegisterClick: (name: String, email: String, password: String, passwordConfirm: String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6
    val isPasswordMatch = password == passwordConfirm

    val emailError = if (email.isNotEmpty() && !isEmailValid) "올바른 이메일을 입력해주세요." else null
    val passwordError = if (password.isNotEmpty() && !isPasswordValid) "비밀번호는 6자 이상 입력해주세요." else null
    val passwordConfirmError = if (passwordConfirm.isNotEmpty() && !isPasswordMatch) "비밀번호와 동일하게 입력해주세요." else null

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, top = 60.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
    ) {
        Text(
            text = "이메일로 회원가입",
            style = b28,
            color = Black,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            PlayTextField(
                label = "이름",
                value = name,
                onValueChange = onNameChange,
                placeholder = "홍길동",
            )

            PlayTextField(
                label = "이메일 주소",
                value = email,
                onValueChange = onEmailChange,
                placeholder = "abc@email.com",
                errorMessage = emailError,
            )

            PlayTextField(
                label = "비밀번호",
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "6자 이상의 비밀번호",
                errorMessage = passwordError,
                isPassword = true,
            )

            PlayTextField(
                label = "비밀번호 확인",
                value = passwordConfirm,
                onValueChange = onPasswordConfirmChange,
                placeholder = "6자 이상의 비밀번호",
                errorMessage = passwordConfirmError,
                isPassword = true,
            )
        }

        PlayButton(
            text = "회원가입",
            enabled = name.isNotBlank() && isEmailValid && isPasswordValid && isPasswordMatch,
            onClick = { onRegisterClick(name,email, password, passwordConfirm) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    PlaySoptTheme {
        RegisterScreen(
            name = "홍길동",
            email = "abc@email.com",
            password = "",
            passwordConfirm = "",
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordConfirmChange = {},
            onRegisterClick = { _, _, _, _ -> } )}
}
