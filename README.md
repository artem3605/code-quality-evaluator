# Code quality evaluator

## How to use

To run the code quality evaluator for Kotlin programs, you need just to run the file **Main.kt** and pass the path of
the file you want to
evaluate as an argument.

## How it works

I implemented two features to evaluate the code quality of a file:

1. **Complexity of the methods/functions**: I decided just to count the number of keywords to evaluate the complexity of
   the methods/functions.
2. **Code style**: Program checks names of methods and functions.

For the code analyzer, I decided to write a separate class that implements a code parser and functions responsible for
calculating certain metrics. I also wrote small unit tests in order to check that everything is working really
correctly.