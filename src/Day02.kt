fun main() {
    fun part1(input: List<String>): Int =
        input.sumOf { line ->
            val (a, b) = line.split(" ")
            when (b) {
                "X" -> 1 + when (a) {
                    "A" -> 3
                    "B" -> 0
                    "C" -> 6
                    else -> error("")
                }
                "Y" -> 2 + when (a) {
                    "A" -> 6
                    "B" -> 3
                    "C" -> 0
                    else -> error("")
                }
                "Z" -> 3 + when (a) {
                    "A" -> 0
                    "B" -> 6
                    "C" -> 3
                    else -> error("")
                }
                else -> error("Shouldn't be here")
            }
        }


    fun part2(input: List<String>): Int =
        input.sumOf { line ->
            val (a, b) = line.split(" ")
            when (b) {
                "X" -> 0 + when (a) {
                    "A" -> 3
                    "B" -> 1
                    "C" -> 2
                    else -> error("")
                }
                "Y" -> 3 + when (a) {
                    "A" -> 1
                    "B" -> 2
                    "C" -> 3
                    else -> error("")
                }
                "Z" -> 6 + when (a) {
                    "A" -> 2
                    "B" -> 3
                    "C" -> 1
                    else -> error("")
                }
                else -> error("Shouldn't be here")
            }
        }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
