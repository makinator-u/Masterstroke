package com.masterstroke.app.domain.repository

import com.masterstroke.app.domain.model.UserSession
import kotlinx.coroutines.flow.StateFlow

/** Boundary for future secure authentication and persisted sessions. */
interface SessionRepository {
    val session: StateFlow<UserSession?>
    fun signInForPreview()
    fun signOut()
}
