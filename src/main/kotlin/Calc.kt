import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "My Calculator"
    ) {
        MaterialTheme {
            Calculator()
        }
    }
}

@Composable
fun Calculator() {

    var display by remember { mutableStateOf("0") }
    var firstNumber by remember { mutableStateOf<Double?>(null) }
    var operation by remember { mutableStateOf<String?>(null) }
    var shouldResetDisplay by remember { mutableStateOf(false) }

    fun inputNumber(number: String) {

        if (shouldResetDisplay || display == "0") {
            display = number
            shouldResetDisplay = false
        } else {
            display += number
        }
    }

    fun inputDecimal() {

        if (shouldResetDisplay) {
            display = "0."
            shouldResetDisplay = false
        } else if (!display.contains(".")) {
            display += "."
        }
    }

    fun chooseOperation(newOperation: String) {

        firstNumber = display.toDoubleOrNull()
        operation = newOperation
        shouldResetDisplay = true
    }

    fun calculate() {

        val secondNumber = display.toDoubleOrNull()

        if (firstNumber != null && secondNumber != null && operation != null) {

            val result = when (operation) {

                "+" -> firstNumber!! + secondNumber

                "-" -> firstNumber!! - secondNumber

                "*" -> firstNumber!! * secondNumber

                "/" -> {
                    if (secondNumber == 0.0) {
                        display = "Error"
                        firstNumber = null
                        operation = null
                        shouldResetDisplay = true
                        return
                    }

                    firstNumber!! / secondNumber
                }

                else -> return
            }

            display = formatNumber(result)

            firstNumber = null
            operation = null
            shouldResetDisplay = true
        }
    }

    fun clear() {

        display = "0"
        firstNumber = null
        operation = null
        shouldResetDisplay = false
    }

    fun deleteLast() {

        if (display.length > 1) {
            display = display.dropLast(1)
        } else {
            display = "0"
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 20, 20)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .size(width = 360.dp, height = 600.dp)
                .background(
                    color = Color(30, 30, 30),
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {


            Text(
                text = "CALCULATOR",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(
                        color = Color(15, 15, 15),
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {

                Text(
                    text = display,
                    color = Color.White,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                CalculatorButton(
                    text = "C",
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(90, 90, 90),
                    onClick = { clear() }
                )

                CalculatorButton(
                    text = "⌫",
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(90, 90, 90),
                    onClick = { deleteLast() }
                )

                CalculatorButton(
                    text = "÷",
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(255, 149, 0),
                    onClick = { chooseOperation("/") }
                )

                CalculatorButton(
                    text = "×",
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(255, 149, 0),
                    onClick = { chooseOperation("*") }
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                CalculatorButton(
                    text = "7",
                    modifier = Modifier.weight(1f),
                    onClick = { inputNumber("7") }
                )

                CalculatorButton(
                    text = "8",
                    modifier = Modifier.weight(1f),
                    onClick = { inputNumber("8") }
                )

                CalculatorButton(
                    text = "9",
                    modifier = Modifier.weight(1f),
                    onClick = { inputNumber("9") }
                )

                CalculatorButton(
                    text = "−",
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(255, 149, 0),
                    onClick = { chooseOperation("-") }
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                CalculatorButton(
                    text = "4",
                    modifier = Modifier.weight(1f),
                    onClick = { inputNumber("4") }
                )

                CalculatorButton(
                    text = "5",
                    modifier = Modifier.weight(1f),
                    onClick = { inputNumber("5") }
                )

                CalculatorButton(
                    text = "6",
                    modifier = Modifier.weight(1f),
                    onClick = { inputNumber("6") }
                )

                CalculatorButton(
                    text = "+",
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(255, 149, 0),
                    onClick = { chooseOperation("+") }
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                CalculatorButton(
                    text = "1",
                    modifier = Modifier.weight(1f),
                    onClick = { inputNumber("1") }
                )

                CalculatorButton(
                    text = "2",
                    modifier = Modifier.weight(1f),
                    onClick = { inputNumber("2") }
                )

                CalculatorButton(
                    text = "3",
                    modifier = Modifier.weight(1f),
                    onClick = { inputNumber("3") }
                )

                CalculatorButton(
                    text = "=",
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(40, 160, 90),
                    onClick = { calculate() }
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                CalculatorButton(
                    text = "0",
                    modifier = Modifier.weight(2f),
                    onClick = { inputNumber("0") }
                )

                CalculatorButton(
                    text = ".",
                    modifier = Modifier.weight(1f),
                    onClick = { inputDecimal() }
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(55, 55, 55),
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {

        Text(
            text = text,
            fontSize = 23.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}

fun formatNumber(number: Double): String {

    return if (number % 1.0 == 0.0) {
        number.toLong().toString()
    } else {
        number.toString()
    }
}