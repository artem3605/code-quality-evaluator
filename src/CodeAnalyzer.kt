import java.io.File

class CodeAnalyzer {
    private var code: List<String> = listOf()

    private fun calculateComplexityOfFunction(function: String): Int {
        val keyWords =
            listOf("if", "when", "for", "while", "repeat") // words which increase the complexity of functions
        return function.split(" ", "(").count { keyWords.contains(it) }
    }

    private fun getListOfFunctionsFromCode(): List<Pair<String, String>> {
        // returns list of functions with their names and bodies
        var ind = 0
        val functions: MutableList<Pair<String, String>> = mutableListOf()
        while (ind < code.size) {
            if (code[ind] == "fun") {
                var function = ""
                val name = code[ind + 1].split("(")[0]
                ind += 2
                var firstOpen = false // the first open bracket appeared or not
                var balance = 0
                while (balance != 0 || !firstOpen) {
                    function += code[ind] + " "
                    if (code[ind].contains("{")) {
                        firstOpen = true
                        balance++
                    }
                    if (code[ind].contains("}")) {
                        balance--
                    }
                    ind++
                }
                functions.add(Pair(name, function))
            } else {
                ind++
            }
        }
        return functions
    }

    private fun checkCamelCase(name: String): Boolean {
        val regex = "[a-z]+((\\d)|([A-Z0-9][a-z0-9]+))*([A-Z])?".toRegex()
        return regex.matches(name)
    }

    fun readCodeFromFile(path: String) {
        val file = File(path)
        code = file.readText().split(" ", "\n")
    }

    fun analyze(): List<Pair<String, Int>> {
        val complexities: MutableMap<String, Int> = mutableMapOf()
        val functions = getListOfFunctionsFromCode()
        functions.forEach {
            complexities[it.first] = calculateComplexityOfFunction(it.second)
        }
        val topThreeMethods = complexities.entries.sortedByDescending { it.value }.take(3)
        return topThreeMethods.map { Pair(it.key, it.value) }
    }

    fun codeStyleCheck(): Int {
        val functions = getListOfFunctionsFromCode()
        val badFunctions = functions.count { !checkCamelCase(it.first) }
        val totalFunctions = functions.size
        return badFunctions * 100 / totalFunctions
    }
}