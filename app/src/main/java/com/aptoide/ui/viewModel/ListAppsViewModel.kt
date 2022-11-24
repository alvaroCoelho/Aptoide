package com.aptoide.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aptoide.data.app.model.AppModel
import com.aptoide.data.app.model.AppModelReponse
import com.aptoide.ui.useCase.GetListAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListAppsViewModel @Inject constructor(
    private val getListAppsUseCase: GetListAppsUseCase
): ViewModel(){

    private val _list: MutableState<ResourceState> = mutableStateOf(ResourceState.Loading)
    val list: State<ResourceState> = _list

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        loadListApps()
    }

    private suspend fun loadListApps() {
        try {
            val response = getListAppsUseCase.invoke()
            _list.value = handleResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _list.value = ResourceState.Error("Conection error")
                else -> _list.value = ResourceState.Error("data error")
            }
        }
    }

    private fun handleResponse(response: Response<AppModelReponse>): ResourceState {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                return ResourceState.Success(values.listApps.dataSet.appModelAll.data.appModelList.list)
            }
        }
        return ResourceState.Error(response.message())
    }
}

sealed interface ResourceState {
    object Loading : ResourceState
    class Success(
        val allApps: List<AppModel>
    ) : ResourceState

    class Error(
        val Message: String
    ) : ResourceState

    object Empty : ResourceState

}


