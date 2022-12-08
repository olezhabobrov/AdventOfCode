// Literally the most horrible code I've ever written

fun main() {
    fun part1(input: List<String>): Int {
        var counter = 0
        val X = input[0].length
        val Y = input.size
        val field = Array(input.size, {index -> input[index].map { it.digitToInt() } })
        field.forEachIndexed { y, xLine ->
            xLine.forEachIndexed loop@{ x, height ->
                if (x == 0 || x == X - 1 || y == 0 || y == Y - 1) {
                    counter++
                    return@loop
                }

                var dirs = 0
                for (xid in 0 until x) {
                    if (field[y][xid] >= height) {
                        dirs++
                        break
                    }
                }
                for (xid in x + 1 until X) {
                    if (field[y][xid] >= height) {
                        dirs++
                        break
                    }
                }
                for (yid in 0 until y) {
                    if (field[yid][x] >= height) {
                        dirs++
                        break
                    }
                }
                for (yid in y + 1 until Y) {
                    if (field[yid][x] >= height) {
                        dirs++
                        break
                    }
                }
                if (dirs < 4)
                    counter++
            }
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val X = input[0].length
        val Y = input.size
        val field = Array(input.size, {index -> input[index].map { it.digitToInt() } })
        field.forEachIndexed { y, xLine ->
            xLine.forEachIndexed loop@{ x, height ->
                if (x == 0 || x == X - 1 || y == 0 || y == Y - 1) {
                    return@loop
                }

                var current = 1
                for (xid in x - 1 downTo -1) {
                    if (xid == -1) {
                        current *= x
                        break
                    }
                    if (field[y][xid] >= height) {
                        current *= (x - xid)
                        break
                    }
                }
                for (xid in x + 1 .. X) {
                    if (xid == X) {
                        current *= (X - x - 1)
                        break
                    }
                    if (field[y][xid] >= height) {
                        current *= (xid - x)
                        break
                    }
                }
                for (yid in y - 1 downTo -1) {
                    if (yid == -1) {
                        current *= y
                        break
                    }
                    if (field[yid][x] >= height) {
                        current *= (y - yid)
                        break
                    }
                }
                for (yid in y + 1 .. Y) {
                    if (yid == Y) {
                        current *= (Y - y - 1)
                        break
                    }
                    if (field[yid][x] >= height) {
                        current *= (yid - y)
                        break
                    }
                }
                result = maxOf(result, current)
            }
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
