fun main() {
    fun Char.priority(): Int {
        return if (isLowerCase())
            code - 'a'.code + 1
        else
            code - 'A'.code  + 27
    }

    fun part1(input: List<String>): Int =
        input.sumOf { rucksack ->
            val (first, second) =
                rucksack.map { item -> item.priority() }
                    .chunked(rucksack.length / 2)
                    .map { compartment -> compartment.toSet() }
            first.sumOf { if (second.contains(it)) it else 0 }
        }

    fun part2(input: List<String>): Int =
        input.chunked(3).sumOf { team ->
            val sets = team.map { elf -> elf.map { item -> item.priority() }.toSet() }
            (sets[0] intersect sets[1] intersect sets[2]).random()
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
