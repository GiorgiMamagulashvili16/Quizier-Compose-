package com.example.quizier_compose_.ui.signup_screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navArgument
import com.example.quizier_compose_.model.auth.SignUpResponse
import com.example.quizier_compose_.util.Constants.LOGIN_SCREEN
import com.example.quizier_compose_.util.Constants.SPLASH_SCREEN
import com.example.quizier_compose_.util.Resource
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        TopSection(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 100.dp)
                .fillMaxHeight(0.2f)
        )
        Spacer(modifier = Modifier.height(200.dp))
        TextFieldSection(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController
        )
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.TopCenter) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.surface,
                        fontSize = 31.sp
                    )
                ) {
                    append("S")
                }
                append("ign")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.surface,
                        fontSize = 31.sp
                    )
                ) {
                    append(" U")
                }
                append("p")
            },
            color = MaterialTheme.colors.onBackground,
            fontSize = 31.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun TextFieldSection(
    modifier: Modifier = Modifier,
    vm: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {

    val email = vm.email
    val password = vm.password
    val repPassword = vm.repeatPassword

    var passwordvisibility: Boolean by remember {
        mutableStateOf(false)
    }

    Column {
        OutlinedTextField(
            value = email.value,
            onValueChange = { newEmail ->
                vm.onEmailChanged(newEmail)
            },
            label = {
                Text(text = "Email Address...")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 10.dp, end = 10.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            maxLines = 1,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            },
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface)
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = password.value,
            onValueChange = { newPassword ->
                vm.onPasswordChanged(newPassword)
            },
            label = {
                Text(text = "Password...")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 10.dp, end = 10.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            maxLines = 1,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "password",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            },
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
            visualTransformation = if (passwordvisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {

                val icon = if (passwordvisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    passwordvisibility = !passwordvisibility
                }) {
                    Icon(imageVector = icon, contentDescription = "visibility")
                }
            }

        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = repPassword.value,
            onValueChange = { newPassword ->
                vm.onRepeatPassChanged(newPassword)
            },
            label = {
                Text(text = "Repeat Password...")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 10.dp, end = 10.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            maxLines = 1,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "password",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            },
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
            visualTransformation = if (passwordvisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {

                val icon = if (passwordvisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    passwordvisibility = !passwordvisibility
                }) {
                    Icon(imageVector = icon, contentDescription = "visibility")
                }
            }
        )
        Spacer(modifier = Modifier.height(45.dp))
        ScreenBottomSection(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            email = email.value,
            password = password.value,
            repPassword = repPassword.value
        )
    }
}

@Composable
fun ScreenBottomSection(
    navController: NavController,
    modifier: Modifier = Modifier,
    vm: SignUpViewModel = hiltViewModel(),
    password: String,
    email: String,
    repPassword: String
) {
    val response = vm.signUpResponse.value
    val isLoading = vm.isLoading.value
    val errorMessage = vm.error.value

    val scaffoldState  = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(),scaffoldState = scaffoldState,backgroundColor = MaterialTheme.colors.primaryVariant) {
        Box() {
            Button(
                onClick = {
                   vm.signUp(email,password)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 17.dp, end = 17.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                )

            ) {
                Text(
                    text = "Sign Up",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onSurface
                )
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {
                    if (isLoading)
                        CircularProgressIndicator()
                    if (errorMessage.isNotEmpty()) {
                        Text(text = errorMessage, color = Color.Red, fontSize = 21.sp)
                    }
                    if (response is Resource.Success) {
                        navController.navigate(LOGIN_SCREEN) {
                            popUpTo(SPLASH_SCREEN) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CreateSnackBar(
    snackbarHostState: SnackbarHostState,
    message: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SnackbarHost(hostState = snackbarHostState,
            snackbar = {
                Snackbar(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = message)
                }
            }
        )
    }
}

