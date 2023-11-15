package com.example.redditapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditapp.domain.entity.Data
import com.example.redditapp.domain.usecase.TopUseCase
import com.example.redditapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val topUseCase: TopUseCase
) : ViewModel() {

    val uiState = MutableLiveData<UiState>()

    init {
        getAccessToken("")
    }

    fun getAccessToken(page: String) =
        viewModelScope.launch {
            topUseCase(page).collect {
                when (it) {
                    DataState.Loading -> {
                        uiState.value = UiState.Loading
                    }

                    is DataState.OtherError -> {
                        uiState.value = UiState.Error(it.error)
                    }

                    is DataState.Error -> {
                        it.exception.printStackTrace()
                        uiState.value =
                            UiState.Error(it.exception.message.orEmpty())
                    }

                    is DataState.Success -> {
                        uiState.value = UiState.Success(it.data.data)
                    }
                }
            }
        }

    sealed class UiState {
        object Loading : UiState()
        class Success(val data: Data) : UiState()
        class Error(val msg: String) : UiState()
    }
}