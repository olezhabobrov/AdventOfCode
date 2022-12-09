import kotlin.math.abs

//enum class Move(val y: Int, val x: Int) {
//    Left(0, -1),
//    Right(0, 1),
//    Up(1, 0),
//    Down(-1, 0)
//}

class Coordinate(
    var x: Int = 0,
    var y: Int = 0) {

    operator fun plus(increment: Coordinate): Coordinate = Coordinate(x + increment.x, y + increment.y)


    operator fun minus(increment: Coordinate) = Coordinate(x - increment.x, y - increment.y)

    override fun hashCode(): Int =
        (x to y).hashCode()

    override fun equals(other: Any?): Boolean {
        if (other !is Coordinate)
            return false
        return x == other.x && y == other.y
    }

}

class Positions(val sizeOfTail: Int = 1) {
    var curH = Coordinate()
    val curT: Array<Coordinate>

    init {
        curT = Array(sizeOfTail, {_ -> Coordinate()})
    }

    val set = hashSetOf(curT.last())

    fun isClose(a: Coordinate, b: Coordinate): Boolean {
        return (abs(a.x - b.x) <= 1 &&
            abs(a.y - b.y) <= 1
        )
    }

    fun moveH(dir: String) {
        curH +=
            when (dir) {
                "U" -> Coordinate(0, 1)
                "D" -> Coordinate(0, -1)
                "L" -> Coordinate(-1, 0)
                "R" -> Coordinate(1, 0)
                else -> error("")
            }
    }

    fun moveT() {
        for (index in 0 until sizeOfTail) {
            val prev = if (index == 0) curH else curT[index - 1]
            if (!isClose(prev, curT[index])) {
                val delta = prev - curT[index]
                if (abs(delta.x) == 2)
                    delta.x /= 2
                if (abs(delta.y) == 2)
                    delta.y /= 2
                curT[index] += delta
            }
        }
        set.add(curT.last())

    }

}

fun main() {

    fun part1(input: List<String>): Int {
        val positions = Positions()
        input.forEach { line ->
            val (dir, steps) = line.split(" ")
            repeat(steps.toInt()) {
                positions.moveH(dir)
                positions.moveT()
            }
        }
        return positions.set.size
    }

    fun part2(input: List<String>): Int {
        val positions = Positions(9)
        input.forEach { line ->
            val (dir, steps) = line.split(" ")
            repeat(steps.toInt()) {
                positions.moveH(dir)
                positions.moveT()
            }
        }
        return positions.set.size
    }



    val testInput = readInput("Day09_test")
    val testInput2 = readInput("Day09_test2")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)
    check(part2(testInput2) == 36)


    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))

}

