object Version {

    private const val MAJOR = 1
    private const val MINOR = 2
    private const val PATCH = 7
    private const val SUFFIX = "beta"

    const val APPLICATION_ID = "com.otix"
    const val JVM_TARGET = "11"
    const val MIN_SDK = 28
    const val TARGET_SDK = 36
    const val COMPILE_SDK = 36

    val VERSION_NAME: String
        get() = buildString {
            append("$MAJOR.$MINOR.$PATCH")
            if (SUFFIX.isNotEmpty()) {
                append(".$SUFFIX")
            }
        }

    val VERSION_CODE: Int
        get() = 120 + (MAJOR + MINOR) + PATCH
}
