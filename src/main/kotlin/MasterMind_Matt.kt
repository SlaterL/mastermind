fun main() {
    var wins = 0
    var loses = 0
    var total = 0
    for (i in 0..999) {
        val round = startGame()
        if (round > 0) {
            wins += 1
            total += round
        } else loses += 1
    }
    val avg = total.toFloat() / wins
    println("wins: $wins losses: $loses")
    println("avg # of rounds per win: $avg")
    // testCheckResult()
}

fun startGame(): Int {
    //create code
    val code = createCode()
    val results = mutableListOf<Result>()

    for (i in 0..9) {
        val guess = makeGuess(results)
        val result = checkResult(guess, code)
        if (result.rightColorRightPosition == 4) {
            return i + 1
        }
    }
    return -1
}

fun makeGuess(previousGuesses: List<Result>): List<Colors> {
    // TODO
    return listOf()
}

fun checkResult(guess: List<Colors>, code: List<Colors>): Result {
    var rightColorRightPlace = 0
    var rightColorWrongPlace = 0
    val usedIndex = mutableMapOf<Int, Boolean>()
    guess.forEachIndexed { guessIndex, guessColor ->
        if (code[guessIndex] == guessColor) {
            rightColorRightPlace += 1
            usedIndex.put(guessIndex, true)
        } else {
            code.forEachIndexed { codeIndex, codeColor ->
                if (guessIndex != codeIndex && usedIndex[codeIndex] != true) {
                    if (guessColor == codeColor) {
                        rightColorWrongPlace += 1
                        usedIndex.put(codeIndex, true)
                    }

                }
            }
        }
    }
    return Result(guess, rightColorRightPlace, rightColorWrongPlace)
}

fun createCode(): List<Colors> {
    val code = mutableListOf<Colors>()
    for (i in 0..3) {
        val colors = Colors.values()
        colors.shuffle()
        code.add(colors.first())
    }
    return code
}

data class Result(
    val guess: List<Colors>,
    val rightColorRightPosition: Int,
    val rightColorWrongPosition: Int
)


enum class Colors {
    RED,
    YELLOW,
    BLUE,
    GREEN,
    WHITE,
    BLACK
}

fun testCheckResult() {
    var result = checkResult(
        listOf(Colors.RED, Colors.RED, Colors.GREEN, Colors.GREEN),
        listOf(Colors.GREEN, Colors.GREEN, Colors.BLUE, Colors.BLUE)
    )
    println(result.rightColorWrongPosition == 2)
    println(result.rightColorRightPosition == 0)

    result = checkResult(
        listOf(Colors.GREEN, Colors.GREEN, Colors.GREEN, Colors.GREEN),
        listOf(Colors.GREEN, Colors.GREEN, Colors.BLUE, Colors.BLUE)
    )
    println(result.rightColorWrongPosition == 0)
    println(result.rightColorRightPosition == 2)

    result = checkResult(
        listOf(Colors.RED, Colors.RED, Colors.RED, Colors.RED),
        listOf(Colors.GREEN, Colors.GREEN, Colors.BLUE, Colors.BLUE)
    )
    println(result.rightColorWrongPosition == 0)
    println(result.rightColorRightPosition == 0)

    result = checkResult(
        listOf(Colors.RED, Colors.RED, Colors.GREEN, Colors.GREEN),
        listOf(Colors.RED, Colors.RED, Colors.GREEN, Colors.GREEN)
    )
    println(result.rightColorWrongPosition == 0)
    println(result.rightColorRightPosition == 4)

}
