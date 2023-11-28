# ModuleConsumerAndroid

Integration of module to Native Android Project


Need to place the repo directory to same location as root of your native project. Repo folder will be provided by SDK team.


Add following to your app level gradle file
```
repositories {
    maven {
        url '../../repo'
    }
    maven {
        url "$storageUrl/download.flutter.io"
    }
}
```



Add following to your app level gradle dependency section.
```	
 implementation 'com.bijay.example:flutter_release:1.0'
```



Create and initialize flutter engine. Make sure the below code is executed only single time. Place it in locations like Application class or DI modules.
```
val flutterEngine = FlutterEngine(appContext)
flutterEngine
    .dartExecutor
    .executeDartEntrypoint(
        DartExecutor.DartEntrypoint.createDefault()
    )
FlutterEngineCache
    .getInstance()
    .put(AppConstants.FULL_SCREEN_ENGINE_ID, flutterEngine)
```





	
Initialize Method channel pointed to channel name provided using above flutter engine where the data is required.
```	   
 private val methodChannel: MethodChannel = MethodChannel(
        flutterEngine.dartExecutor.binaryMessenger,
        AppConstants.CHANNEL_DUMMY
    )
```


Invoke method name provided for calling sdk particular method.
```
methodChannel.invokeMethod(AppConstants.METHOD_GET_DUMMY, "DEFAULT DATA", object : MethodChannel.Result {
    override fun success(result: Any?) {
        if (result == null) {
            cont.resumeWithException(Exception("Empty Model"))
        } else {
            try {
                val data: String = result as String
                cont.resume(data)
            } catch (e: Exception) {
                cont.resumeWithException(e)
            }
        }
    }
```









For above sample, you can use following channel name and method name.
const val FULL_SCREEN_ENGINE_ID = "fullScreenEngineId"
const val CHANNEL_DUMMY = "CHANNEL_DUMMY"
const val METHOD_GET_DUMMY = "METHOD_GET_DUMMY"




Note: Along with this documentation, repo folder and sample application will be provided.
