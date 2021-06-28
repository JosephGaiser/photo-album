package me.jgaiser.photoAlbum.services

import io.mockk.every
import io.mockk.mockk
import me.jgaiser.photoAlbum.domain.Album
import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import reactor.kotlin.core.publisher.toMono
import reactor.test.StepVerifier
import kotlin.random.Random

@Suppress("classname", "ReactiveStreamsUnusedPublisher")
class PhotoAlbumServiceTest {
    private val random = EasyRandom(EasyRandomParameters().seed(Random.nextLong()))
    private val photoService: PhotoService = mockk()
    private val photoAlbumService = PhotoAlbumService(photoService)

    private val albumId = random.nextInt(999).toString()
    private val album = random.nextObject(Album::class.java)

    @BeforeEach
    fun beforeEach() {
        every { photoService.getAlbum(albumId) } returns album.toMono()
    }

    @Test
    fun `should fetch all photos in album to return`() {
        StepVerifier.create(photoAlbumService.displayAlbum(albumId))
            .expectNextSequence(album.photos.map { it.toString() })
            .verifyComplete()
    }

    @Nested
    inner class `given album does not exit or is empty` {

        @BeforeEach
        fun beforeEach() {
            every { photoService.getAlbum(albumId) } returns Album(albumId, listOf()).toMono()
        }

        @Test
        fun `should return message indicating no photos`() {
            StepVerifier.create(photoAlbumService.displayAlbum(albumId))
                .expectNext("album contained no photos")
                .verifyComplete()
        }
    }
}
