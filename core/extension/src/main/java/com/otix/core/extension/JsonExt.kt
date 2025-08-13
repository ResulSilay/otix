package com.otix.core.extension

import kotlinx.serialization.json.Json

inline fun <reified T> T.toJson(): String where T : Any {
    return Json.encodeToString(this)
}