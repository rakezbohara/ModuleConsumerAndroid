package fr.bijay.moduleconsumerandroid.domain.repositories

import fr.bijay.moduleconsumerandroid.data.FlutterBridge
import fr.bijay.moduleconsumerandroid.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DummyRepository @Inject constructor(private val flutterBridge: FlutterBridge) {

    suspend fun getDummyDatas(): Flow<DataState<String?>> = flow {
        emit(DataState.Loading)

        try {
            val dummyModel = flutterBridge.fetchDummyDatas()
            if (dummyModel != null) {
                emit(DataState.Success(dummyModel))
            } else {
                emit(DataState.Error(Exception("Error while loading dummy model")))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
