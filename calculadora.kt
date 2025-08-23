package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraIMCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculadoraIMCScreen()
                }
            }
        }
    }
}



@Composable
fun CalculadoraIMCScreen() {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de IMC",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Digite seu peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Digite sua altura (m)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (peso.isNotEmpty() && altura.isNotEmpty()) {
                    val p = peso.replace(",",".")
                    val a = altura.replace(",",".")
                    val p1 = p.toDoubleOrNull()
                    val a1 = a.toDoubleOrNull()
                    if (p1 != null && a1 != null) {
                        val imc = p1 / (a1 * a1)
                        resultado = "Seu IMC: %.2f \n%s".format(imc, classificarIMC(imc))
                    } else {
                        resultado = "Digite apenas valores numéricos"
                    }
                } else {
                    resultado = "Preencha todos os campos!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular IMC" )


            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = resultado,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Button(
            onClick = {
                peso = ""
                altura = ""
                resultado = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Restart")
        }
    }
}

private fun classificarIMC(imc: Double): String {
    return when {
        imc < 18.5 -> "Abaixo do peso"
        imc < 24.9 -> "Peso normal"
        imc < 29.9 -> "Sobrepeso"
        imc < 34.9 -> "Obesidade Grau I"
        imc < 39.9 -> "Obesidade Grau II"
        else -> "Obesidade Grau III"
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculadoraIMC() {
    CalculadoraIMCTheme {
        CalculadoraIMCScreen()
    }
}
