import java.io.File

fun main() {
    File("TheCore_5.0_Right_Plus_Kinesis.SC2Hotkeys").useLines { it.toList().asSequence() }
        .map { line -> remap(line) }
        .forEach(::println)
}


fun remap(line: String): String {
    if ("=" !in line) {
        return line
    }
    val command = line.substringBefore("=")
    var hotkey = line.substringAfter('=')
    val hotkeyTokens = hotkey.splitKeeping(",", "+")
    hotkey = hotkeyTokens.map { token -> remaps.getOrElse(token, { token }) }
        .joinToString(separator = "")
    return "$command=$hotkey"
}

fun String.splitKeeping(str: String): List<String> {
    return this.split(str).flatMap { listOf(it, str) }.dropLast(1).filterNot { it.isEmpty() }
}

fun String.splitKeeping(vararg strs: String): List<String> {
    var res = listOf(this)
    strs.forEach { str ->
        res = res.flatMap { it.splitKeeping(str) }
    }
    return res
}

val remaps: Map<String, String> = hashMapOf(
    "7" to "2",
    "8" to "1",
    "U" to "W",
    "I" to "S",
    "K" to "X",
    "Comma" to "BackSlash",
    "9" to "E",
    "0" to "3",
    "O" to "D",
    "L" to "C",
    "Period" to "Left",
    "P" to "F",
    "Minus" to "R",
    "Equals" to "T",
    "BracketOpen" to "G",
    "SemiColon" to "V",
    "Apostrophe" to "B",
    "BracketClose" to "Right",
    "J" to "Z",
    "M" to "OEM8",
    "N" to "A",
    "B" to "Q",
    "G" to "F1",
    "Y" to "F2",
    "H" to "F3"
)
