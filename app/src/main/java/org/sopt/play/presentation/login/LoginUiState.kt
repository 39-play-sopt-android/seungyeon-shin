package org.sopt.play.presentation.login

import android.util.Patterns

data class LoginUiState(
    val email: String = "",
    val password: String = "",
) {
    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6
}
