package me.jgaiser.photoAlbum.services

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class PhotoAlbumService(
    private val photoService: PhotoService
) {
    fun displayAlbum(albumId: String): Flux<String> =
        photoService.getAlbum(albumId)
            .flatMapIterable { album ->
                when {
                    album.photos.isNotEmpty() -> album.photos.map { it.toString() }
                    else -> listOf("album contained no photos")
                }
            }
}
