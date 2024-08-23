package com.example.flowexample.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.flowexample.R
import com.example.flowexample.api.ApiService
import com.example.flowexample.api.RetrofitClient
import com.example.flowexample.repositories.UserRepository
import com.example.flowexample.utils.UiState
import com.example.flowexample.utils.collectWhenStarted
import com.example.flowexample.viewmodels.MainViewModel
import com.example.flowexample.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : RootActivity() {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var mainViewModel: MainViewModel



    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.getUsers()
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.userState.collectWhenStarted(this@MainActivity) { uiState ->
            when (uiState) {
                UiState.Loading -> Log.d(TAG, "Loading..")
                is UiState.Success -> Log.d(TAG, "${uiState.data[0]}")
                is UiState.Error -> Log.d(TAG, "ErrosIs : ${uiState.message}")
            }
        }
    }
}