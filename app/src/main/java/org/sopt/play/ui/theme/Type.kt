package org.sopt.play.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.play.R

val Pretendard = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
)

// 행간 120%, 자간 -1%
val b28 = TextStyle(
    fontFamily = Pretendard,
    fontSize = 28.sp,
    lineHeight = 1.2.em,
    letterSpacing = (-0.01).em,
    fontWeight = FontWeight.Bold,
)

val m18 = TextStyle(
    fontFamily = Pretendard,
    fontSize = 18.sp,
    lineHeight = 1.2.em,
    letterSpacing = (-0.01).em,
    fontWeight = FontWeight.Medium,
)

val sb16 = TextStyle(
    fontFamily = Pretendard,
    fontSize = 16.sp,
    lineHeight = 1.2.em,
    letterSpacing = (-0.01).em,
    fontWeight = FontWeight.SemiBold,
)

val m14 = TextStyle(
    fontFamily = Pretendard,
    fontSize = 14.sp,
    lineHeight = 1.2.em,
    letterSpacing = (-0.01).em,
    fontWeight = FontWeight.Medium,
)

val sb14 = TextStyle(
    fontFamily = Pretendard,
    fontSize = 14.sp,
    lineHeight = 1.2.em,
    letterSpacing = (-0.01).em,
    fontWeight = FontWeight.SemiBold,
)

val Typography = Typography()
