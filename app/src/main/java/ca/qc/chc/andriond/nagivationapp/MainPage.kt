package ca.qc.chc.andriond.nagivationapp


import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController, modifier: Modifier = Modifier) {
    var username by rememberSaveable { mutableStateOf("") }
    var userAge by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Main Page") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Enter your name") },
                modifier = Modifier.width(300.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = userAge,
                onValueChange = { userAge = it },
                label = { Text("Enter your age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(300.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    if (username.isBlank() || userAge.isBlank()) {
                        Toast.makeText(context, "Please enter all data", Toast.LENGTH_SHORT).show()
                    } else {
                        try {
                            val ageInt = userAge.toInt()
                            val encodedName = Uri.encode(username)
                            navController.navigate("secondPage/$encodedName/$ageInt")
                        } catch (e: NumberFormatException) {
                            Toast.makeText(context, "Please enter a valid age", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                },
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Send", fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPagePreview() {
    val navController = rememberNavController()
    MainPage(navController = navController)
}