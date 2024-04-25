import java.io.File

class CodeAnalyzer() {
    private var code: List<String> = listOf()
    private fun calculateComplexityOfFunction(function: String): Int {
        val keyWords = listOf("if", "when", "for", "while")
        return function.split(" ", "(").count { keyWords.contains(it) }
    }

    fun readCodeFromFile(path: String) {
        val file = File(path)
        code = file.readText().split(" ")
    }

    fun analyze() {
        var ind = 0
        val complexities: MutableMap<String, Int> = mutableMapOf()

        while (ind < code.size) {
            if (code[ind] == "fun") {
                var function = ""
                val name = code[ind + 1].split("(")[0]
                ind += 2
                var firstOpen = false // the first open bracket appeared or not
                var balance = 0
                while (balance != 0 || !firstOpen) {
                    function += code[ind] + " "
                    if (code[ind] == "fun"){
                        break
                    }
                    if (code[ind].contains("{")) {
                        firstOpen = true
                        balance++
                    }
                    if (code[ind].contains("}")) {
                        balance--
                    }
                    ind++
                }
                complexities[name] = calculateComplexityOfFunction(function)
            }
            else {
                ind++
            }
        }
        val topThreeMethods = complexities.entries.sortedByDescending { it.value }.take(3)
        println("Top 3 methods by complexity:")
        topThreeMethods.forEach { println("${it.key} - ${it.value}") }
    }
}