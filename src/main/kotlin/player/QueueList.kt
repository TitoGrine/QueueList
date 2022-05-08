package player

class QueueList() {
    private var currentTrack: Int = 0
    private val tracks: MutableList<Track> = mutableListOf()

    constructor(initialTrack: Int = 0, initialTracks: Collection<Track>) : this() {
        currentTrack = if (initialTrack < initialTracks.size) initialTrack else 0
        tracks.addAll(initialTracks)
    }

    fun getCurrentTrack(): Track? {
        return tracks.getOrNull(currentTrack)
    }

    fun next(): Track? {
        if (tracks.size == 0) return null

        currentTrack = (currentTrack + 1) % tracks.size

        return tracks.getOrNull(currentTrack)
    }

    fun previous(): Track? {
        currentTrack = Integer.max(currentTrack - 1, 0)

        return tracks.getOrNull(currentTrack)
    }
}
