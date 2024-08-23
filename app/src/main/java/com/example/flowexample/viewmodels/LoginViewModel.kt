package com.example.flowexample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowexample.models.User
import com.example.flowexample.repositories.LoginRepository
import com.example.flowexample.utils.Result
import com.example.flowexample.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _userAuthStatus = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val userAuthStatus: StateFlow<UiState<List<User>>> = _userAuthStatus

    fun checkForUserAuthentication(userId: Int) {
        viewModelScope.launch {
            loginRepository.checkForUserAuthentication(userId).collect { uiState ->
                when (uiState) {
                    is Result.Success -> _userAuthStatus.value = UiState.Success(uiState.data)
                    is Result.Error -> _userAuthStatus.value =
                        UiState.Error(uiState.exception.localizedMessage ?: "Unknown Error")
                }
            }
        }
    }
}