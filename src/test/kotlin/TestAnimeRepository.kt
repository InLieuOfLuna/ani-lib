import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import me.lunaluna.anime.AnimeRepository
import org.junit.Test

class TestAnimeRepository {

    @Test
    fun testSearch() {
        runBlocking {
            assertEquals(AnimeRepository.search("sword art online").first().id, "sword-art-online")
        }
    }

    @Test
    fun testGet() {
        assertEquals(AnimeRepository["bakemonogatari"].episodeRange, 0..15)
    }

}