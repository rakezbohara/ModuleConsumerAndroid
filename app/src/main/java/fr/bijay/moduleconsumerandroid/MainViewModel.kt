package fr.bijay.moduleconsumerandroid

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.bijay.moduleconsumerandroid.domain.repositories.DummyRepository
import fr.bijay.moduleconsumerandroid.domain.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val dummyRepository: DummyRepository,
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<String?>> = MutableLiveData()

    val dataState: LiveData<DataState<String?>>
        get() = _dataState

    fun fetchDatas() {
        viewModelScope.launch {
            dummyRepository.getDummyDatas()
                .onEach { dataState ->
                    _dataState.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }
}