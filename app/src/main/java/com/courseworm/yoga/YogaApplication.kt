package com.courseworm.yoga

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.courseworm.yoga.ui.main.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration

class YogaApplication : Application() {

    private val tag = "YogaApplication"
    private val preferences: SharedPreferences by lazy {
        getSharedPreferences(tag, Context.MODE_PRIVATE)
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize the MainRepository
        MainRepository.initialize(this)
        syncDatabase()
        setupPeriodicSync()
        setDarkModeEnabled()
    }

    private fun syncDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                MainRepository.get().syncDatabase()
                Log.d(tag, "Database synced successfully on launch.")
            } catch (e: Exception) {
                Log.e(tag, "Failed to sync database on launch: ${e.message}", e)
            }
        }
    }

    private fun setupPeriodicSync() {
        try {
            val interval = getSyncInterval() // Get user-defined sync interval
            val syncWorkRequest: WorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(interval)
                .build()

            Log.d(tag, "Setting up periodic sync with interval: ${interval.toMinutes()} minutes")
            WorkManager.getInstance(this).enqueue(syncWorkRequest)
        } catch (e: Exception) {
            Log.e(tag, "Error setting up periodic sync: ${e.message}", e)
        }
    }

    fun getSyncInterval(): Duration {
        // Default to 1 minute if not set
        val defaultMinutes = 1
        return try {
            val minutes = preferences.getInt("sync_interval", defaultMinutes)
            Duration.ofMinutes(minutes.toLong())
        } catch (e: Exception) {
            Log.e(tag, "Error getting sync interval: ${e.message}", e)
            Duration.ofMinutes(defaultMinutes.toLong()) // Return default on error
        }
    }

    fun setSyncInterval(minutes: Int) {
        try {
            preferences.edit().putInt("sync_interval", minutes).apply()
            setupPeriodicSync()
            Log.d(tag, "Sync interval set to $minutes minutes.")
        } catch (e: Exception) {
            Log.e(tag, "Error setting sync interval: ${e.message}", e)
        }
    }

    // Dark Mode Methods
    fun setDarkModeEnabled(enabled: Boolean = isDarkModeEnabled()) {
        try {
            preferences.edit().putBoolean("dark_mode", enabled).apply()
            applyDarkMode() // Apply the change immediately if needed
            Log.d(tag, "Dark mode set to: $enabled.")
        } catch (e: Exception) {
            Log.e(tag, "Error setting dark mode: ${e.message}", e)
        }
    }

    fun isDarkModeEnabled(): Boolean {
        return try {
            preferences.getBoolean("dark_mode", true) // Default to true (dark mode)
        } catch (e: Exception) {
            Log.e(tag, "Error getting dark mode status: ${e.message}", e)
           false
        }
    }

    private fun applyDarkMode() {
        try {
            if (isDarkModeEnabled()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            Log.d(tag, "Dark mode applied.")
        } catch (e: Exception) {
            Log.e(tag, "Error applying dark mode: ${e.message}", e)
        }
    }
}
