import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CodeAnalyzerTest {
    private val codeAnalyzer = CodeAnalyzer()

    @Test
    fun analyze() {
        codeAnalyzer.readCodeFromFile("samples/Sample1.kt")
        var topMethods = codeAnalyzer.analyze()
        assertEquals(topMethods.size, 3)
        assertEquals(topMethods[0], Pair("main", 8))
        assertEquals(topMethods[1], Pair("dfs", 2))
        assertEquals(topMethods[2], Pair("compareTo", 2))

        codeAnalyzer.readCodeFromFile("samples/Sample2.kt")
        topMethods = codeAnalyzer.analyze()
        assertEquals(topMethods.size, 1)
        assertEquals(topMethods[0], Pair("main", 6))

        codeAnalyzer.readCodeFromFile("samples/Sample3.kt")
        topMethods = codeAnalyzer.analyze()
        assertEquals(topMethods.size, 2)
        assertEquals(topMethods[0], Pair("main", 5))
        assertEquals(topMethods[1], Pair("swap_543", 0))


    }

    @Test
    fun codeStyleCheck() {
        codeAnalyzer.readCodeFromFile("samples/Sample1.kt")
        var percentage = codeAnalyzer.codeStyleCheck()
        assertEquals(0, percentage)

        codeAnalyzer.readCodeFromFile("samples/Sample2.kt")
        percentage = codeAnalyzer.codeStyleCheck()
        assertEquals(0, percentage)

        codeAnalyzer.readCodeFromFile("samples/Sample3.kt")
        percentage = codeAnalyzer.codeStyleCheck()
        assertEquals(50, percentage)

    }
}