package me.jgaiser.photoAlbum

import me.jgaiser.photoAlbum.services.PhotoAlbumService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhotoAlbumApplication(
	private val photoAlbumService: PhotoAlbumService,
) : ApplicationRunner {
	override fun run(args: ApplicationArguments?) {
		TODO("Not yet implemented")
	}
}

fun main(args: Array<String>) {
	runApplication<PhotoAlbumApplication>(*args)
}
