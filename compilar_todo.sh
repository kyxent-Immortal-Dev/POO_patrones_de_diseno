#!/bin/bash

echo "🏗️ Compilando Sistema de Reservas de Hotel (todos los archivos juntos)..."

# Limpiar archivos .class anteriores
find src -name "*.class" -delete

# Compilar todos los archivos Java juntos
echo "📦 Compilando todos los archivos Java..."
javac -cp src $(find src -name "*.java")

echo "✅ Compilación completada!"
echo "🚀 Para ejecutar: ./ejecutar.sh" 