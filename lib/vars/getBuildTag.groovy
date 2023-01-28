import java.text.SimpleDateFormat

/**
 * getBuildTag generates a build tag using version, date, and
 * short git hash.
 */
def call(Map opts = [:]) {
    [ opts.version, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()),
        getCommitHash()[0..6] ].join("-")
}