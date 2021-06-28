package me.jgaiser.photoAlbum.services

import me.jgaiser.photoAlbum.domain.Album
import me.jgaiser.photoAlbum.domain.Photo
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class PhotoService(
    private val webClient: WebClient
) {
    fun getAlbum(albumId: String): Mono<Album> =
        webClient.get()
            .uri("https://jsonplaceholder.typicode.com/photos?albumId=$albumId")
            .retrieve()
            .bodyToFlux(Photo::class.java)
            .collectList()
            .map { Album(albumId, it) }
}
