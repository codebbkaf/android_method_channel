package com.example.testfluttermodule

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MyFlutterActivity : FlutterActivity() {
    override fun getInitialRoute(): String {
        return "/detail/pool000000"
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method == CLOSE_PAGE) {
                finish()
            } else {
                result.notImplemented()
            }
        }
    }

    companion object {
        private const val CHANNEL = "com.swimple.channel"
        private const val CLOSE_PAGE = "closePage"
    }
}
