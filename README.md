## Granados Martínez Pablo Daniel

# 📱 UI Elements - App Android

## 📋 Descripción de la Aplicación

Esta aplicación Android demuestra el uso de **Activities y Fragments** para explicar diferentes elementos de interfaz de usuario de manera interactiva y educativa. Cada fragment muestra ejemplos prácticos de componentes UI fundamentales en Android.

## 📸 Capturas de Pantalla

### Pantalla Principal
<div align="center">
<img src="screenshots/main_activity.jpg" width="300" alt="MainActivity - Pantalla Principal">
</div>

### Elementos de UI
<div align="center">
<table>
<tr>
<td align="center">
<img src="screenshots/textfields_fragment.jpg" width="250" alt="TextFields Fragment">
<br><b>📝 TextFields</b>
</td>
<td align="center">
<img src="screenshots/botones_fragment.jpg" width="250" alt="Botones Fragment">
<br><b>🔘 Botones</b>
</td>
<td align="center">
<img src="screenshots/seleccion_fragment.jpg" width="250" alt="Selección Fragment">
<img src="screenshots/seleccion_fragment_02.jpg" width="250" alt="Selección Fragment 2">
<br><b>☑️ Selección</b>
</td>
</tr>
<tr>
<td align="center">
<img src="screenshots/listas_fragment.jpg" width="250" alt="Listas Fragment">
<br><b>📋 Listas</b>
</td>
<td align="center">
<img src="screenshots/informacion_fragment.jpg" width="250" alt="Información Fragment">
<br><b>ℹ️ Información</b>
</td>
</tr>
</table>
</div>

## 🎯 Objetivo

Crear una herramienta educativa que ayude a entender y experimentar con los principales elementos de interfaz de usuario de Android, organizados en fragments navegables mediante pestañas.

## 🏗️ Estructura de la Aplicación

### MainActivity
- **Actividad principal** con menú de navegación
- Pantalla de inicio con botones para acceder a cada elemento UI
- Navegación mediante **Intents** hacia Activities específicas

### 📚 Activities y Fragments Implementados

La aplicación cuenta con **5 Activities** y **5 Fragments** (1:1), cada Activity contiene un Fragment específico:

#### **TextFieldsActivity + TextFieldsFragment**
#### **BotonesActivity + BotonesFragment** 
#### **SeleccionActivity + SeleccionFragment**
#### **ListasActivity + ListasFragment**
#### **InformacionActivity + InformacionFragment**

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
- **Architecture**: Multiple Activities + Fragments (5:5)
- **Navigation**: Intent-based navigation between Activities
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
1. **Navegación**: Desde la pantalla principal, toca cualquier botón para acceder a un elemento UI específico
2. **Interacción**: Cada Activity/Fragment tiene elementos interactivos - ¡pruébalos todos!
3. **Aprendizaje**: Lee las explicaciones y experimenta con cada componente
4. **Regreso**: Usa el botón de "Atrás" o la flecha en la ActionBar para volver al menú principal
5. **Funcionalidad**: Cada Activity mantiene su propio estado independiente

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
- Ciclo de vida de Activities (5 Activities independientes)
- Gestión de Fragments (1 Fragment por Activity)
- Navegación entre Activities mediante Intents
- Comunicación Activity-Fragment

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

## 🚧 Dificultades Encontradas y Soluciones

### 1. **Gestión de Estado en Fragments**
**Dificultad**: Mantener el estado de los componentes UI cuando se navega entre fragments
**Solución**: Implementación de variables de instancia en cada fragment y uso correcto del ciclo de vida `onViewCreated()` y `onDestroyView()`

### 2. **Configuración del RecyclerView**
**Dificultad**: Implementar correctamente el adapter personalizado para la lista de tareas con funcionalidad completa
**Solución**: Creación de una inner class `TareasAdapter` que hereda de `RecyclerView.Adapter` y manejo adecuado de callbacks para eliminación de elementos

