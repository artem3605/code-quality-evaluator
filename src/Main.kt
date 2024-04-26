
fun main() {
    val codeAnalyzer = CodeAnalyzer()
    codeAnalyzer.readCodeFromFile("src/CodeAnalyzer.kt")
    codeAnalyzer.analyze()
    codeAnalyzer.codeStyleCheck()

}