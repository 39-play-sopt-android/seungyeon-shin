package org.sopt.play.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.Gray1
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.sb14
import org.sopt.play.util.noRippleClickable

@Composable
fun PlayButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100.dp))
            .background(if (enabled) Black else Gray1)
            .noRippleClickable(
                enabled = enabled,
                onClick = onClick,
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = sb14,
            color = if (enabled) Gray1 else Gray3,
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
            PlayButton(
                text = "로그인",
                enabled = false,
                onClick = {},
            )
            PlayButton(
                text = "로그인",
                onClick = {},
            )
        }
    }
}
