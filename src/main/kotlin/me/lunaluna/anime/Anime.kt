package me.lunaluna.anime

import kotlinx.coroutines.runBlocking

class Anime(val id: String): Iterable<Episode> {
    constructor(result: MatchResult) : this(result.groupValues[1])

    private val page by lazy {
        runBlocking {
            Http.get("https://gogoanime.pe/category/$id")
        }
    }
    val episodeRange: IntRange by lazy {
        Regex("<a href=\"#\" class=\"active\" ep_start = '(\\d+)' ep_end = '(\\d+)'>")
            .find(page)
            ?.apply { return@lazy groupValues[1].toInt()..groupValues[2].toInt() }
        throw MissingEpisodeRangeException()
    }

    override fun iterator(): Iterator<Episode> = Episode(this, episodeRange.first)
    operator fun get(episode: Int): Episode {
        if (episode in episodeRange) return Episode(this, episode)
        else throw IndexOutOfBoundsException()
    }

    class MissingEpisodeRangeException : Throwable("Missing episode range")
}