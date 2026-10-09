package org.sopt.play.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.Gray2
import org.sopt.play.ui.theme.Gray5
import org.sopt.play.ui.theme.Gray6
import org.sopt.play.ui.theme.Red
import org.sopt.play.ui.theme.White
import org.sopt.play.ui.theme.m14
import org.sopt.play.ui.theme.m18
import org.sopt.play.ui.theme.sb16

@Composable
fun TextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    isPassword: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = label,
            style = sb16,
            color = Gray6,
            modifier = Modifier.padding(start = 8.dp),
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = m18,
            placeholder = { Text(text = placeholder, style = m18, color = Gray2) },
            isError = errorMessage != null,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                errorContainerColor = White,
                focusedBorderColor = Gray5,
                unfocusedBorderColor = Gray2,
                errorBorderColor = Red,
                focusedTextColor = Gray5,
                unfocusedTextColor = Gray5,
                errorTextColor = Gray6,
            ),
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                style = m14,
                color = Red,
                modifier = Modifier.padding(start = 8.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    PlaySoptTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            TextField(
                label = "이메일 주소",
                value = "",
                onValueChange = {},
                placeholder = "abc@email.com",
            )
            TextField(
                label = "이메일 주소",
                value = "abc.com",
                onValueChange = {},
                placeholder = "abc@email.com",
                errorMessage = "올바른 이메일을 입력해주세요.",
            )
            TextField(
                label = "비밀번호",
                value = "123456",
                onValueChange = {},
                placeholder = "6자 이상의 비밀번호",
                isPassword = true,
            )
        }
    }
}
