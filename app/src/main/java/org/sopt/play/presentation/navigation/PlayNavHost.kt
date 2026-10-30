package org.sopt.play.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.play.presentation.home.HomeRoute
import org.sopt.play.presentation.login.LoginRoute
import org.sopt.play.presentation.register.RegisterRoute

@Composable
fun PlayNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val context = LocalContext.current

    // 회원가입 정보: registerForActivityResult 대신 NavHost에서 상태로 보관
    var registeredEmail by remember { mutableStateOf("") }
    var registeredPassword by remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = Route.LOGIN,
        modifier = modifier,
    ) {
        composable(Route.LOGIN) {
            LoginRoute(
                onLoginClick = { email, password ->
                    // 로그인 성공 시 로그인 화면을 백스택에서 제거하고 홈으로 이동
                    if (email == registeredEmail && password == registeredPassword) {
                        navController.navigate(Route.HOME) {
                            popUpTo(Route.LOGIN) { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "이메일 또는 비밀번호가 올바르지 않아요.", Toast.LENGTH_SHORT).show()
                    }
                },
                onRegisterClick = { navController.navigate(Route.REGISTER) },
            )
        }
        composable(Route.REGISTER) {
            RegisterRoute(
                onRegisterClick = { name, email, password, passwordConfirm ->
                    registeredEmail = email
                    registeredPassword = password
                    navController.popBackStack() // 회원가입 화면 닫기 (기존 finish() 역할),
                },
            )
        }
        composable(Route.HOME) {
            HomeRoute()
        }
    }
}

