## Granados Martínez Pablo Daniel


# 📱 UI Elements Demo - App Android Educativa

## 📋 Descripción de la Aplicación

Esta aplicación Android demuestra el uso de **Activities y Fragments** para explicar diferentes elementos de interfaz de usuario de manera interactiva y educativa. Cada fragment muestra ejemplos prácticos de componentes UI fundamentales en Android.

## 🎯 Objetivo

Crear una herramienta educativa que ayude a entender y experimentar con los principales elementos de interfaz de usuario de Android, organizados en fragments navegables mediante pestañas.

## 🏗️ Estructura de la Aplicación

### MainActivity
- **Actividad principal** que gestiona la navegación entre fragments
- Utiliza **ViewPager2** con **TabLayout** para navegación por pestañas
- Implementa **FragmentStateAdapter** para gestionar los 5 fragments

### 📚 Fragments Implementados

#### 1. 📝 TextFieldsFragment - Campos de Texto
- **EditText normal** para entrada de texto
- **EditText email** con validación de formato
- **EditText password** con toggle de visibilidad
- **EditText numérico** para números
- **EditText multilinea** para textos largos
- **Demostración interactiva**: Botón que muestra toda la información ingresada

#### 2. 🔘 BotonesFragment - Botones Interactivos
- **Button normal** con acción básica
- **Button con ícono** y texto
- **Material Button Outlined** estilo contorno
- **Text Button** estilo minimalista
- **ImageButtons** con íconos diferentes
- **ToggleButton** con estados on/off
- **Contador de interacciones** que muestra total de clics
- **Demostración interactiva**: Cada botón muestra Toast con mensaje específico

#### 3. ☑️ SeleccionFragment - Elementos de Selección
- **CheckBoxes** para selección múltiple (lenguajes de programación)
- **RadioButtons** para selección única (nivel de experiencia)
- **Switches** para activar/desactivar configuraciones
- **Spinner** para lista desplegable (países)
- **Demostración interactiva**: Botón que muestra resumen de todas las selecciones

#### 4. 📋 ListasFragment - Listas Interactivas
- **RecyclerView** con adaptador personalizado
- **Lista de tareas** con funcionalidad completa
- **Agregar elementos** desde campo de texto
- **Marcar como completado** con checkbox
- **Eliminar elementos** individualmente
- **Contador dinámico** de elementos totales y completados
- **Demostración interactiva**: Sistema completo de gestión de tareas

#### 5. ℹ️ InformacionFragment - Elementos de Información
- **TextViews** con diferentes estilos (normal, negrita, cursiva)
- **ImageViews** con íconos interactivos
- **ProgressBar circular** (indeterminado)
- **ProgressBar horizontal** (determinado) con animación
- **Información dinámica**: fecha/hora actual, contador de visitas
- **Demostración interactiva**: Progreso animado y actualización de datos

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Kotlin
- **SDK**: Android API 24+ (Android 7.0)
- **UI Framework**: Traditional Android Views (no Compose)
- **Architecture**: Activity + Fragments
- **Navigation**: ViewPager2 + TabLayout
- **Material Design**: Material Design Components
- **View Binding**: Habilitado para binding de vistas

## 📦 Dependencias Principales

```gradle
implementation("androidx.fragment:fragment-ktx:1.6.2")
implementation("androidx.viewpager2:viewpager2:1.0.0")
implementation("com.google.android.material:material:1.10.0")
implementation("androidx.recyclerview:recyclerview:1.3.2")
implementation("androidx.cardview:cardview:1.0.0")
```

## 🚀 Instrucciones de Uso

### Para Usuarios:
1. **Navegación**: Usa las pestañas inferiores para cambiar entre diferentes tipos de elementos UI
2. **Interacción**: Cada fragment tiene elementos interactivos - ¡pruébalos todos!
3. **Aprendizaje**: Lee las explicaciones y experimenta con cada componente
4. **Funcionalidad**: Algunos fragments mantienen estado (como el contador de listas)

### Para Desarrolladores:
1. **Clonar el repositorio**: `git clone [URL_DEL_REPO]`
2. **Abrir en Android Studio**: Importar el proyecto
3. **Sync Project**: Sincronizar dependencias Gradle
4. **Run**: Ejecutar en dispositivo o emulador (API 24+)

## 📱 Screenshots de Funcionamiento

### Fragment de Campos de Texto
- Muestra diferentes tipos de EditText
- Demostración de entrada de datos
- Validación y formateo de texto

### Fragment de Botones
- Variedad de estilos de botones
- Contador de interacciones
- Feedback visual mediante Toast

### Fragment de Selección
- CheckBoxes para selección múltiple
- RadioButtons para selección única
- Switches y Spinners funcionales

### Fragment de Listas
- RecyclerView con gestión completa de tareas
- Operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
- UI responsive y animaciones

### Fragment de Información
- Elementos de solo lectura y informativos
- Animaciones de progreso
- Datos dinámicos actualizables

## 🎓 Conceptos Educativos Cubiertos

### Activities y Fragments
- Ciclo de vida de Activities
- Gestión de Fragments
- Comunicación entre Activity y Fragments

### Layouts y Views
- LinearLayout y ConstraintLayout
- Material Design Components
- View Binding para acceso a vistas

### Interacción de Usuario
- Event Listeners
- Estado de componentes
- Feedback visual (Toast, animaciones)

### Gestión de Datos
- Adaptadores para RecyclerView
- Manejo de estado local
- Persistencia temporal en memoria

## 🔧 Características Técnicas

- **Responsive Design**: Adaptable a diferentes tamaños de pantalla
- **Material Design**: Siguiendo las guías de diseño de Google
- **Performance**: Uso eficiente de memoria con ViewBinding
- **Accesibilidad**: Content descriptions para elementos visuales
- **Internacionalización**: Strings externalizados para fácil traducción