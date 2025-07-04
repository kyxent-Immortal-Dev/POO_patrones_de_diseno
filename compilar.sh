#!/bin/bash

echo "🏗️ Compilando Sistema de Reservas de Hotel..."

# Limpiar archivos .class anteriores
find src -name "*.class" -delete

# Compilar en orden de dependencias

# 1. Interfaces
echo "📦 Compilando interfaces..."
javac src/interfaces/*.java

# 2. Excepciones
echo "📦 Compilando excepciones..."
javac -cp src src/exceptions/*.java

# 3. Modelos (enums y clases básicas)
echo "📦 Compilando modelos..."
javac -cp src src/models/*.java

# 4. Servicios
echo "📦 Compilando servicios..."
javac -cp src src/services/*.java

# 5. Factories
echo "📦 Compilando factories..."
javac -cp src src/factories/*.java

# 6. Sistema principal
echo "📦 Compilando sistema..."
javac -cp src src/system/*.java

# 7. Interfaz de usuario
echo "📦 Compilando interfaz de usuario..."
javac -cp src src/ui/*.java

# 8. Main
echo "📦 Compilando Main..."
javac -cp src src/Main.java

echo "✅ Compilación completada!"
echo "🚀 Para ejecutar: ./ejecutar.sh" 