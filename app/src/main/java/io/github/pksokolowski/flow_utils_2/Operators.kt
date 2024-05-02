package io.github.pksokolowski.flow_utils_2

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<T>.filterDoubleTapAlternative(periodMillis: Long): Flow<Unit> {
    require(periodMillis > 0) { "Double tap acceptance period should be positive" }
    var lastStamp = -1L
    return transform {
        val now = System.currentTimeMillis()
        val sinceLast = now - lastStamp
        lastStamp = now
        if (sinceLast <= periodMillis) {
            emit(Unit)
            lastStamp = -1
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@FlowPreview
fun <T, R> Flow<T>.mapConcurrently(block: (T) -> R): Flow<R> =
    this.flatMapMerge(Runtime.getRuntime().availableProcessors()) { item ->
        flow {
            emit(block(item))
        }
    }