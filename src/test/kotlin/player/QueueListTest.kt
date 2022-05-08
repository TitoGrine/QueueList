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
}