package com.example.testfluttermodule

import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

/**
 * 這邊只是為了透過 FlutterEngine 來 Cache FlutterActivity
 */
class MyApplication : Application() {

    lateinit var flutterEngine: FlutterEngine

    override fun onCreate() {
        super.onCreate()

        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(this)

        /**
         * 初始路徑改寫在 MyFlutterActivity
         */
//        flutterEngine.navigationChannel.setInitialRoute("/listing_first_step")

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(FLUTTER_ENGINE_ID, flutterEngine)
    }

    companion object {
        const val FLUTTER_ENGINE_ID = "my_flutter_engine"
    }
}