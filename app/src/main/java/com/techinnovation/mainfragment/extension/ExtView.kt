package com.techinnovation.mainfragment.extension

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.techinnovation.mainfragment.Constants
import com.techinnovation.mainfragment.Utils
import com.techinnovation.mainfragment.DialogBox.Companion.applicationContext
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

fun View.isVisible() = visibility == View.VISIBLE

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visibility(flag: Boolean) {
    visibility = if (flag) View.VISIBLE else View.GONE
}


fun View?.gone() {
    this?.let { it.visibility = View.GONE }
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setSingleClickListener(delayMillis: Long = 700, action: (View) -> Unit) {
    setOnClickListener {
        isClickable = false
        action.invoke(this)
        postDelayed({
            isClickable = true
        }, delayMillis)
    }
}

fun View.setBackgroundTint(@ColorRes resId: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, resId)
}

fun calculateDuration(total: Long): Long {
    return when (applicationContext.preference.getIntDecrypted(
        Constants.ANIMATION_SPEED, 0
    )) {
        0 -> Utils.findPercentage(100f, total.toFloat()).toLong()
        1 -> Utils.findPercentage(75f, total.toFloat()).toLong()
        2 -> Utils.findPercentage(50f, total.toFloat()).toLong()
        else -> total
    }
}

val Context.preference: SharedPreferences
    get() {
        return getSharedPreferences(
            Constants.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }

fun SharedPreferences.getIntDecrypted(key: String, defaultValue: Int): Int {
    return getString(key, null)?.decrypt()?.toIntOrNull()
        ?: defaultValue
}

fun String.decrypt(): String {
    return try {
        val message = hexStringToByteArray(this)
        val keyBytes = getKeyAES().toByteArray(StandardCharsets.UTF_8)
        val key: SecretKey = SecretKeySpec(keyBytes, "AES")
        val iv = IvParameterSpec(ByteArray(16))
        val decipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        decipher.init(Cipher.DECRYPT_MODE, key, iv)
        val plainText = decipher.doFinal(message)
        String(plainText, StandardCharsets.UTF_8)
    } catch (e: Exception) {
        this
    }
}

// Encryption decruption
private fun hexStringToByteArray(s: String): ByteArray {
    val len = s.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        data[i / 2] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
        i += 2
    }
    return data
}

private fun getKeyAES(): String {
//    val strKey = APIURLs.MOBILE_OIL
     val strKey = Constants.MOBILE_OIL
    val interimKeyBytes = strKey.toByteArray(StandardCharsets.UTF_8)
    val keyBytes = interimKeyBytes.copyOf(32)
    return String(keyBytes, StandardCharsets.US_ASCII)
}
fun BottomSheetDialogFragment.show(manager: FragmentManager) {
    if (manager.isDestroyed) return
    show(manager, "BottomSheetDialogFragment")
}




