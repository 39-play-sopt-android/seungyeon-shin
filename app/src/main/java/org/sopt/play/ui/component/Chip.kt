package org.sopt.play.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.Gray2
import org.sopt.play.ui.theme.Gray5
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.White
import org.sopt.play.ui.theme.m14
import org.sopt.play.util.noRippleClickable

@Composable
fun Chip(
    text: String,
    selected: Boolean, // 선택 상태가 있는 경우만
    onClick: () -> Unit, // 눌리는 경우만
    modifier: Modifier = Modifier, // 항상 마지막, 기본값 Modifier
) {
    val backgroundColor = if (selected) Gray5 else White
    val borderColor = if (selected) Gray5 else Gray2
    val textColor = if (selected) White else Gray5

/*
바깥 상자 = Box (글자 하나 감싸는 상자니까)
상자 꾸미기는 전부 modifier에 순서대로: 테두리 → 배경 → 클릭 → 여백
선택 여부에 따라 색만 바뀌니까 if (selected) A else B로 색을 고르기
*/
    Box(
        modifier = modifier
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(6.dp))
            .background(color = backgroundColor, shape = RoundedCornerShape(6.dp))
            .noRippleClickable { onClick() } // 1주차 util, 누를 때 물결(리플) 효과 없이 클릭만
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(text = text, style = m14, color = textColor)
    }
}

@Preview(showBackground = true)
@Composable
private fun ChipPreview() {
    PlaySoptTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Chip(text = "칩", selected = false, onClick = {})
            Chip(text = "칩", selected = true, onClick = {})
        }
    }
}