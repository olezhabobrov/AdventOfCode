fun main() {
    fun List<String>.toPath(): String = this.joinToString(separator = "/")

    fun part1(input: List<String>): Int {
        val currentPosition = mutableListOf("")
        val sizeMap = mutableMapOf<String, Int>()
        input.forEach { line ->
            val lineSplited = line.split(" ")
            if (lineSplited[0] == "$") {
                if (lineSplited[1] == "cd") {
                    if (lineSplited[2] == "..") {
                        val prevPath = currentPosition.toPath()
                        currentPosition.removeLast()
                        sizeMap[currentPosition.toPath()] =
                            sizeMap.getOrDefault(currentPosition.toPath(), 0) + sizeMap[prevPath]!!
                    } else {
                        currentPosition.add(lineSplited[2])
                    }
                }
            } else {
                if (lineSplited[0] != "dir") {
                    sizeMap[currentPosition.toPath()] =
                        sizeMap.getOrDefault(currentPosition.toPath(), 0) + lineSplited[0].toInt()
                }
            }
        }
        return sizeMap.map { (_, size)  -> size }.filter { it <= 100000 }.sum()
    }

    fun part2(input: List<String>): Int {
        val currentPosition = mutableListOf<String>()
        val sizeMap = mutableMapOf<String, Int>()
        input.forEach { line ->
            val lineSplited = line.split(" ")
            if (lineSplited[0] == "$") {
                if (lineSplited[1] == "cd") {
                    if (lineSplited[2] == "..") {
                        val prevPath = currentPosition.toPath()
                        currentPosition.removeLast()
                        sizeMap[currentPosition.toPath()] =
                            sizeMap.getOrDefault(currentPosition.toPath(), 0) + sizeMap[prevPath]!!
                    } else {
                        if (lineSplited[2] != "/")
                            currentPosition.add(lineSplited[2])
                    }
                }
            } else {
                if (lineSplited[0] != "dir") {
                    sizeMap[currentPosition.toPath()] =
                        sizeMap.getOrDefault(currentPosition.toPath(), 0) + lineSplited[0].toInt()
                }
            }
        }
        while (currentPosition.isNotEmpty()) {
            val prevPath = currentPosition.toPath()
            currentPosition.removeLast()
            sizeMap[currentPosition.toPath()] =
                sizeMap.getOrDefault(currentPosition.toPath(), 0) + sizeMap[prevPath]!!
        }
        val usedSpace = sizeMap[""]!!
        val spaceToFree = 30000000 - (70000000 - usedSpace)
        return sizeMap.map { (_, size)  -> size }.sorted().filter { it >= spaceToFree}.first()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
