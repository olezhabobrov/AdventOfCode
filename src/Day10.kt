import java.lang.Math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var counter = 0
        var result = 0
        input.fold(1) { cur, s ->
            val lst = s.split(" ")
            var new = cur
            if (lst.size == 1) {
                counter++
            } else {
                counter++
                if ((counter - 20) % 40 == 0 && counter <= 220) {
                    result += counter * cur
                }
                counter++
                new += lst[1].toInt()
            }
            if ((counter - 20) % 40 == 0 && counter <= 220) {
                result += counter * cur
            }
            new
        }
        return result
    }

    fun part2(input: List<String>) {
        var cycle = 0
        var result = ""
        input.fold(1) { cur, s ->
            val lst = s.split(" ")
            var new = cur
            if (lst.size == 1) {
                cycle++
            } else {
                cycle++
                if (abs(((cycle - 1) % 40) - cur) <= 1) {
                    result += "#"
                } else {
                    result += "."
                }
                cycle++
                new += lst[1].toInt()
            }
            if (abs(((cycle - 1) % 40) - cur) <= 1) {
                result += "#"
            } else {
                result += "."
            }
            new
        }
        println(result.chunked(40).joinToString(System.lineSeparator()))
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    part2(testInput)

    val input = readInput("Day10")
    println(part1(input))
    part2(input)
}
