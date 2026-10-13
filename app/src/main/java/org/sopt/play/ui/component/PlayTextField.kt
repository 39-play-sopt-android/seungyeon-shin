package org.sopt.play.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
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
fun PlayTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    isPassword: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val borderColor = when {
        !errorMessage.isNullOrBlank() -> Red
        isFocused -> Gray5
        else -> Gray2
    }

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

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = m18.copy(color = if (!errorMessage.isNullOrBlank()) Gray6 else Gray5),
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(Gray5),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White, RoundedCornerShape(12.dp))
                        .border(2.dp, borderColor, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = m18,
                            color = Gray2,
                        )
                    }
                    innerTextField()
                }
            },
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
            PlayTextField(
                label = "이메일 주소",
                value = "",
                onValueChange = {},
                placeholder = "abc@email.com",
            )
            PlayTextField(
                label = "이메일 주소",
                value = "abc.com",
                onValueChange = {},
                placeholder = "abc@email.com",
                errorMessage = "올바른 이메일을 입력해주세요.",
            )
            PlayTextField(
                label = "비밀번호",
                value = "123456",
                onValueChange = {},
                placeholder = "6자 이상의 비밀번호",
                isPassword = true,
            )
        }
    }
}
