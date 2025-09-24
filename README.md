# 📱 UI Elements - App Android
## Granados Martínez Pablo Daniel

Aplicación Android que demuestra **5 Activities + 5 Fragments** con diferentes elementos de interfaz de usuario.

## 📸 Capturas de Pantalla

<div align="center">

### 🏠 Pantalla Principal
<img src="screenshots/main_activity.jpg" width="400" alt="MainActivity - Pantalla Principal">

---

### 📱 Elementos de UI Implementados

</div>

#### 📝 **TextFields - Campos de Texto**
<p align="center">
<img src="screenshots/textfields_fragment.jpg" width="350" alt="TextFields Fragment">
</p>

#### � **Botones - Elementos Interactivos**  
<p align="center">
<img src="screenshots/botones_fragment.jpg" width="350" alt="Botones Fragment">
</p>

#### ☑️ **Selección - Checkboxes, Radio & Switches**
<p align="center">
<img src="screenshots/seleccion_fragment.jpg" width="350" alt="Selección Fragment - Parte 1">
<br><br>
<img src="screenshots/seleccion_fragment_02.jpg" width="350" alt="Selección Fragment - Parte 2">
</p>

#### 📋 **Listas**
<p align="center">
<img src="screenshots/listas_fragment.jpg" width="350" alt="Listas Fragment">
</p>

#### ℹ️ **Información - TextViews & Widgets**
<p align="center">
<img src="screenshots/informacion_fragment.jpg" width="350" alt="Información Fragment">
</p>

---

## ️ Estructura

```
MainActivity (menú principal)
├── TextFieldsActivity → TextFieldsFragment
├── BotonesActivity → BotonesFragment  
├── SeleccionActivity → SeleccionFragment
├── ListasActivity → ListasFragment
└── InformacionActivity → InformacionFragment
```

### Funcionalidades:

#### 📝 **TextFields Fragment**
- EditText normal, email, password, numérico y multilinea
- Validación de formato y botón demo interactivo

#### 🔘 **Botones Fragment** 
- Button normal, con ícono, outlined, text button, image buttons
- Contador de interacciones y feedback con Toast

#### ☑️ **Selección Fragment**
- CheckBoxes (selección múltiple), RadioButtons (selección única)
- Switches para configuraciones, Spinner con países

#### 📋 **Listas Fragment**
- RecyclerView con lista de tareas completa
- Agregar, eliminar y marcar como completadas

#### ℹ️ **Información Fragment**
- TextViews con estilos, ImageViews interactivos
- ProgressBars animados e información en tiempo real

## 🛠️ Tecnologías
- **Kotlin** | API 24+ | Material Design | ViewBinding | RecyclerView

## � Requisitos
- Android Studio Arctic Fox+
- JDK 8+
- SDK Android API 24+ (Android 7.0)
- Gradle 8.0+

## �🚀 Instalación
```bash
git clone https://github.com/PabloGranados/Moviles.git
cd Moviles
```
1. Abrir en Android Studio  
2. Sync Gradle dependencies
3. Conectar dispositivo/emulador
4. Run (Shift+F10)

## 📖 Uso
Ver `INSTRUCCIONES_USO.txt` para guía detallada de cada funcionalidad.