package com.example.flowexample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowexample.models.User
import com.example.flowexample.repositories.UserRepository
import com.example.flowexample.utils.Result
import com.example.flowexample.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor (private val userRepository: UserRepository) : ViewModel() {

    private val userStateMutable = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val userState: StateFlow<UiState<List<User>>> = userStateMutable

    fun getUsers() {
        viewModelScope.launch {
            userRepository.getUserData()
                .catch { exception ->
                    userStateMutable.value = UiState.Error(exception.localizedMessage ?: "Unknown Error")
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> userStateMutable.value = UiState.Success(result.data)
                        is Result.Error -> userStateMutable.value = UiState.Error(result.exception.localizedMessage ?: "Unknown Error")
                    }
                }
        }
    }

    sealed class MainState {
        object Loading:MainState()

        data class Success(val userList:List<User>):MainState()

        data class Error(val message:String):MainState()
    }


}