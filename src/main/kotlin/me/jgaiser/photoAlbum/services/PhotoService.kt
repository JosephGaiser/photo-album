package me.jgaiser.photoAlbum.services

import me.jgaiser.photoAlbum.domain.Album
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class PhotoService(
    private val webClient: WebClient
) {
    fun getAlbum(albumId: String): Mono<Album> {
        throw NotImplementedError()
    }
}
