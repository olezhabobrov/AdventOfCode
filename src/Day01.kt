fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        var current = 0
        input.map {
            if (it.isEmpty())
                null
            else
                it.toInt()
        }.forEach {
            if (it == null) {
                result = maxOf(result, current)
                current = 0
            } else {
                current += it
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val result = mutableListOf<Int>()
        var current = 0
        input.map {
            if (it.isEmpty())
                null
            else
                it.toInt()
        }.forEach {
            if (it == null) {
                result.add(current)
                current = 0
            } else {
                current += it
            }
        }
        result.sortDescending()
        return result[0] + result[1] + result[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
