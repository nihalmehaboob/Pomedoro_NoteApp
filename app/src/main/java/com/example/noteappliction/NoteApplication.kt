package com.example.noteappliction

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            auth.signInAnonymously()
                .addOnSuccessListener { /* uid now available via auth.currentUser?.uid */ }
                .addOnFailureListener { /* log — sync will be unavailable until this succeeds */ }
        }
    }
}
