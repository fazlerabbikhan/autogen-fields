package com.fazlerabbikhan.autogenfields.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fazlerabbikhan.autogenfields.R
import com.fazlerabbikhan.autogenfields.data.remote.user_detail_dto.UserDetailDto
import com.fazlerabbikhan.autogenfields.domain.repository.UserRepository
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var presenter: DynamicFieldsPresenter
    @Inject
    lateinit var userRepository: UserRepository
    private var userId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = DynamicFieldsPresenter()

        val containerLayout = findViewById<LinearLayout>(R.id.containerLayout)

        // Fetch the user data asynchronously
        lifecycleScope.launch {
            try {
                val userData = userRepository.getUserData(userId)
                Log.d("UserData", "$userData")
                presenter.generateFields(userData, this@MainActivity, containerLayout)
            } catch (e: Exception) {
                // Handle the error case
            }
        }

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            val fieldValues = getFieldValues(containerLayout)
            Log.d("FieldValues", fieldValues.toString())

            val updatedUserDetailDto = createUserDetailDto(fieldValues)
            updateUserData(updatedUserDetailDto)
        }
    }

    private fun getFieldValues(containerLayout: LinearLayout): List<String> {
        val fieldValues = mutableListOf<String>()
        for (i in 0 until containerLayout.childCount) {
            val inputFieldLayout = containerLayout.getChildAt(i) as ViewGroup
            val textInputEditText = inputFieldLayout.findViewById<TextInputEditText>(R.id.textInputEditText)
            val fieldValue = textInputEditText.text.toString()
            fieldValues.add(fieldValue)
        }
        return fieldValues
    }

    private fun createUserDetailDto(fieldValues: List<String>): UserDetailDto {
        val (name, username, email, phone, address) = fieldValues
        return UserDetailDto(address = address, email = email, id = userId, name = name, outCode = "", outMessage = "", phone = phone, username = username)
    }

    private fun updateUserData(userDetailDto: UserDetailDto) {
        lifecycleScope.launch {
            try {
                val updatedUserData = userRepository.updateUserData(userId, userDetailDto)
                Log.d("UpdatedUserData", "$updatedUserData")
                // Handle success case
            } catch (e: Exception) {
                // Handle error case
            }
        }
    }
}

