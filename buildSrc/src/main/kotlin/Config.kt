object Config {

    object Debug {

        const val IS_MINIFY_ENABLED = false
        const val IS_SHRINK_RESOURCES = false
    }

    object Release {

        const val IS_MINIFY_ENABLED = true
        const val IS_SHRINK_RESOURCES = true

        object Module {

            const val IS_MINIFY_ENABLED = false
        }
    }
}
