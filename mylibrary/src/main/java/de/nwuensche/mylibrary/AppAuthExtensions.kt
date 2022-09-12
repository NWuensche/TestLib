package de.nwuensche.mylibrary

import android.net.Uri
import net.openid.appauth.AuthorizationServiceConfiguration
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
