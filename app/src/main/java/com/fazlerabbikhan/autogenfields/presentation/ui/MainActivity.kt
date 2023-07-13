package com.fazlerabbikhan.autogenfields.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fazlerabbikhan.autogenfields.R
import com.fazlerabbikhan.autogenfields.domain.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var presenter: DynamicFieldsPresenter
    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = DynamicFieldsPresenter()

        val containerLayout = findViewById<LinearLayout>(R.id.containerLayout)

        // Fetch the user data asynchronously
        lifecycleScope.launch {
            try {
                val userData = userRepository.getUserData(1)
                Log.d("userData", "$userData")
                presenter.generateFields(userData, this@MainActivity, containerLayout)
            } catch (e: Exception) {
                // Handle the error case
            }
        }
    }
}

