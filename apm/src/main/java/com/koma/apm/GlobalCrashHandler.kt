package com.koma.apm

import android.os.Process

class GlobalCrashHandler : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {
        // todo handle uncaught exception
        Process.killProcess(Process.myPid())
    }
}