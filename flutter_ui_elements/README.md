# 🦋 UI Elements - Flutter Version
## Granados Martínez Pablo Daniel

Versión **Flutter multiplataforma** equivalente a la app Android con **1 App + 5 Pages**.

## 📸 Screenshots

### HomePage
<p align="center">
<img src="screenshots/flutter_home_page.png" width="350" alt="Flutter HomePage">
</p>

### Pages Implementadas
<p align="center">
<img src="screenshots/flutter_textfields.png" width="300" alt="TextFields">
<img src="screenshots/flutter_buttons.png" width="300" alt="Buttons">
</p>
<p align="center">
<img src="screenshots/flutter_selection.png" width="300" alt="Selection">
<img src="screenshots/flutter_lists.png" width="300" alt="Lists">
<img src="screenshots/flutter_info.png" width="300" alt="Info">
</p>

## ️ Estructura
```
HomePage (navegación principal)
├── TextFieldsPage - TextField widgets
├── ButtonsPage - Button widgets  
├── SelectionPage - Checkbox, Radio, Dropdown
├── ListsPage - ListView dinámico CRUD
└── InfoPage - Text, Progress, Animaciones
```

## 🛠️ Tecnologías
**Flutter 3.5+ | Dart | Material Design 3 | Navigator.push**

## � Requisitos
- Flutter SDK 3.5+
- Dart 3.0+
- Android Studio / VS Code
- Dispositivo/Emulador Android/iOS

## �🚀 Instalación
```bash
git clone https://github.com/PabloGranados/Moviles.git
cd Moviles/flutter_ui_elements
flutter pub get
flutter run
```

## ✨ Funcionalidades

#### 📝 **TextFields Page**
- TextField normal, email, password, numérico, multilinea
- Controladores y teclados específicos por tipo

#### 🔘 **Buttons Page**
- ElevatedButton, OutlinedButton, TextButton, IconButton, FAB
- Contador persistente con SnackBar feedback

#### ☑️ **Selection Page** 
- Checkbox, Radio, Switch para configuraciones
- DropdownButton con países y Slider interactivo

#### 📋 **Lists Page**
- ListView.builder con gestión de tareas CRUD
- Cards, ListTile y Dialog de confirmación

#### ℹ️ **Info Page**
- Text widgets con estilos, íconos interactivos
- Progress indicators, Timer y AnimationController

## 🆚 vs Android Version
| Flutter | Android Kotlin |
|---------|-------------|
| 1 App + 5 Pages | 1 MainActivity + 5 Activities + 5 Fragments |
| Navigator.push | Intent navigation |
| Hot Reload ⚡ | Rebuild completo |
| Multiplataforma 🌍 | Solo Android |