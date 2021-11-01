package me.lunaluna.anime

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*

// Because I'm lazy

object Http {
    private val http = HttpClient(CIO)
    private val builder = URLBuilder().apply { parameters.urlEncodingOption = UrlEncodingOption.NO_ENCODING }
    suspend fun get(url: String) = http.get<String>(url)
    suspend fun getNoEncoding(url: String) = http.get<String>(builder.takeFrom(url).build())
}