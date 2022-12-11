package com.akkeylab.c.search

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class SearchCorporate {
    suspend fun search(name: String) {
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
        val response: HttpResponse = client.get(
            "https://api.houjin-bangou.nta.go.jp/4/name?id=${apiKey}&name=${name}&type=12&mode=2&kind=03&close=0"
        )
        println(response.status)
        println(response.body<Any?>().toString())
        client.close()
    }
}
