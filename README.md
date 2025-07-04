# 🏨 Sistema de Reservas de Hotel - Proyecto Académico

## 📋 Descripción del Proyecto

Este proyecto implementa un **Sistema de Reservas para un Hotel** desarrollado en Java, aplicando conceptos avanzados de **Programación Orientada a Objetos (POO)** y **patrones de diseño**. Es una aplicación de consola que simula la gestión completa de reservas hoteleras.

## 🎯 Objetivos Académicos

- ✅ Implementar **patrones de diseño** fundamentales
- ✅ Aplicar **principios de POO** (encapsulamiento, herencia, polimorfismo, abstracción)
- ✅ Desarrollar **manejo de excepciones** personalizado
- ✅ Crear una **interfaz de usuario** intuitiva
- ✅ Demostrar **buenas prácticas** de programación

## 🏗️ Arquitectura del Proyecto

### 📁 Estructura de Carpetas

```
src/
├── interfaces/          # Interfaces para patrones Observer/Subject
│   ├── Observer.java
│   └── Subject.java
├── models/              # Entidades del dominio
│   ├── Cliente.java
│   ├── Habitacion.java
│   ├── Reserva.java
│   ├── TipoHabitacion.java
│   ├── TipoServicio.java
│   └── EstadoReserva.java
├── services/            # Servicios y patrón Decorator
│   ├── Servicio.java
│   ├── ServicioBasico.java
│   ├── ServicioDecorador.java
│   ├── ServicioSpa.java
│   └── ServicioRestaurante.java
├── factories/           # Patrón Factory
│   ├── HabitacionFactory.java
│   └── ServicioFactory.java
├── exceptions/          # Excepciones personalizadas
│   ├── HotelException.java
│   ├── ClienteDuplicadoException.java
│   └── HabitacionNoDisponibleException.java
├── system/              # Sistema principal (Singleton)
│   └── SistemaReservas.java
├── ui/                  # Interfaz de usuario
│   └── InterfazConsola.java
└── Main.java           # Punto de entrada de la aplicación
```

## 🎨 Patrones de Diseño Implementados

### 1. 🔒 **Singleton Pattern**
- **Clase**: `SistemaReservas`
- **Propósito**: Garantizar una única instancia del sistema de reservas
- **Implementación**: Constructor privado + método `getInstance()`

### 2. 🏭 **Factory Pattern**
- **Clases**: `HabitacionFactory`, `ServicioFactory`
- **Propósito**: Crear objetos (habitaciones y servicios) de manera centralizada
- **Beneficio**: Facilita la creación y configuración de objetos complejos

### 3. 🎁 **Decorator Pattern**
- **Clases**: `ServicioDecorador`, `ServicioSpa`, `ServicioRestaurante`
- **Propósito**: Agregar funcionalidades a servicios base sin modificar su estructura
- **Ejemplo**: Servicio básico + Spa + Restaurante

### 4. 👁️ **Observer Pattern**
- **Interfaces**: `Observer`, `Subject`
- **Implementación**: `Cliente` observa cambios en `Reserva`
- **Propósito**: Notificar automáticamente a clientes sobre cambios en sus reservas

## 🧱 Principios de POO Aplicados

### 🔐 **Encapsulamiento**
- Atributos privados en todas las clases
- Acceso controlado mediante getters/setters
- Validación de datos en métodos públicos

### 🌳 **Herencia**
- Jerarquía de servicios: `Servicio` → `ServicioBasico`, `ServicioDecorador`
- Jerarquía de excepciones: `HotelException` → excepciones específicas

### 🎭 **Polimorfismo**
- Métodos abstractos en `Servicio`
- Implementación de interfaces `Observer` y `Subject`
- Uso de tipos base para referencias polimórficas

### 🎨 **Abstracción**
- Interfaces `Observer` y `Subject`
- Clase abstracta `Servicio`
- Ocultación de detalles de implementación

## ⚠️ Manejo de Excepciones

### Jerarquía de Excepciones
```
Exception
└── HotelException (base)
    ├── ClienteDuplicadoException
    └── HabitacionNoDisponibleException
```

### Casos de Uso
- **ClienteDuplicadoException**: Cliente con DNI ya registrado
- **HabitacionNoDisponibleException**: Habitación ocupada o no existe

## 🚀 Instalación y Ejecución

### Prerrequisitos
- **Java 21** o superior
- Terminal con soporte para bash

### Pasos para Ejecutar

1. **Clonar o descargar el proyecto**
   ```bash
   git clone [repositorio]
   cd "aplicacion-usando POO-patrones"
   ```

2. **Compilar el proyecto**
   ```bash
   chmod +x compilar_todo.sh
   ./compilar_todo.sh
   ```

3. **Ejecutar la aplicación**
   ```bash
   chmod +x ejecutar.sh
   ./ejecutar.sh
   ```

