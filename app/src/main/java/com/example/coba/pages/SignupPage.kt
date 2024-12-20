package com.example.coba.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coba.model.AuthState
import com.example.coba.model.AuthViewModel
import com.example.coba.model.TaskViewModel
import com.example.coba.ui.theme.WaveBackground2
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPage(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    authViewModel: AuthViewModel? = null,
    taskViewModel: TaskViewModel? = null
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val authState = authViewModel?.authState?.observeAsState()
    val context = LocalContext.current

    // Handle authentication state changes
    LaunchedEffect(authState?.value) {
        when (authState?.value) {
            is AuthState.Authenticated -> {
                navController?.navigate("home") {
                    popUpTo("signup") { inclusive = true }
                }
                Toast.makeText(context, "Signed up successfully", Toast.LENGTH_SHORT).show()
            }
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        WaveBackground2()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "SIGN UP",
                fontSize = 24.sp,
                color = Color(0xFF7E57C2),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "FOR YOUR ACCOUNT",
                fontSize = 16.sp,
                color = Color(0xFF7E57C2),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("someone@gmail.com", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                modifier = Modifier
                    .width(280.dp)
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(45.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE1BEE7),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                modifier = Modifier
                    .width(280.dp)
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(45.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE1BEE7),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = { Text("Confirm Password", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                modifier = Modifier
                    .width(280.dp)
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(45.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE1BEE7),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = { authViewModel?.signup(email, password, confirmPassword) },
                modifier = Modifier
                    .width(280.dp)
                    .padding(vertical = 16.dp)
                    .clip(RoundedCornerShape(45.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7E57C2)
                )
            ) {
                Text("SIGN UP", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Link to Sign In page
            val annotatedText = buildAnnotatedString {
                append("Do you have an account? ")
                withStyle(style = SpanStyle(color = Color(0xFF7E57C2), fontWeight = FontWeight.Bold)) {
                    append("Sign in")
                }
            }

            ClickableText(
                text = annotatedText,
                onClick = {
                    navController?.navigate("signin") // Adjust "signin" to match your route
                },
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignupPagePreview() {
    SignupPage()
}
