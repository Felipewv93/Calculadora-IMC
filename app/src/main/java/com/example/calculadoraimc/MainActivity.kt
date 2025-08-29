package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
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
                    color = MaterialTheme.colorScheme.surface
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
    var classificacao by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .border(width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(35.dp))
            .background(color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(35.dp)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de IMC",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 50.dp,bottom = 75.dp),
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onTertiary
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(3.dp)),
            horizontalAlignment = Alignment.Start
        ) {
            TextButton(onClick = { infoIMC = !infoIMC }) {
            Text(
                text = if (infoIMC) "Ocultar" else "O que é IMC?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        }
            if (infoIMC) {
                Text(
                    text = "O IMC (Índice de Massa Corporal) é uma medida que mostra a relação entre o peso e a altura de uma pessoa.",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Medium
                )
            }

            TextButton(onClick = { infoUso = !infoUso }) {
                Text(
                    text = if (infoUso) "Ocultar" else "Para que serve?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
            if (infoUso) {
                Text(
                    text = "Ele é usado por profissionais da saúde para identificar riscos relacionados à desnutrição, sobrepeso e obesidade.",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Medium
                )
            }

            TextButton(onClick = { infoCalc = !infoCalc }) {
                Text(
                    text = if (infoCalc) "Ocultar" else "Como é feito o cálculo?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
            if (infoCalc) {
                Text(
                    text = "O cálculo é feito dividindo o peso (em kg) pela altura ao quadrado (em metros).",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(8.dp, 0.dp, 8.dp, 5.dp),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(55.dp))
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Digite seu peso (kg)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Digite sua altura (m)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            )
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
                        val p = peso.replace(",",".").toDoubleOrNull()
                        val a = altura.replace(",",".").toDoubleOrNull()
                        if (p != null && a != null) {
                            val imc = p / (a * a)
                            resultado = "%.2f".format(imc)
                            classificacao = classificarIMC(imc)
                        } else {
                            resultado = "Digite apenas valores numéricos!"
                        }
                    } else {
                        resultado = "Preencha todos os campos!"
                    }
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(160.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onSurface),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Text("Calcular IMC",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                    )
            }

            Button(
                onClick = {
                    peso = ""
                    altura = ""
                    resultado = ""
                    classificacao = ""
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(160.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onSurface),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Text("Restart",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(60.dp))

        if (classificacao.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp)),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onTertiary)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = "Seu IMC: ",
                        modifier = Modifier.padding(5.dp),
                        fontSize = 21.sp
                    )
                    Text(
                        text = resultado,
                        modifier = Modifier.padding(5.dp),
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Text(text = "Classificação: ",
                        modifier = Modifier.padding(5.dp),
                        fontSize = 21.sp
                    )

                    Text(text = classificacao,
                        modifier = Modifier.padding(5.dp),
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = corIMC(classificacao))
                }
            }
        } else if (classificacao.isEmpty() && resultado.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp)),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onTertiary)
            ) {
                Text (
                    text = resultado,
                    modifier = Modifier.padding(10.dp),
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            }
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

private fun corIMC(classificacao: String): Color {
    return when (classificacao) {
        "Abaixo do peso" -> Color(0xFFFF9800)
        "Peso normal" -> Color(0xFF4CAF50)
        "Sobrepeso" -> Color(0xFFFF9800)
        else -> Color(0xFFF44336)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculadoraIMC() {
    CalculadoraIMCTheme {
        CalculadoraIMCScreen()
    }
}
