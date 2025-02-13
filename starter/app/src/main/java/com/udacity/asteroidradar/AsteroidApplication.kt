package com.udacity.asteroidradar

import android.app.Application
import androidx.work.WorkManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.ExistingPeriodicWorkPolicy
import com.udacity.asteroidradar.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AsteroidApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            recurringWork()
        }
    }

    private fun recurringWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .build()

        val repeatingRequest =
            PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            repeatingRequest)
    }

}