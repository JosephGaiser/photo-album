package me.jgaiser.photoAlbum

import me.jgaiser.photoAlbum.services.PhotoAlbumService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class PhotoAlbumApplication(
    private val photoAlbumService: PhotoAlbumService,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        while (true) {
            println("${System.lineSeparator()}Enter an album id: ")
            when (val input = readLine()) {
                "exit" -> exitProcess(1)
                else -> {
                    photoAlbumService.displayAlbum(input.toString())
                        .map { println(it) }
                        .blockLast()
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<PhotoAlbumApplication>(*args)
}
