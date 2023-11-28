package fr.bijay.moduleconsumerandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
//    lateinit var flutterEngine : FlutterEngine

    override fun onCreate() {
        super.onCreate()

//        flutterEngine = FlutterEngine(this)
//
//        flutterEngine
//            .dartExecutor
//            .executeDartEntrypoint(
//                DartExecutor.DartEntrypoint.createDefault()
//            )
//
//        FlutterEngineCache
//            .getInstance()
//            .put(AppConstants.FULL_SCREEN_ENGINE_ID, flutterEngine)

    }
}