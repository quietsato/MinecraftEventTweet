package io.github.quietsato.minecraft_event_tweet.twitter

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.utils.io.*

class TwitterManager {
    suspend fun fetchWebApiTest() {
        val httpClient = HttpClient(CIO)
        val response: HttpResponse = httpClient.get("https://api.myip.com")

        response.content.readUTF8Line().also {
            println(it)
        }
    }
}
