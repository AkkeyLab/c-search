package com.akkeylab.c.search

import io.ktor.client.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class SearchCorporate {
    @Throws(Throwable::class)
    suspend fun search(name: String): String {
        val client = HttpClient() {
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
        }
        val apiKey = BuildKonfig.API_KEY
        // https://www.houjin-bangou.nta.go.jp/documents/k-web-api-kinou-gaiyo.pdf#page=24
        val response: HttpResponse = client.get("https://api.houjin-bangou.nta.go.jp/4/name") {
            parameter("id", apiKey)
            parameter("name", name)
            parameter("type", 12)
            parameter("mode", 2)
            parameter("kind", "03")
            parameter("close", 0)
        }
        client.close()
        return response.bodyAsText()
    }
}
