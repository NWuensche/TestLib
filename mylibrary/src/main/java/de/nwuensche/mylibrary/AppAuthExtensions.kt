package de.nwuensche.mylibrary

import android.net.Uri
import net.openid.appauth.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun fetchFromIssuer(openIdConnectIssuerUri: Uri): AuthorizationServiceConfiguration = suspendCoroutine { cont ->
    AuthorizationServiceConfiguration.fetchFromIssuer(
        openIdConnectIssuerUri
    ) { serviceConfiguration, ex ->
        if (serviceConfiguration != null) {
            cont.resume(serviceConfiguration)
        } else if (ex != null) {
            cont.resumeWithException(ex)
        } else {
            cont.resumeWithException(IllegalStateException("serviceConfiguration AND exception is null")) //fetchFromIssuer-Dok says exactly one is non-null
        }
    }
}

suspend fun fetchFromUrl(openIdConnectIssuerUri: Uri): AuthorizationServiceConfiguration = suspendCoroutine { cont ->
    AuthorizationServiceConfiguration.fetchFromIssuer(
        openIdConnectIssuerUri
    ) { serviceConfiguration, ex ->
        if (serviceConfiguration != null) {
            cont.resume(serviceConfiguration)
        } else if (ex != null) {
            cont.resumeWithException(ex)
        } else {
            cont.resumeWithException(IllegalStateException("serviceConfiguration AND exception is null")) //fetchFromIssuer-Dok says exactly one is non-null
        }
    }
}

suspend fun AuthorizationService.performTokenRequest(tokenRequest: TokenRequest): TokenResponse = suspendCoroutine { cont ->
    this.performTokenRequest(
        tokenRequest
    ) { response, ex ->
        if (response != null) {
            cont.resume(response)
        } else if (ex != null) {
            cont.resumeWithException(ex)
        } else {
            cont.resumeWithException(IllegalStateException("response AND exception is null")) //performTokenRequest-Dok says exactly one is non-null
        }
    }
}

suspend fun AuthorizationService.performTokenRequest(tokenRequest: TokenRequest, clientAuthentication: ClientAuthentication): TokenResponse = suspendCoroutine { cont ->
    this.performTokenRequest(
        tokenRequest,
        clientAuthentication
    ) { response, ex ->
        if (response != null) {
            cont.resume(response)
        } else if (ex != null) {
            cont.resumeWithException(ex)
        } else {
            cont.resumeWithException(IllegalStateException("response AND exception is null")) //performTokenRequest-Dok says exactly one is non-null
        }
    }
}

data class FreshTokens(val accessToken: String?, val idToken: String?)

suspend fun AuthState.performActionWithFreshTokens(service: AuthorizationService): FreshTokens = suspendCoroutine { cont ->
    this.performActionWithFreshTokens(
        service
    ) { accessToken, idToken, ex ->
        if (accessToken != null || idToken != null) {
            cont.resume(FreshTokens(accessToken, idToken))
        } else if (ex != null) {
            cont.resumeWithException(ex)
        } else {
            cont.resumeWithException(IllegalStateException("tokens AND exception is null")) //performActionWithFreshTokens-Dok says exactly one is non-null
        }
    }
}

suspend fun AuthState.performActionWithFreshTokens(service: AuthorizationService, clientAuthentication: ClientAuthentication): FreshTokens = suspendCoroutine { cont ->
    this.performActionWithFreshTokens(
        service,
        clientAuthentication
    ) { accessToken, idToken, ex ->
        if (accessToken != null || idToken != null) {
            cont.resume(FreshTokens(accessToken, idToken))
        } else if (ex != null) {
            cont.resumeWithException(ex)
        } else {
            cont.resumeWithException(IllegalStateException("tokens AND exception is null")) //performActionWithFreshTokens-Dok says exactly one is non-null
        }
    }
}
