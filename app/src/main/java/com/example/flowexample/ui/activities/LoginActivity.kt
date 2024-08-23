package com.example.flowexample.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.flowexample.databinding.ActivityLoginBinding
import com.example.flowexample.di.components.RandomString
import com.example.flowexample.ui.helpers.RandomDataCollector
import com.example.flowexample.utils.UiState
import com.example.flowexample.utils.collectWhenStarted
import com.example.flowexample.viewmodels.LoginViewModel
import javax.inject.Inject

class LoginActivity : RootActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    @Inject
    @RandomString lateinit var baseUrl:String

    @Inject
    lateinit var randomDataCollector: RandomDataCollector

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActivity()
    }

    private fun initActivity(){

        Log.d(TAG, "initActivity:$baseUrl")

        Log.d(TAG, "initActivity:${randomDataCollector.collectSomeRandomData()}")

        binding.button.setOnClickListener {
            try {
                loginViewModel.checkForUserAuthentication(binding.editTextText.text.toString().toInt())
            }catch (e:NumberFormatException){
                Toast.makeText(this,"Enter Valid Number..",Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.userAuthStatus.collectWhenStarted(this@LoginActivity){uiState->
            when (uiState) {
                UiState.Loading -> Log.d(TAG, "Loading..")
                is UiState.Success -> startActivity(Intent(this,MainActivity::class.java))
                is UiState.Error -> Toast.makeText(this,uiState.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object{
        private const val TAG="LoginActivity"
    }
}