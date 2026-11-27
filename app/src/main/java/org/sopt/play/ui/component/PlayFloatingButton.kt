package org.sopt.play.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.Red
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.util.noRippleClickable

@Composable
fun PlayFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(60.dp)
            .shadow(6.dp, CircleShape) // 피그마 drop shadow
            .clip(CircleShape)
            .background(Red)
            .noRippleClickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_plus),
            contentDescription = "게시글 작성",
            tint = White,
            modifier = Modifier.size(28.dp),
        )
    }
}

@Preview
@Composable
private fun PlayFloatingButtonPreview() {
    PlaySoptTheme {
        PlayFloatingButton(
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
