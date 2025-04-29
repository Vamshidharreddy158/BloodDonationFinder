package teesside.s3381983.blooddonation

import android.content.Context

object BloodDonationPreferences {

    private const val PREFS_FILE = "BLOOD_DONATION_PREFS"
    private const val FIELD_IS_LOGGED_IN = "FIELD_IS_LOGGED_IN"
    private const val FIELD_DONOR_NAME = "FIELD_DONOR_NAME"
    private const val FIELD_DONOR_EMAIL = "FIELD_DONOR_EMAIL"

    fun saveLoginState(context: Context, isLoggedIn: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(FIELD_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun fetchLoginState(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getBoolean(FIELD_IS_LOGGED_IN, false)
    }

    fun saveDonorName(context: Context, name: String) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        prefs.edit().putString(FIELD_DONOR_NAME, name).apply()
    }

    fun fetchDonorName(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getString(FIELD_DONOR_NAME, "") ?: ""
    }

    fun saveDonorEmail(context: Context, email: String) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        prefs.edit().putString(FIELD_DONOR_EMAIL, email).apply()
    }

    fun fetchDonorEmail(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getString(FIELD_DONOR_EMAIL, "") ?: ""
    }
}
