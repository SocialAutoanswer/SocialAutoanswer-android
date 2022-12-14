package ru.bibaboba.core_utils

import android.text.Editable
import android.text.TextWatcher

interface SimpleTextWatcher: TextWatcher {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
}