fun main() {

    fun initialForTest(): List<MutableList<Char>> {
        val size = 3
        val crates = arrayListOf(
            mutableListOf('Z', 'N'),
            mutableListOf('M', 'C', 'D'),
            mutableListOf('P')
        )
        return crates
    }

    fun initial(): List<MutableList<Char>> {
        val size = 9
        val crates = arrayListOf(
            mutableListOf('H', 'B', 'V', 'W', 'N', 'M', 'L', 'P'),
            mutableListOf('M', 'Q', 'H'),
            mutableListOf('N', 'D', 'B', 'G', 'F', 'Q', 'M', 'L'),
            mutableListOf('Z', 'T', 'F', 'Q', 'M', 'W', 'G'),
            mutableListOf('M', 'T', 'H', 'P'),
            mutableListOf('C', 'B', 'M', 'J', 'D', 'H', 'G', 'T'),
            mutableListOf('M', 'N', 'B', 'F', 'V', 'R'),
            mutableListOf('P', 'L', 'H', 'M', 'R', 'G', 'S'),
            mutableListOf('P', 'D', 'B', 'C', 'N')
        )
        return crates
    }

//    [P]     [L]         [T]
//    [L]     [M] [G]     [G]     [S]
//    [M]     [Q] [W]     [H] [R] [G]
//    [N]     [F] [M]     [D] [V] [R] [N]
//    [W]     [G] [Q] [P] [J] [F] [M] [C]
//    [V] [H] [B] [F] [H] [M] [B] [H] [B]
//    [B] [Q] [D] [T] [T] [B] [N] [L] [D]
//    [H] [M] [N] [Z] [M] [C] [M] [P] [P]
//    1   2   3   4   5   6   7   8   9

    fun part1(input: List<String>, isTest: Boolean): String {
        val crates = if (isTest) initialForTest() else initial()
        input.forEach { instruction ->
            val (k, from, to) = instruction.split(" ").map { it.toIntOrNull() }.filter { it != null }.map{it!! - 1}
            repeat(k + 1) {
                val element = crates[from].removeLast()
                crates[to].add(element)
            }
        }
        var result = ""
        crates.forEach {
            result += it.last()
        }
        return result
    }

    fun part2(input: List<String>, isTest: Boolean): String {
        val crates = if (isTest) initialForTest() else initial()
        input.forEach { instruction ->
            val (k, from, to) = instruction.split(" ").map { it.toIntOrNull() }.filter { it != null }.map{it!! - 1}
            val toMove = mutableListOf<Char>()
            repeat(k + 1) {
                toMove.add(crates[from].removeLast())
            }
            toMove.reversed().forEach { crates[to].add(it) }
        }
        var result = ""
        crates.forEach {
            result += it.last()
        }
        return result
    }


    val inputTest = readInput("Day05_test")
    check(part1(inputTest, true) == "CMZ")
    check(part2(inputTest, true) == "MCD")

    val input = readInput("Day05")
    println(part1(input, false))
    println(part2(input, false))
}
