package io.github.pksokolowski.flow_utils_2

import android.app.Application
import android.view.View
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
fun View.clicks(): Flow<Unit> {
    return callbackFlow {
        setOnClickListener { _ ->
            trySend(Unit).isSuccess
        }
        awaitClose { setOnClickListener(null) }
    }
}

/**
 * Early experimental feature, do not use in production.
 */
@ExperimentalCoroutinesApi
@CheckResult
fun View.clicksPoc(): Flow<Unit> {
    initExperimentalFeatures(context.applicationContext as Application)
    return callbackFlow {
        setOnClickListener { _ ->
            trySend(Unit).isSuccess
        }
        awaitClose { setOnClickListener(null) }
    }
}