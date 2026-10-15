package org.sopt.play.presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.ui.theme.PlaySoptTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(text = "홈", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    PlaySoptTheme {
        HomeScreen()
    }
}
