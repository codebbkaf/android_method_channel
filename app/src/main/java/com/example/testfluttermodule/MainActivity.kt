package com.example.testfluttermodule

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.testfluttermodule.MyFlutter3Activity.Companion.POOL_ID
import com.example.testfluttermodule.ui.theme.TestFlutterModuleTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            TestFlutterModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Go Flutter flow1 Page",
                            modifier = Modifier.clickable {
                                startActivity(
                                    Intent(context, MyFlutterActivity::class.java)
                                )
                            }
                        )

                        Divider(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Go Flutter flow2 Page",
                            modifier = Modifier.clickable {
                                startActivity(
                                    Intent(context, MyFlutter2Activity::class.java)
                                )
                            }
                        )

                        Divider(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Get Token",
                            modifier = Modifier.clickable {
                                startActivity(
                                    Intent(context, MyFlutter3Activity::class.java).apply {
                                        putExtra(POOL_ID, "pool111111111")
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
