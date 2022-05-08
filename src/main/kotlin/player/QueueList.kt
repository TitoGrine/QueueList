package player

import kotlinx.serialization.Serializable

@Serializable
class QueueList() {
    private var currentTrack: Int = 0
    private val tracks: MutableList<Track> = mutableListOf()

    constructor(initialTrack: Int = 0, initialTracks: Collection<Track>) : this() {
        currentTrack = if (initialTrack < initialTracks.size) initialTrack else 0
        tracks.addAll(initialTracks)
    }

    private fun incrementCurrentTrack() {
        if (tracks.size == 0) return

        currentTrack = (currentTrack + 1) % tracks.size
    }

    private fun decrementCurrentTrack() {
        currentTrack = Integer.max(currentTrack - 1, 0)
    }

    fun getCurrentTrack(): Track? {
        return tracks.getOrNull(currentTrack)
    }

    fun next(): Track? {
        incrementCurrentTrack()

        return tracks.getOrNull(currentTrack)
    }

    fun previous(): Track? {
        decrementCurrentTrack()

        return tracks.getOrNull(currentTrack)
    }

    fun add(newTrack: Track): Boolean {
        return tracks.add(newTrack)
    }

    fun add(newTracks: Collection<Track>): Boolean {
        return tracks.addAll(newTracks)
    }

    fun removeAt(trackIndex: Int): Track? {
        if (trackIndex !in 0 until tracks.size) return null

        if (trackIndex == currentTrack && trackIndex == tracks.size - 1) currentTrack = 0
        else if (trackIndex < currentTrack) decrementCurrentTrack()

        return tracks.removeAt(trackIndex)
    }
}