### Compilación Manual (alternativa)
```bash
# Compilar todos los archivos juntos
javac -cp src $(find src -name "*.java")

# Ejecutar
cd src && java Main
```

## 🎮 Funcionalidades del Sistema

### 👤 **Gestión de Clientes**
- ✅ Registrar nuevos clientes
- ✅ Validar DNI único
- ✅ Notificaciones automáticas

### 🏠 **Gestión de Habitaciones**
- ✅ 4 tipos: Simple, Doble, Suite, Presidencial
- ✅ 26 habitaciones pre-configuradas
- ✅ Control de disponibilidad

### 📅 **Gestión de Reservas**
- ✅ Crear reservas con fechas
- ✅ Confirmar/cancelar reservas
- ✅ Cálculo automático de precios
- ✅ Estados: Pendiente, Confirmada, Cancelada, Completada

### 🛎️ **Gestión de Servicios**
- ✅ Servicios básicos: Desayuno, WiFi, Estacionamiento, Gimnasio
- ✅ Servicios premium: Spa (+$80), Restaurante (+$50)
- ✅ Combinación flexible usando Decorator

### 📊 **Reportes y Estadísticas**
- ✅ Habitaciones disponibles
- ✅ Reservas activas
- ✅ Ingresos totales
- ✅ Consultas por cliente

## 🎯 Ejemplo de Uso

```
1. Registrar cliente: Juan Pérez, DNI: 12345678
2. Consultar habitaciones disponibles
3. Crear reserva: Habitación 203, 23/02/2025 - 29/08/2025
4. Agregar servicios: Desayuno + Spa + Restaurante
5. Confirmar reserva
6. Ver estadísticas del hotel
```

## 🧪 Casos de Prueba Recomendados

### ✅ **Funcionalidad Básica**
1. Registrar cliente nuevo
2. Intentar registrar cliente duplicado (excepción)
3. Crear reserva válida
4. Intentar reservar habitación ocupada (excepción)
5. Agregar servicios a reserva
6. Confirmar y cancelar reservas

### ✅ **Patrones de Diseño**
1. Verificar Singleton (una sola instancia)
2. Probar Factory (diferentes tipos de habitaciones)
3. Probar Decorator (servicios combinados)
4. Verificar Observer (notificaciones automáticas)

## 📚 Criterios de Evaluación Cumplidos

| Criterio | Peso | Implementación |
|----------|------|----------------|
| **POO y Patrones** | 50% | ✅ 4 patrones + principios POO completos |
| **Funcionalidad** | 25% | ✅ Sistema completo con todas las funciones |
| **Interfaz Usuario** | 15% | ✅ Menú interactivo, validaciones, UX |
| **Documentación** | 10% | ✅ README completo + comentarios en código |

## 👨‍💻 Tecnologías Utilizadas

- **Lenguaje**: Java 21
- **Paradigma**: Programación Orientada a Objetos
- **Patrones**: Singleton, Factory, Decorator, Observer
- **Interfaz**: Consola/Terminal
- **Manejo de fechas**: java.time API

## 🎖️ Características Destacadas

- 🏗️ **Arquitectura limpia** con separación de responsabilidades
- 🎨 **Código bien documentado** con JavaDoc
- 🔧 **Manejo robusto de errores** con excepciones personalizadas
- 🎮 **Interfaz intuitiva** con emojis y navegación clara
- 📊 **Funcionalidades completas** de un sistema real
- ⚡ **Scripts de compilación** automatizados

## 🔧 Archivos de Construcción

El proyecto incluye scripts automatizados para facilitar la compilación y ejecución:

- `compilar_todo.sh`: Compila todos los archivos Java juntos
- `ejecutar.sh`: Ejecuta la aplicación
- `compilar.sh`: Compilación por fases (alternativa)

## 📂 Organización del Código

El proyecto está organizado en carpetas temáticas para facilitar la comprensión:

- **interfaces/**: Contratos para patrones Observer/Subject
- **models/**: Entidades del dominio (Cliente, Habitación, Reserva, etc.)
- **services/**: Lógica de servicios con patrón Decorator
- **factories/**: Creación de objetos con patrón Factory
- **exceptions/**: Excepciones personalizadas del sistema
- **system/**: Sistema principal con patrón Singleton
- **ui/**: Interfaz de usuario de consola

## 🤝 Contribuciones

Este es un proyecto académico demostrativo. Las contribuciones son bienvenidas para:
- 🐛 Corrección de bugs
- ✨ Nuevas funcionalidades
- 📖 Mejoras en documentación
- 🧪 Casos de prueba adicionales

---

## 📄 Conclusión

Este proyecto demuestra la aplicación práctica de conceptos fundamentales de POO y patrones de diseño en un contexto real. La implementación es funcional, bien estructurada y cumple con todos los requisitos académicos establecidos.

**¡Sistema de Reservas de Hotel - Demostrando la potencia de Java y POO! 🚀** 