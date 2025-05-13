# Conversor de Monedas con Java

[![Java](https://img.shields.io/badge/Java-17+-blue)] [![Maven](https://img.shields.io/badge/Maven-3.8+-orange)] [![License](https://img.shields.io/badge/License-MIT-green)]

Aplicación de consola para conversión de monedas utilizando tasas de cambio en tiempo real.

## Características Principales
- Consulta tasas de cambio actualizadas
- 6 pares de conversión disponibles
- Interfaz intuitiva en consola
- Manejo robusto de errores
- Configuración segura de API Key

## Pares de Conversión
1. USD ↔ MXN (Dólar ↔ Peso Mexicano)
2. USD ↔ EUR (Dólar ↔ Euro) 
3. USD ↔ BRL (Dólar ↔ Real Brasileño)
4. EUR ↔ USD (Euro ↔ Dólar)
5. MXN ↔ USD (Peso Mexicano ↔ Dólar)
6. BRL ↔ USD (Real Brasileño ↔ Dólar)

## Requisitos
- Java JDK 11+
- Maven 3.8+
- API Key de ExchangeRate-API

## Instalación
1. Clonar repositorio:
git clone https://github.com/tu-usuario/conversor-monedas.git
cd conversor-monedas

2. Configurar API Key:
cp src/main/resources/config.example.properties src/main/resources/config.properties
# Editar el archivo y añadir tu API Key

3. Compilar y ejecutar:
mvn clean package
java -jar target/conversor-monedas-1.0.0.jar

## Ejemplo de Uso
*** CONVERSOR DE MONEDAS ***
1. USD → MXN
2. MXN → USD
3. USD → EUR
Seleccione opción: 1
Ingrese cantidad: 100
100.00 USD = 1,750.00 MXN

## Estructura del Proyecto
conversor-monedas/
├── src/
│   ├── main/
│   │   ├── java/ConversorMonedas.java
│   │   └── resources/config.properties
├── pom.xml
└── README.md

## Mejoras Implementadas
- Protección de API Key
- Manejo profesional de errores
- Validación de entradas
- Código más mantenible

## Roadmap
- Más pares de conversión
- Historial de operaciones
- Interfaz gráfica

