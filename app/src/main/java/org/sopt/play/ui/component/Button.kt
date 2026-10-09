package org.sopt.play.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.Gray1
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.sb14

@Composable
fun Button(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // 함수 이름이 같아서 material3 Button은 전체 이름으로 호출
    androidx.compose.material3.Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(100.dp),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Black,
            contentColor = Gray1,
            disabledContainerColor = Gray1,
            disabledContentColor = Gray3,
        ),
    ) {
        Text(
            text = text,
            style = sb14,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonPreview() {
    PlaySoptTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Button(text = "로그인", enabled = false, onClick = {})
            Button(text = "로그인", enabled = true, onClick = {})
        }
    }
}
