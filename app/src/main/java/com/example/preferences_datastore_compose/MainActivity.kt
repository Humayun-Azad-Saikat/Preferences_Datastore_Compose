package com.example.preferences_datastore_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.preferences_datastore_compose.ui.theme.Preferences_DataStore_ComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val prefUtils by lazy {
        PrefUtils(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Preferences_DataStore_ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MainScreen(modifier = Modifier.padding(innerPadding),prefUtils)

                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier,prefUtils: PrefUtils){

    val key = remember { mutableStateOf("") }
    val value = remember { mutableStateOf("") }
    val storedValue = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        TextField(value = key.value, onValueChange = {key.value = it})
        TextField(value = value.value, onValueChange = {value.value = it})

        Spacer(modifier.padding(12.dp))

        Button(onClick = {
            scope.launch{
                prefUtils.saveString(key.value,value.value)
            }
        }) {
            Text("Store value")
        }

        Text(text = storedValue.value, style = MaterialTheme.typography.headlineLarge)

        Button(onClick ={
            scope.launch{
               storedValue.value = prefUtils.getString(key.value).toString()
            }
        } ) {
            Text("Fetch value")
        }


    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Preferences_DataStore_ComposeTheme {

    }
}