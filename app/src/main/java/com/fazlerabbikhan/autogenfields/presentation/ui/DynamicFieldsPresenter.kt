package com.fazlerabbikhan.autogenfields.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.fazlerabbikhan.autogenfields.R
import com.fazlerabbikhan.autogenfields.data.remote.user_detail_dto.UserDetailDto
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DynamicFieldsPresenter {

    @SuppressLint("InflateParams")
    fun generateFields(userDetailData: UserDetailDto, context: Context, containerLayout: LinearLayout) {
        val fieldData = mapOf(
            "Name" to userDetailData.name,
            "Username" to userDetailData.username,
            "Email" to userDetailData.email,
            "Phone" to userDetailData.phone,
            "Address" to userDetailData.address,
        )

        val inflater = LayoutInflater.from(context)

        for ((fieldName, fieldValue) in fieldData) {
            val inputFieldLayout = inflater.inflate(R.layout.input_field_layout, null)
            val textInputLayout = inputFieldLayout.findViewById<TextInputLayout>(R.id.textInputLayout)
            val textInputEditText = inputFieldLayout.findViewById<TextInputEditText>(R.id.textInputEditText)

            textInputLayout.hint = fieldName

            textInputEditText.setText(fieldValue.toString())
            containerLayout.addView(inputFieldLayout)
        }
    }
}