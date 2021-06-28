package me.jgaiser.photoAlbum.domain

data class Photo(
    val id: String,
    val albumId: String,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
) {
    override fun toString(): String = "[$id] $title"
}
