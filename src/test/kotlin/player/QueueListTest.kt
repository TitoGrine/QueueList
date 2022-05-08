package player

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*
import io.mockk.mockk
import io.mockk.every
import org.junit.jupiter.api.Nested

internal class QueueListTest {

    @Nested
    inner class Level1 {
        lateinit var track1: Track
        lateinit var track2: Track
        lateinit var track3: Track
        lateinit var tracks: ArrayList<Track>

        @BeforeEach
        fun setup() {
            track1 = mockk()
            track2 = mockk()
            track3 = mockk()
            every { track1.id } returns "000001"
            every { track2.id } returns "000002"
            every { track3.id } returns "000003"


            tracks = arrayListOf(track1, track2, track3)
        }

        @Test
        fun `test getCurrentTrack when QueueList empty`() {
            val queueList = QueueList()
            val currentTrack = queueList.getCurrentTrack()

            assertNull(currentTrack)
        }

        @Test
        fun `test getCurrentTrack when QueueList has tracks`() {
            val queueList = QueueList(initialTracks = tracks)
            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track1.id, currentTrack?.id)
        }

        @Test
        fun `test getCurrentTrack when QueueList is constructed with initialTrack higher than 0`() {
            val queueList = QueueList(1, tracks)
            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track2.id, currentTrack?.id)
        }

        @Test
        fun `test next when QueueList empty`() {
            val queueList = QueueList()
            val nextTrack = queueList.next()

            assertNull(nextTrack)
        }

        @Test
        fun `test next when QueueList has tracks`() {
            val queueList = QueueList(initialTracks = tracks)
            var nextTrack = queueList.next()

            assertEquals(track2.id, nextTrack?.id)

            nextTrack = queueList.next()

            assertEquals(track3.id, nextTrack?.id)
        }

        @Test
        fun `test next when QueueList is constructed with initialTrack higher than 0`() {
            val queueList = QueueList(1, tracks)
            val nextTrack = queueList.next()

            assertEquals(track3.id, nextTrack?.id)
        }

        @Test
        fun `test next when the end of QueueList tracks is reached`() {
            val queueList = QueueList(2, tracks)
            val nextTrack = queueList.next()

            assertEquals(track1.id, nextTrack?.id)
        }

        @Test
        fun `test previous when QueueList empty`() {
            val queueList = QueueList()
            val previousTrack = queueList.previous()

            assertNull(previousTrack)
        }

        @Test
        fun `test previous when current track is the first in tracks`() {
            val queueList = QueueList(initialTracks = tracks)
            val previousTrack = queueList.previous()

            assertEquals(track1.id, previousTrack?.id)
        }

        @Test
        fun `test previous when QueueList is constructed with initialTrack higher than 0`() {
            val queueList = QueueList(2, tracks)
            var previousTrack = queueList.previous()

            assertEquals(track2.id, previousTrack?.id)

            previousTrack = queueList.previous()

            assertEquals(track1.id, previousTrack?.id)
        }

        @Test
        fun `test normal flow`() {
            val queueList = QueueList(initialTracks = tracks)
            var currentTrack: Track?
            var nextTrack: Track?
            val previousTrack: Track?

            currentTrack = queueList.getCurrentTrack()

            assertEquals(track1.id, currentTrack?.id)

            nextTrack = queueList.next()
            currentTrack = queueList.getCurrentTrack()

            assertEquals(track2.id, nextTrack?.id)
            assertEquals(track2.id, currentTrack?.id)

            nextTrack = queueList.next()
            currentTrack = queueList.getCurrentTrack()

            assertEquals(track3.id, nextTrack?.id)
            assertEquals(track3.id, currentTrack?.id)

            previousTrack = queueList.previous()
            currentTrack = queueList.getCurrentTrack()

            assertEquals(track2.id, previousTrack?.id)
            assertEquals(track2.id, currentTrack?.id)
        }
    }

    @Nested
    inner class Level2 {
        lateinit var track1: Track
        lateinit var track2: Track
        lateinit var track3: Track
        lateinit var track4: Track
        lateinit var initialTracks: ArrayList<Track>
        lateinit var additionalTracks: ArrayList<Track>

        @BeforeEach
        fun setup() {
            track1 = mockk()
            track2 = mockk()
            track3 = mockk()
            track4 = mockk()
            every { track1.id } returns "000001"
            every { track2.id } returns "000002"
            every { track3.id } returns "000003"
            every { track4.id } returns "000004"


            initialTracks = arrayListOf(track1, track2)
            additionalTracks = arrayListOf(track3, track4)
        }

        @Test
        fun `test add single track when QueueList empty`() {
            val queueList = QueueList()

            queueList.add(track1)

            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track1.id, currentTrack?.id)
        }

        @Test
        fun `test add single track when QueueList has tracks`() {
            val queueList = QueueList(1, initialTracks)

            queueList.add(track3)

            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track2.id, currentTrack?.id)

            val nextTrack = queueList.next()

            assertEquals(track3.id, nextTrack?.id)
        }

        @Test
        fun `test add multiple tracks when QueueList empty`() {
            val queueList = QueueList()

            queueList.add(initialTracks)

            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track1.id, currentTrack?.id)

            val nextTrack = queueList.next()

            assertEquals(track2.id, nextTrack?.id)
        }

        @Test
        fun `test add multiple tracks when QueueList has tracks`() {
            val queueList = QueueList(1, initialTracks)

            queueList.add(additionalTracks)

            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track2.id, currentTrack?.id)

            var nextTrack = queueList.next()

            assertEquals(track3.id, nextTrack?.id)

            nextTrack = queueList.next()

            assertEquals(track4.id, nextTrack?.id)
        }

        @Test
        fun `test add multiple tracks passing an empty Collection`() {
            val queueList = QueueList(1, initialTracks)

            queueList.add(ArrayList())

            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track2.id, currentTrack?.id)

            val nextTrack = queueList.next()

            assertEquals(track1.id, nextTrack?.id)
        }

        @Test
        fun `test removeAt when QueueList empty`() {
            val queueList = QueueList()

            queueList.removeAt(0)

            val currentTrack = queueList.getCurrentTrack()

            assertNull(currentTrack)
        }

        @Test
        fun `test removeAt when currentTrack is before trackIndex`() {
            val queueList = QueueList(initialTracks = initialTracks)

            val removedTrack = queueList.removeAt(1)

            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track2.id, removedTrack?.id)
            assertEquals(track1.id, currentTrack?.id)
        }

        @Test
        fun `test removeAt when currentTrack is after trackIndex`() {
            val queueList = QueueList(1, initialTracks)

            val removedTrack = queueList.removeAt(0)

            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track1.id, removedTrack?.id)
            assertEquals(track2.id, currentTrack?.id)
        }

        @Test
        fun `test removeAt when currentTrack is trackIndex`() {
            val allTracks = initialTracks + additionalTracks
            val queueList = QueueList(1, allTracks)

            val removedTrack = queueList.removeAt(1)

            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track2.id, removedTrack?.id)
            assertEquals(track3.id, currentTrack?.id)
        }

        @Test
        fun `test removeAt when currentTrack is trackIndex and the last track`() {
            val allTracks = initialTracks + additionalTracks
            val queueList = QueueList(3, allTracks)

            val removedTrack = queueList.removeAt(3)

            val currentTrack = queueList.getCurrentTrack()

            assertEquals(track4.id, removedTrack?.id)
            assertEquals(track1.id, currentTrack?.id)
        }

        @Test
        fun `test removeAt when trackIndex is out of bounds`() {
            val queueList = QueueList(initialTracks = initialTracks)

            var removedTrack = queueList.removeAt(-1)

            var currentTrack = queueList.getCurrentTrack()

            assertNull(removedTrack)
            assertEquals(track1.id, currentTrack?.id)

            removedTrack = queueList.removeAt(3)

            currentTrack = queueList.getCurrentTrack()

            assertNull(removedTrack)
            assertEquals(track1.id, currentTrack?.id)
        }

        @Test
        fun `test normal flow`() {
            val queueList = QueueList(initialTracks = initialTracks)
            var currentTrack: Track?
            var nextTrack: Track?
            val removedTrack: Track?

            currentTrack = queueList.getCurrentTrack()

            assertEquals(track1.id, currentTrack?.id)

            nextTrack = queueList.next()

            assertEquals(track2.id, nextTrack?.id)

            queueList.add(additionalTracks)

            nextTrack = queueList.next()

            assertEquals(track3.id, nextTrack?.id)

            removedTrack = queueList.removeAt(2)
            currentTrack = queueList.getCurrentTrack()

            assertEquals(track3.id, removedTrack?.id)
            assertEquals(track4.id, currentTrack?.id)

            queueList.add(track3)

            nextTrack = queueList.next()

            assertEquals(track3.id, nextTrack?.id)
        }
    }
}