# 🧮 Calculadora de IMC

Aplicativo Android simples desenvolvido em **Kotlin** com **Jetpack Compose** para calcular o **Índice de Massa Corporal (IMC)**.  

## ⚡ Funcionalidades

- Entrada de **peso (kg)** e **altura (m)**  
- Cálculo automático do **IMC** ao clicar no botão  
- Classificação do IMC:  
  - **Abaixo do peso**  
  - **Peso normal**  
  - **Sobrepeso**  
  - **Obesidade Grau I**  
  - **Obesidade Grau II**  
  - **Obesidade Grau III**  
- Alerta caso algum campo não seja preenchido  

## 🛠 Tecnologias Utilizadas

- **Kotlin**  
- **Jetpack Compose**  
- **Android Studio**  
- **Material3**  

## 🖼 Layout do App

- Tela única com:  
  - **Título:** “Calculadora de IMC”  
  - **Campos de texto** para peso e altura  
  - **Botão** para calcular o IMC  
  - **Área** para exibir o resultado e a classificação  

## 🚀 Como Usar

1. Clone o repositório:  
   git clone 
2. Abra o projeto no **Android Studio**
3. Execute o app em um **emulador** ou **dispositivo físico**
4. Insira seu **peso** e **altura** e clique em **Calcular IMC**

## 📝 Observações

- A função `classificarIMC` define a **categoria do IMC**
- Atualmente o **resultado** é exibido na própria tela após o cálculo
