package com.example.quizier_compose_.ui.add_quiz

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizier_compose_.util.DashboardNavigation
import com.example.quizier_compose_.util.NavigationItems


@Composable
fun AddQuizScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ImageSection(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        ScreenCenterSection(navController = navController)
    }
}

@Composable
fun ImageSection(
    modifier: Modifier = Modifier
) {
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                imageUri.value = it
            }
        }
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val buttonVisibility = remember { mutableStateOf(true) }
    Column(modifier = modifier) {
        imageUri.value?.let {
            buttonVisibility.value = false

            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images.Media.getBitmap(
                    LocalContext.current.contentResolver,
                    it
                )
            } else {
                val source =
                    ImageDecoder.createSource(LocalContext.current.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
            bitmap.value?.let { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .offset(y = 25.dp)
                        .align(CenterHorizontally)
                        .clip(RoundedCornerShape(33.dp))
                        .border(1.dp, Color.Gray),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (buttonVisibility.value) {
            OutlinedButton(
                onClick = {
                    launcher.launch("image/*")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .offset(y = 60.dp)
                    .align(CenterHorizontally)
                    .padding(start = 30.dp, end = 30.dp)
                    .clip(RoundedCornerShape(8.dp)),

                border = BorderStroke(2.dp, color = MaterialTheme.colors.secondary),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.onSurface),

                ) {
                Text(text = "Pick Quiz Cover")
            }
        }

    }
}

@Composable
fun ScreenCenterSection(
    viewModel: AddQuizViewModel = hiltViewModel(),
    navController: NavController
) {
    val title = viewModel.quizTitle
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = title.value,
            onValueChange = { newTitle ->
                viewModel.onTitleChanged(newTitle)
            },
            label = {
                Text(text = "Quiz Title...")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 10.dp, end = 10.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            maxLines = 1,
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface)
        )
        ExtendedFloatingActionButton(
            text = { Text(text = "Next", color = MaterialTheme.colors.onSurface) },
            onClick = {
                if (title.value.isBlank()) {

                } else {
                    navController.navigate(DashboardNavigation.AddQuestions.route) {
                        popUpTo(NavigationItems.AddQuiz.route) {
                            inclusive = true
                        }
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            },
            modifier = Modifier
                .align(End)
                .offset(y = 100.dp)
                .padding(25.dp)
        )
    }
}