package me.lunaluna.anime

import kotlinx.coroutines.runBlocking

class Episode(private val anime: Anime, private val episodeNumber: Int): Iterator<Episode> {
    override fun hasNext() = (episodeNumber + 1) in anime.episodeRange
    override fun next() = Episode(anime, episodeNumber+1)
    val page by lazy { runBlocking { Http.get(url) } }
    val url = "https://gogoanime.pe/${anime.id}-episode-$episodeNumber"
    val video by lazy {
        runBlocking {
            val url = "https://" + Regex("<a href=\"#\" rel=\"100\" data-video=\"\\/\\/(.+?)\" >")
                .find(page)!!
                .groupValues[1]
            url to Regex("sources:\\[\\{file: '(.*?)'").find(Http.getNoEncoding(url))!!.groupValues[1]
        }
    }
}