package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine

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
    var infoIMC by remember { mutableStateOf(false) }
    var infoUso by remember {mutableStateOf(false)}
    var infoCalc by remember { mutableStateOf(false)}
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de IMC",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 25.dp,bottom = 50.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            TextButton(onClick = { infoIMC = !infoIMC }) {
            Text(
                text = if (infoIMC) "Ocultar" else "O que é IMC?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
            if (infoIMC) {
                Text(
                    text = "O IMC (Índice de Massa Corporal) é uma medida que mostra a relação entre o peso e a altura de uma pessoa.",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            TextButton(onClick = { infoUso = !infoUso }) {
                Text(
                    text = if (infoUso) "Ocultar" else "Para que serve?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            if (infoUso) {
                Text(
                    text = "Ele é usado por profissionais da saúde para identificar riscos relacionados à desnutrição, sobrepeso e obesidade.",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            TextButton(onClick = { infoCalc = !infoCalc }) {
                Text(
                    text = if (infoCalc) "Ocultar" else "Como é feito o cálculo?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            if (infoCalc) {
                Text(
                    text = "O cálculo é feito dividindo o peso (em kg) pela altura ao quadrado (em metros).",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        } // fim coluna botões

        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Digite seu peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Digite sua altura (m)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(35.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                modifier = Modifier
                    .height(50.dp)
                    .width(160.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Text("Calcular IMC",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    )
            }

            Button(
                onClick = {
                    peso = ""
                    altura = ""
                    resultado = ""
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(160.dp),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.Unspecified
                )
            ) {
                Text("Restart",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        if (resultado.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Text(
                    resultado,
                    modifier = Modifier.padding(15.dp),
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    } //fim da coluna principal
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
