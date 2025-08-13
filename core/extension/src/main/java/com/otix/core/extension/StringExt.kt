package com.otix.core.extension

import android.util.Base64
import java.nio.ByteBuffer
import java.util.UUID

private const val CAPACITY = 16

fun String.toUUID(length: Int = 8): String {
    val uuid = UUID.nameUUIDFromBytes(this.toByteArray())
    val byteBuffer = ByteBuffer.allocate(CAPACITY)
    byteBuffer.putLong(uuid.mostSignificantBits)
    byteBuffer.putLong(uuid.leastSignificantBits)
    val base64 = Base64.encodeToString(
        byteBuffer.array(),
        Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP
    )
    val result = if (length <= base64.length) base64.substring(0, length) else base64
    return result.replace(
        oldValue = "-",
        newValue = ""
    ).replace(
        oldValue = "_",
        newValue = ""
    ).uppercase()
}