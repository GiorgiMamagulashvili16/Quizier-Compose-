package com.example.quizier_compose_.ui.login_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizier_compose_.util.Constants.SIGNUP_SCREEN


@Composable
fun LogInScreen(
    navController: NavController
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        TopSection(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 100.dp)
                .fillMaxHeight(0.2f),
        )
        Spacer(modifier = Modifier.height(200.dp))
        TextFieldSection(Modifier.fillMaxSize(),navController = navController)
    }

}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = TopCenter) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.surface,
                        fontSize = 45.sp,

                        )
                ) {
                    append("Q")
                }
                append("uizier")
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
    vm: LogInViewModel = hiltViewModel(),
    navController: NavController
) {
    val email = vm.email
    val password = vm.password
    val isChecked = vm.isChecked
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
        Spacer(modifier = Modifier.height(45.dp))

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
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.padding(9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = { newValue ->
                    vm.onCheckBoxChanged(newValue)
                },
                modifier = Modifier

                    .width(40.dp)
                    .height(40.dp),
                colors = CheckboxDefaults.colors(
                    MaterialTheme.colors.secondary,
                    checkmarkColor = MaterialTheme.colors.onSurface
                ),
            )
            Text(text = "Remember Me", color = MaterialTheme.colors.onSurface)
        }
        Spacer(modifier = Modifier.height(35.dp))
        ButtonSection(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary),
            email = email.value,
            password = password.value,
            isChecked = isChecked.value,
            navController = navController
        )
    }
}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    isChecked: Boolean,
    navController: NavController
) {
    Column {
        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(60.dp)
              ,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary
            )

        ) {
            Text(
                text = "Log In",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface
            )
        }
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = buildAnnotatedString {
                append("Do not have an account?")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.surface,
                        fontSize = 19.sp
                    )
                ) {
                    append(" Sign Up!")
                }
            },
            fontSize = 16.sp,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(SIGNUP_SCREEN)
                }
        )

    }
}