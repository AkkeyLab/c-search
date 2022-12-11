package com.akkeylab.c.search

import io.ktor.client.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class SearchCorporate {
    suspend fun search() {
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
        val response: HttpResponse = client.get("https://api.houjin-bangou.nta.go.jp/4/num?id=${apiKey}&number={}&type=12&history=0")
        println(response.status)
        client.close()
    }
}
