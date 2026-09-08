package com.masterstroke.app.presentation

import androidx.lifecycle.ViewModel
import com.masterstroke.app.data.repository.PreviewSessionRepository

class AppViewModel : ViewModel() {
    private val sessionRepository = PreviewSessionRepository()
    val session = sessionRepository.session
    fun enterPreview() = sessionRepository.signInForPreview()
    fun signOut() = sessionRepository.signOut()
}
