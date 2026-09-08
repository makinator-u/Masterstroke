package com.masterstroke.app.data.repository

import com.masterstroke.app.domain.model.UserSession
import com.masterstroke.app.domain.repository.SessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/** In-memory preview only. It intentionally contains no production credentials. */
class PreviewSessionRepository : SessionRepository {
    private val mutableSession = MutableStateFlow<UserSession?>(null)
    override val session: StateFlow<UserSession?> = mutableSession

    override fun signInForPreview() { mutableSession.value = UserSession("Field Team", "Operational user") }
    override fun signOut() { mutableSession.value = null }
}
