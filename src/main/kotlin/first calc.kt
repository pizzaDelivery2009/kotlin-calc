fun main() {

    while (true) {

        print("Введите операцию (+, -, *, /) или exit для выхода:")
        val operation = readLine()
        if (operation == "exit") {
            break
        }

        println("Введите первое число: ")
        val FirstNumber = readLine()?.toDoubleOrNull()
        if (FirstNumber == null) {
            println("Вы ввели неправильное число!")
            continue
        }

        println("Введите второе число: ")
        val SecondNumber = readLine()?.toDoubleOrNull()
        if (SecondNumber == null) {
            println("Вы введи неправильное число!")
            continue
        }

        val result = when (operation) {
            "+" -> FirstNumber!! + SecondNumber!!
            "-" -> FirstNumber!! - SecondNumber!!
            "*" -> FirstNumber!! * SecondNumber!!
            "/" -> if (SecondNumber != 0.0) {
                FirstNumber!! / SecondNumber!!
            } else {
                println("Ошибка!")
            }

            else -> {
                println("Ошибка!")
                continue
            }
        }
        println("Результат: $result")
    }

    println("Завершим программу")
}