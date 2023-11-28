package fr.bijay.moduleconsumerandroid.data

interface FlutterBridge {
    suspend fun fetchDummyDatas(): String?
}