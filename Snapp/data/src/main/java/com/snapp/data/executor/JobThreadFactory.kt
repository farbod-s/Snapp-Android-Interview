package com.snapp.data.executor

import java.util.concurrent.ThreadFactory

class JobThreadFactory : ThreadFactory {
    private var counter = 0

    override fun newThread(runnable: Runnable): Thread {
        return Thread(runnable, THREAD_NAME + counter++)
    }

    companion object {
        private const val THREAD_NAME = "android_"
    }
}