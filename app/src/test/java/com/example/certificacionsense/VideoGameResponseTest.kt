package com.example.certificacionsense

import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import org.junit.Test


class VideoGameResponseTest {
   /* @Test
    fun `test entity creation`() {
        val videoGame = VideoGameResponseItem(
            id = 1,
            name = "Game Name",
            released = "2021-12-01",
            backgroundImage = "https://example.com/image.png",
            rating = 4.5,
            metacritic = 85
        )

        assertEquals(1, videoGame.id)
        assertEquals("Game Name", videoGame.name)
        assertEquals("2021-12-01", videoGame.released)
        assertEquals("https://example.com/image.png", videoGame.backgroundImage)
        assertEquals(4.5, videoGame.rating)
        assertEquals(85, videoGame.metacritic)
    }

    @Test
    fun `test JSON deserialization`() {
        val json = """
            {
                "id": 1,
                "name": "Game Name",
                "released": "2021-12-01",
                "background_image": "https://example.com/image.png",
                "rating": 4.5,
                "metacritic": 85
            }
        """

        val videoGame = Gson().fromJson(json, VideoGameResponseItem::class.java)

        assertEquals(1, videoGame.id)
        assertEquals("Game Name", videoGame.name)
        assertEquals("2021-12-01", videoGame.released)
        assertEquals("https://example.com/image.png", videoGame.backgroundImage)
        assertEquals(4.5, videoGame.rating)
        assertEquals(85, videoGame.metacritic)
    }*/
}