package player

data class Track(
    val id: String,
    val artist: String,
    val title: String,
    val duration: Int,
    val media: Media
) {
    data class Media(
        val id: String
    )
}