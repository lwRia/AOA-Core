package com.example.aoacore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.aoacore.interfaces.UpdateNetwork
import com.app.aoacore.services.CoreService
import com.app.aoacore.services.NetworkService
import com.example.aoacore.ui.theme.AOACoreTheme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AOACoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        val appId: String = CoreService.getAppId(this)
        Log.d(TAG, "appId: $appId")

        val updateNetworkState = UpdateNetwork { isConnected ->
            if (isConnected) {
                Log.d(TAG, "hasNetworkConnection: $isConnected")
            } else {
                Log.d(TAG, "hasNetworkConnection: $isConnected")
            }
        }

        NetworkService.checkConnectivity(this, updateNetworkState)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AOACoreTheme {
        Greeting("Android")
    }
}