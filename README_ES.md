# Aplicación Android – Sistema de Votación Método Borda

## Introducción del proyecto
Aplicación Android que implementa un sistema de votación basado en el **método de Borda**, junto con un sistema adicional de puntuación de **0 a 100**.

El método Borda asigna puntos según la posición:
- Última opción → 0 puntos
- Siguiente → 1 punto
- Puntuación progresiva hasta la primera opción
- La opción con mayor puntuación gana

La introducción de votos se realiza mediante barras deslizadoras interactivas.

Proyecto desarrollado como trabajo individual durante una estancia Erasmus en la **Frankfurt University of Applied Sciences** para la asignatura **Mobile Devices**.

---

## Funcionalidades
- Gestión de votaciones
- Sistema de votación método Borda
- Sistema alternativo 0–100
- Recuento de votos
- Seguimiento de participantes
- Actualización en tiempo real
- Generación de ranking final
- Reinicio de votaciones
- Cancelación de votaciones
- Entrada de votos mediante sliders

---

## Stack Tecnológico
- Lenguaje: Kotlin
- Plataforma: Android
- Interfaz: XML + ConstraintLayout
- Sistema de compilación: Gradle (KTS)
- Componentes Material Design
- Arquitectura: Multi-Activity

### Configuración Android
- Compile SDK: 34 (Android 14)
- Target SDK: 34 (Android 14)
- Min SDK: 28 (Android 9 Pie)

### Estructura del Proyecto (app/src/main/java/de/fra_uas/fb2/mobiledevices/bordasvotingmethod)

#### `MainActivity.kt`
Actúa como el punto de entrada y la pantalla de configuración de la aplicación.

**Las responsabilidades incluyen:**
* **Validación de entradas:** Definir el número de opciones de votación (validación de rango 2–10) y gestionar los nombres de las opciones.
* **Gestión de estado:** Restablecer los datos de votación almacenados cuando cambia la configuración y gestionar el estado de la aplicación utilizando `SharedPreferences`.
* **Resultados y Navegación:** Mostrar los resultados/recuentos de la votación e iniciar la interfaz de votación (`SecondActivity`).
* **Acciones Globales:** Proporcionar una funcionalidad de reinicio global.

#### `SecondActivity.kt`
Implementa la interfaz de votación y la lógica de cálculo Borda.

**Las responsabilidades incluyen:**
* **Interfaz Dinámica:** Mostrar dinámicamente las opciones de votación basándose en la configuración del usuario.
* **Recolección de Votos:** Recolectar votos utilizando deslizadores `SeekBar`.
* **Lógica Borda:** Convertir los valores de los deslizadores en clasificaciones Borda y actualizar los puntajes acumulados.
* **Validación:** Detectar empates en las clasificaciones y prevenir envíos inválidos.
* **Persistencia:** Rastrear el número total de sesiones de votación y persistir los resultados utilizando `SharedPreferences`.

#### `Resources:`
- Layouts → `app/src/main/res/layout`
- Drawables → `app/src/main/res/drawable`
- Values → `app/src/main/res/values`
- Manifest → `app/src/main/AndroidManifest.xml`

---

## Ejecución

### Requisitos
- Android Studio
- SDK Android 34
- Emulador o dispositivo físico

### Emulador
1. Abrir en Android Studio
2. Crear dispositivo virtual Pixel 5
3. Ejecutar

### Dispositivo físico
1. Activar modo desarrollador
2. Activar depuración USB
3. Conectar el dispositivo
4. Ejecutar desde Android Studio

---

## Demo
A continuación podrá ver una demostración de la aplicación, accediendo al enlace. [Borda Voting App Demo](https://drive.google.com/file/d/1H22jtLJsIGjqh_J1aedVEx13OXwt0bcI/view?usp=sharing)
