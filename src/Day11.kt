
class Monkeys(input: List<String>, val toDivide: Boolean = true) {
    val monkeys: MutableList<Monkey> = mutableListOf()

    init {
        input.chunked(7).forEach { monkeyString ->
            val items = monkeyString[1].split(" ", ",").map{ it.toLongOrNull() }
                .filter { it != null }.toMutableList()
            val opStr = monkeyString[2].removePrefix("  Operation: new = ")
            val (a, op, b) = opStr.split(" ")
            val operation: (Long) -> Long = { old: Long ->
                val a2 = if (a == "old") old else a.toLong()
                val b2 = if (b == "old") old else b.toLong()
                if (op == "*") a2 * b2 else if (op == "+") a2 + b2 else error("not valid operation")
            }
            val x = monkeyString[3].split(" ").map { it.toLongOrNull() }
                .filter { it != null }.first()!!
            val testItem: (Long) -> Boolean = { z -> z % x == 0L }
            val monkey1 = monkeyString[4].split(" ").map { it.toIntOrNull() }
                .filter { it != null }.first()!!
            val monkey2 = monkeyString[5].split(" ").map { it.toIntOrNull() }
                .filter { it != null }.first()!!
            val trueOp: (Long) -> Unit = { item ->
                monkeys[monkey1].items.add(item)
            }
            val falseOp: (Long) -> Unit = { item ->
                monkeys[monkey2].items.add(item)
            }
            monkeys.add(Monkey(items, operation, testItem, trueOp, falseOp))
        }
    }

    fun process(index: Int) {
        val monkey = monkeys[index]
        while (monkey.items.isNotEmpty()) {
            val item = monkey.items.removeFirst()!!
            monkey.counter++
            val newLevel = if (toDivide)
                monkey.operation(item) / 3L
            else
                monkey.operation(item)
            if (monkey.testItem(newLevel)) {
                monkey.trueOp(newLevel)
            } else {
                monkey.falseOp(newLevel)
            }
        }
    }

    class Monkey(
        val items: MutableList<Long?>,
        val operation: (Long) -> Long,
        val testItem: (Long) -> Boolean,
        val trueOp: (Long) -> Unit,
        val falseOp: (Long) -> Unit
    ) {
        var counter: Long = 0L
    }
}

fun main() {
    fun part1(input: List<String>): Long {
        val monkeys = Monkeys(input)
        repeat(20) {
            for (index in 0 until monkeys.monkeys.size) {
                monkeys.process(index)
            }
        }
        val sorted = monkeys.monkeys.sortedByDescending { monkey -> monkey.counter }
        return sorted[0].counter * sorted[1].counter
    }

    fun part2(input: List<String>): Long {
        val monkeys = Monkeys(input, false)
        repeat(10000) {
            for (index in 0 until monkeys.monkeys.size) {
                monkeys.process(index)
            }
        }
        val sorted = monkeys.monkeys.sortedByDescending { monkey -> monkey.counter }
        return sorted[0].counter * sorted[1].counter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 10605L)
    println(part2(testInput))
//    check(part2(testInput) == 2713310158L)


    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}