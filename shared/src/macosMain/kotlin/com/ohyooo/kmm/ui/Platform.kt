package com.ohyooo.kmm.ui

import platform.Foundation.NSProcessInfo

actual class Platform actual constructor() {
    actual val platform: String = NSProcessInfo.processInfo.operatingSystemVersionString
}