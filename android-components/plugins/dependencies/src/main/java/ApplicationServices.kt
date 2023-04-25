/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// These lines are generated by android-components/automation/application-services-nightly-bump.py
val VERSION = "114.20230425050331"
val CHANNEL = ApplicationServicesChannel.NIGHTLY

object ApplicationServicesConfig {
    val version = VERSION
    val channel = CHANNEL
    val groupId = when (channel) {
        ApplicationServicesChannel.RELEASE -> "org.mozilla.appservices"
        ApplicationServicesChannel.STAGING -> "org.mozilla.appservices"
        // Nightly channels use a different group id to keep the the packages separate
        ApplicationServicesChannel.NIGHTLY -> "org.mozilla.appservices.nightly"
        ApplicationServicesChannel.NIGHTLY_STAGING -> "org.mozilla.appservices.nightly"
    }
}

/**
 * Enum for GeckoView release channels.
 *
 * This determines which Maven Repository.  Each channel is uploaded to a separate Maven repository.
 */
enum class ApplicationServicesChannel {
    // Used for official, non-nightly, releases
    RELEASE,
    // Used for nightly releases
    NIGHTLY,
    // Used for preview releases for PRs
    STAGING,
    // Used for nightly releases
    NIGHTLY_STAGING,
}