package com.github.pksokolowski.flow_utils_2

import android.widget.Button
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        val watcher = doOnTextChanged { text, start, count, after ->
            trySend(text).isSuccess
        }
        awaitClose { removeTextChangedListener(watcher) }
    }
}

@ExperimentalCoroutinesApi
@CheckResult
fun Button.clicks(): Flow<Unit> {
    return callbackFlow {
        setOnClickListener { _ ->
            trySend(Unit).isSuccess
        }
        awaitClose { setOnClickListener(null) }
    }
}