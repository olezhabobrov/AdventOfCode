fun main() {
    fun part1(input: List<String>): Int {
        return input.count { line ->
            val (a, b, c, d) = line.split(",", "-").map { it.toInt() }
            assert(a <= b && c <= d)
            a <= c && b >= d || a >=c && b <= d
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { line ->
            val (a, b, c, d) = line.split(",", "-").map { it.toInt() }
            assert(a <= b && c <= d)
            !(b < c || a > d)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
