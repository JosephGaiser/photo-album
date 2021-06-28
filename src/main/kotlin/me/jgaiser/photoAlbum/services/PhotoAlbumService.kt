package me.jgaiser.photoAlbum.services

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PhotoAlbumService(
    private val photoService: PhotoService
) {
    fun displayAlbum(albumId: String): Flux<String> {
        return Flux.empty()
    }
}
