import player.deserializeJSONDataFile

private fun getPlayerFromJSON(filename: String, identifier: String) {
    val player = deserializeJSONDataFile(filename, identifier)

    if (player == null) {
        println("Error: Unable to get player from file '$filename' using the identifier '$identifier'.")
        return
    }

    println("First track is: ${player.getCurrentTrack()}")
    println("Second track is: ${player.next()}")
    println("Third track is: ${player.removeAt(2)}")
}

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Error: Program arguments are <filename> <player_identifier>")
        return
    }

    getPlayerFromJSON(args[0], args[1])
}