### 3. **Animaciones de ProgressBar**
**Dificultad**: Crear animaciones fluidas sin bloquear el hilo principal
**Solución**: Uso de `Handler` con `Looper.getMainLooper()` y `Runnable` para actualizaciones periódicas del progreso

### 4. **Gestión de Memory Leaks**
**Dificultad**: Evitar memory leaks al navegar entre fragments
**Solución**: Implementación correcta de `onDestroyView()` asignando `null` a todas las referencias de vistas y cancelando handlers activos

### 5. **Validación de Entrada de Usuario**
**Dificultad**: Validar diferentes tipos de input en los EditText
**Solución**: Implementación de listeners específicos (`EditorInfo.IME_ACTION_DONE`) y validaciones condicionales con feedback mediante Toast

## 🔍 Hallazgos Importantes

### **1. ViewPager2 vs ViewPager**
- **Hallazgo**: ViewPager2 ofrece mejor rendimiento y soporte para RecyclerView.Adapter
- **Beneficio**: Navegación más fluida entre fragments y mejor gestión de memoria

### **2. Material Design Components**
- **Hallazgo**: Los componentes Material ofrecen mejor UX out-of-the-box
- **Beneficio**: Menos código personalizado requerido para animaciones y estados

### **3. Fragment Lifecycle**
- **Hallazgo**: `onViewCreated()` es más confiable que `onCreateView()` para configurar listeners
- **Beneficio**: Garantiza que las vistas estén completamente inicializadas

### **4. Error Handling**
- **Hallazgo**: Try-catch blocks en métodos críticos mejoran la estabilidad
- **Beneficio**: La app no crashea ante errores inesperados y muestra feedback al usuario

### **5. Performance en RecyclerView**
- **Hallazgo**: `notifyItemChanged()` es más eficiente que `notifyDataSetChanged()`
- **Beneficio**: Animaciones más suaves y mejor rendimiento en listas grandes

## 📊 Métricas del Proyecto

- **Total de Activities**: 6 archivos (1 MainActivity + 5 específicas)
- **Total de Fragments**: 5 fragments funcionales
- **Total de Layouts XML**: 12 archivos (6 activities + 6 fragments)
- **Componentes UI Demostrados**: 15+ componentes diferentes
- **Líneas de código**: ~2000 líneas aproximadamente
- **SDK Mínimo**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

## 🎯 Objetivos Educativos Alcanzados

✅ **Comprensión de Activities y Fragments (5:5)**
✅ **Implementación de navegación entre Activities mediante Intents**
✅ **Uso práctico de componentes Material Design**
✅ **Gestión correcta del ciclo de vida de múltiples Activities**
✅ **Implementación de listas dinámicas con RecyclerView**
✅ **Manejo de eventos de usuario e interacciones**
✅ **Aplicación de buenas prácticas de desarrollo Android**
✅ **Arquitectura modular con separación de responsabilidades**

## 🏗️ **Nueva Arquitectura: 5 Activities + 5 Fragments**

### **Estructura Final Implementada:**

```
📱 MainActivity (Menú Principal)
├── 📝 TextFieldsActivity → TextFieldsFragment
├── 🔘 BotonesActivity → BotonesFragment  
├── ☑️ SeleccionActivity → SeleccionFragment
├── 📋 ListasActivity → ListasFragment
└── ℹ️ InformacionActivity → InformacionFragment
```

### **Beneficios de esta Arquitectura:**
- ✅ **Separación completa** de responsabilidades
- ✅ **Navegación clara** mediante botones en pantalla principal
- ✅ **Gestión independiente** del estado de cada pantalla
- ✅ **Escalabilidad** - fácil agregar nuevas funcionalidades
- ✅ **Mantenimiento** - cada Activity es independiente
- ✅ **Experiencia de usuario** - navegación intuitiva con botón "Atrás"

### **Navegación:**
1. **MainActivity**: Pantalla principal con 5 botones grandes
2. **Activities específicas**: Cada una con su Fragment correspondiente
3. **ActionBar**: Con botón de regreso automático al MainActivity
4. **Back Button**: Funcionalidad nativa de Android para volver