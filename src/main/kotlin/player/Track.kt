package player

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val id: String,
    val artist: String,
    val title: String,
    val duration: Int,
    val media: Media
) {
    @Serializable
    data class Media(
        val id: String
    )

    override fun toString(): String {
        return """
            
        -------------------------------------------------------
            ID: $id
            Artist: $artist
            Title: $title
            Duration: ${duration}s
            Media ID: ${media.id}
        -------------------------------------------------------
        
        """.trimIndent()
    }
}
