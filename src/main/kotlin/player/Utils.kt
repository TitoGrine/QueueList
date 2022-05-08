package player

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import java.io.File

fun deserializeJSONDataFile(filename: String, identifier: String): QueueList? {
    val file = File(filename)

    if (!file.exists()) return null

    val jsonString = Json.parseToJsonElement(file.readText()).jsonObject

    val jsonObject = jsonString["data"]?.jsonObject?.get(identifier) ?: return null

    return Json.decodeFromJsonElement(QueueList.serializer(), jsonObject)
}
