package me.lunaluna.anime

object AnimeRepository {
    suspend fun search(search: String): Sequence<Anime> {
        val resultsPage = Http.get("https://gogoanime.pe/search.html?keyword=$search")

        return Regex("<a href=\"\\/category\\/(.*?)\" title=\".*?\">\n")
            .findAll(resultsPage)
            .map(::Anime)
    }
    operator fun get(id: String): Anime {
        return Anime(id)
    }
}