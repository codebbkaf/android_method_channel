package com.example.testfluttermodule

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

class MyFlutter3Activity : FlutterActivity() {
    private var myAccessToken = ""

    /**
     * 傳入 Pool ID
     */
    override fun getInitialRoute(): String {
        val poolId = intent.getStringExtra(POOL_ID).orEmpty()
        return "/getToken/$poolId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAccessToken = UUID.randomUUID().toString()
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            when (call.method) {
                CLOSE_PAGE -> {
                    finish()
                }

                /**
                 * 取得 AccessToken
                 */
                GET_ACCESS_TOKEN -> {
                    fetchToken(
                        onSuccess = { token ->
                            result.success(token)
                        },
                        onError = {
                            result.error("UNAVAILABLE", "Token not available.", null)
                        },
                    )
                }

                /**
                 * 刷新 AccessToken
                 */
                REFRESH_ACCESS_TOKEN -> {
                    refreshToken(
                        onSuccess = { newToken ->
                            result.success(newToken)
                        },
                        onError = {
                            result.error("UNAVAILABLE", "Token not available.", null)

                        },
                    )
                }

                else -> {
                    result.notImplemented()
                }
            }
        }
    }

    private fun fetchToken(
        onSuccess: (String) -> Unit,
        onError: () -> Unit,
    ) {
        lifecycleScope.launch {
            runCatching {
                onSuccess(myAccessToken)
            }.onFailure {
                onError()
            }
        }
    }

    private fun refreshToken(
        onSuccess: (String) -> Unit,
        onError: () -> Unit,
    ) {
        lifecycleScope.launch {
            runCatching {
                delay(300L)
                myAccessToken = UUID.randomUUID().toString()
                onSuccess(myAccessToken)
            }.onFailure {
                onError()
            }
        }
    }

    companion object {
        private const val CHANNEL = "com.swimple.channel"
        private const val CLOSE_PAGE = "closePage"
        private const val GET_ACCESS_TOKEN = "getAccessToken"
        private const val REFRESH_ACCESS_TOKEN = "refreshAccessToken"
        const val POOL_ID = "poolId"
    }
}
