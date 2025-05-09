import kotlin.math.PI

fun main() {
    val ex = Exercises()
    ex.main()
}

class Exercises {

    fun main() {
        println("Running the main function")
        one()
        two()
        three()
        four()
        five()
        six()
        seven()
        eight()
        nine()
        ten()
        eleven()
        twelve()
    }

    /**
     * Complete the code to make the program print "Mary is 20 years old" to standard output:
     *
     * fun main() {
     * val name = "Mary"
     * val age = 20
     * // Write your code here
     * }
     */
    fun one() {
        val name = "Mary"
        val age = 20
        //Solution
        println("$name is $age years old")
    }

    /**
     * Explicitly declare the correct type for each variable:
     *
     * fun main() {
     * val a: Int = 1000
     * val b = "log message"
     * val c = 3.14
     * val d = 100_000_000_000_000
     * val e = false
     * val f = '\n'
     * }
     */
    fun two() {
        //Solution
        val a: Int = 1000
        val b: String = "log message"
        val c: Double = 3.14
        val d: Long = 100_000_000_000_000
        val e: Boolean = false
        val f: Char = '\n'
    }

    /**
     * You have a list of “green” numbers and a list of “red” numbers. Complete the code to print how many numbers there are in total.
     *
     * fun main() {
     * val greenNumbers = listOf(1, 4, 23)
     * val redNumbers = listOf(17, 2)
     * // Write your code here
     * }
     */
    fun three() {
        val greenNumbers = listOf(1, 4, 23)
        val redNumbers = listOf(17, 2)
        val totalCount = greenNumbers.count() + redNumbers.count()
        println(totalCount)
    }

    /**
     * You have a set of protocols supported by your server. A user requests to use a particular protocol. Complete the program to check whether the requested protocol
     * is supported or not (isSupported must be a Boolean value).
     *
     * fun main() {
     * val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
     * val requested = "smtp"
     * val isSupported = // Write your code here
     * println("Support for $requested: $isSupported")
     * }
     */
    fun four() {
        val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
        val requested = "smtp"
        val isSupported = requested.uppercase() in SUPPORTED
        println("Support for $requested: $isSupported")
    }

    /**
     *Define a map that relates integer numbers from 1 to 3 to their corresponding spelling. Use this map to spell the given number.
     *
     * fun main() {
     * val number2word = // Write your code here
     * val n = 2
     * println("$n is spelt as '${<Write your code here >}'")
     * }
     */
    fun five() {
        val number2word = mapOf(1 to "one", 2 to "two", 3 to "three")
        val n = 2
        println("$n is spelt as '${number2word[n]}'")
    }

    /**
     * You have a program that counts pizza slices until there’s a whole pizza with 8 slices. Refactor this program in two ways:
     * Use a while loop.
     * Use a do-while loop.
     *
     * fun main() {
     * var pizzaSlices = 0
     * // Start refactoring here
     * pizzaSlices++
     * println("There's only $pizzaSlices slice/s of pizza :(")
     * pizzaSlices++
     * println("There's only $pizzaSlices slice/s of pizza :(")
     * pizzaSlices++
     * println("There's only $pizzaSlices slice/s of pizza :(")
     * pizzaSlices++
     * println("There's only $pizzaSlices slice/s of pizza :(")
     * pizzaSlices++
     * println("There's only $pizzaSlices slice/s of pizza :(")
     * pizzaSlices++
     * println("There's only $pizzaSlices slice/s of pizza :(")
     * pizzaSlices++
     * println("There's only $pizzaSlices slice/s of pizza :(")
     * pizzaSlices++
     * // End refactoring here
     * println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
     * }
     */
    fun six() {
        var pizzaSlices = 0
        while ( pizzaSlices < 7 ) {
            pizzaSlices++
            println("There's only $pizzaSlices slice/s of pizza :(")
        }
        pizzaSlices++
        println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
    }

    fun seven(){
        var pizzaSlices = 0
        pizzaSlices++
        do {
            println("There's only $pizzaSlices slice/s of pizza :(")
            pizzaSlices++
        } while ( pizzaSlices < 8 )
        println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
    }

    /**
     * Write a program that simulates the Fizz buzz game. Your task is to print numbers from 1 to 100 incrementally, replacing any number divisible by three with the word
     * "fizz", and any number divisible by five with the word "buzz". Any number divisible by both 3 and 5 must be replaced with the word "fizzbuzz".
     */
    fun eight(){
        for (number in 1..100) {
            println(
                when {
                    number % 15 == 0 -> "fizzbuzz"
                    number % 3 == 0 -> "fizz"
                    number % 5 == 0 -> "buzz"
                    else -> "$number"
                }
            )
        }
    }

    /**
     * You have a list of words. Use for and if to print only the words that start with the letter l.
     *
     * fun main() {
     * val words = listOf("dinosaur", "limousine", "magazine", "language")
     * // Write your code here
     * }
     */
    fun nine(){
        val words = listOf("dinosaur", "limousine", "magazine", "language")
        for (w in words) {
            if (w.startsWith("l"))
                println(w)
        }
    }

    /**
     *Write a function called circleArea that takes the radius of a circle in integer format as a parameter and outputs the area of that circle.
     *
     * import kotlin.math.PI
     * // Write your code here
     * fun main() {
     * println(circleArea(2))
     * }
     */
    fun circleArea(radius: Int): Double {
        return PI * radius * radius
    }
    fun ten() {
        println(circleArea(2))
    }

    /**
     * Rewrite the circleArea function from the previous exercise as a single-expression function.
     *
     * import kotlin.math.PI
     * // Write your code here
     * fun main() {
     * println(circleArea(2))
     * }
     */

    fun circleArea2(radius: Int): Double = PI * radius * radius
    fun eleven() {
        println(circleArea2(2))
    }

    /**
     * You have a function that translates a time interval given in hours, minutes, and seconds into seconds. In most cases, you need to pass only one or two function
     * parameters while the rest are equal to 0. Improve the function and the code that calls it by using default parameter values and named arguments so that the code is
     * easier to read.
     *
     * fun intervalInSeconds(hours: Int, minutes: Int, seconds: Int) =
     * ((hours * 60) + minutes) * 60 + seconds
     * fun main() {
     * println(intervalInSeconds(1, 20, 15))
     * println(intervalInSeconds(0, 1, 25))
     * println(intervalInSeconds(2, 0, 0))
     * println(intervalInSeconds(0, 10, 0))
     * println(intervalInSeconds(1, 0, 1))
     * }
     */

    fun intervalInSeconds(hours: Int = 0, minutes: Int = 0, seconds: Int = 0) =
        ((hours * 60) + minutes) * 60 + seconds
    fun twelve() {
        println(intervalInSeconds(1, 20, 15))
        println(intervalInSeconds(minutes = 1, seconds = 25))
        println(intervalInSeconds(hours = 2))
        println(intervalInSeconds(minutes = 10))
        println(intervalInSeconds(hours = 1, seconds = 1))
    }
}