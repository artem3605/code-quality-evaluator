
fun main() {
    val codeAnalyzer = CodeAnalyzer()
    while (true) {
        println("Enter the path to the file with Kotlin code:")
        val path = readln()
        codeAnalyzer.readCodeFromFile(path)
        val topMethods = codeAnalyzer.analyze()
        val percentage = codeAnalyzer.codeStyleCheck()
        println("Top 3 methods with highest complexity:")
        topMethods.forEach {
            println("${it.first} - ${it.second}")
        }
        println("Percentage of functions with bad naming: $percentage%")
    }

}