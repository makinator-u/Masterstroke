package com.masterstroke.app.domain.model

/** A minimal session contract; production identity is supplied by the future backend. */
data class UserSession(
    val displayName: String,
    val roleLabel: String,
)
