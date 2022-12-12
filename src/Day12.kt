fun main() {

    fun Char.mycode(): Int {
        if (this == 'S')
            return 'a'.code
        if (this == 'E')
            return 'z'.code
        return this.code
    }

    fun part1(field: List<String>): Int {
        val q = mutableListOf<Triple<Int, Int, Int>>()
        val X = field[0].length
        val Y = field.size
        field.forEachIndexed { y, s ->
            val pos = s.indexOf('S')
            if (pos != -1) {
                q.add(Triple(y, pos, 0))
            }
        }
        val visited = hashSetOf<Pair<Int, Int>>()
        while (q.isNotEmpty()) {
            val (y, x, dist) = q.removeFirst()
            if (field[y][x] == 'E')
                return dist
            if (visited.contains(y to x))
                continue
            visited.add(y to x)
            if (y + 1 < Y &&
                field[y + 1][x].mycode() - field[y][x].mycode() <= 1 &&
                !visited.contains(y + 1 to x)) {
                q.add(Triple(y + 1, x, dist + 1))
            }
            if (y - 1 >= 0 &&
                field[y - 1][x].mycode() - field[y][x].mycode() <= 1 &&
                !visited.contains(y - 1 to x)) {
                q.add(Triple(y - 1, x, dist + 1))
            }
            if (x + 1 < X &&
                field[y][x + 1].mycode() - field[y][x].mycode() <= 1 &&
                !visited.contains(y to x + 1)) {
                q.add(Triple(y, x + 1, dist + 1))
            }
            if (x - 1 >= 0 &&
                field[y][x - 1].mycode() - field[y][x].mycode() <= 1 &&
                !visited.contains(y to x - 1)) {
                q.add(Triple(y, x - 1, dist + 1))
            }
        }
        error("")
    }

    fun part2(field: List<String>): Int {
        val q = mutableListOf<Triple<Int, Int, Int>>()
        val X = field[0].length
        val Y = field.size
        val visited = hashSetOf<Pair<Int, Int>>()
        field.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                if (c == 'a' || c == 'S') {
                    q.add(Triple(y, x, 0))
                    visited.add(y to x)
                }
            }
        }
        while (q.isNotEmpty()) {
            val (y, x, dist) = q.removeFirst()
            if (field[y][x] == 'E')
                return dist
            if (y + 1 < Y &&
                field[y + 1][x].mycode() - field[y][x].mycode() <= 1 &&
                !visited.contains(y + 1 to x)) {
                visited.add(y + 1 to x)
                q.add(Triple(y + 1, x, dist + 1))
            }
            if (y - 1 >= 0 &&
                field[y - 1][x].mycode() - field[y][x].mycode() <= 1 &&
                !visited.contains(y - 1 to x)) {
                visited.add(y - 1 to x)
                q.add(Triple(y - 1, x, dist + 1))
            }
            if (x + 1 < X &&
                field[y][x + 1].mycode() - field[y][x].mycode() <= 1 &&
                !visited.contains(y to x + 1)) {
                visited.add(y to x + 1)
                q.add(Triple(y, x + 1, dist + 1))
            }
            if (x - 1 >= 0 &&
                field[y][x - 1].mycode() - field[y][x].mycode() <= 1 &&
                !visited.contains(y to x - 1)) {
                visited.add(y to x - 1)
                q.add(Triple(y, x - 1, dist + 1))
            }
        }
        error("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 31)
    check(part2(testInput) == 29)

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}
