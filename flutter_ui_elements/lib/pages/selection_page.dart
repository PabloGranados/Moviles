import 'package:flutter/material.dart';

class SelectionPage extends StatefulWidget {
  const SelectionPage({super.key});

  @override
  State<SelectionPage> createState() => _SelectionPageState();
}

class _SelectionPageState extends State<SelectionPage> {
  // CheckBoxes
  final Map<String, bool> _lenguajes = {
    'Kotlin 🟢': false,
    'Dart 💙': false,
    'Java ☕': false,
    'Python 🐍': false,
    'JavaScript 💛': false,
  };

  // RadioButton
  String _nivelSeleccionado = '';

  // Switches
  bool _notificaciones = false;
  bool _modoOscuro = false;
  bool _sincronizacion = false;

  // Dropdown
  String? _paisSeleccionado;
  final List<String> _paises = [
    'Argentina 🇦🇷',
    'Brasil 🇧🇷',
    'Chile 🇨🇱',
    'Colombia 🇨🇴',
    'España 🇪🇸',
    'México 🇲🇽',
    'Perú 🇵🇪',
    'Uruguay 🇺🇾',
    'Venezuela 🇻🇪'
  ];

  String _resultado = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('☑️ Elementos de Selección'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Título
            const Text(
              '☑️ Elementos de Selección',
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 16),
            
            // Explicación
            const Text(
              '💡 Los elementos de selección permiten al usuario elegir entre múltiples opciones de manera intuitiva.',
              style: TextStyle(fontSize: 16),
            ),
            const SizedBox(height: 24),
            
            // CheckBoxes
            const Text(
              '💻 Lenguajes favoritos (selección múltiple):',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            ..._lenguajes.entries.map((entry) => CheckboxListTile(
              title: Text(entry.key),
              value: entry.value,
              onChanged: (bool? value) {
                setState(() {
                  _lenguajes[entry.key] = value ?? false;
                });
              },
            )),
            const SizedBox(height: 16),
            
            // RadioButtons
            const Text(
              '📊 Nivel de experiencia (selección única):',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Column(
              children: [
                RadioListTile<String>(
                  title: const Text('Principiante 🌱'),
                  value: 'Principiante 🌱',
                  groupValue: _nivelSeleccionado,
                  onChanged: (String? value) {
                    setState(() {
                      _nivelSeleccionado = value ?? '';
                    });
                  },
                ),
                RadioListTile<String>(
                  title: const Text('Intermedio 🌳'),
                  value: 'Intermedio 🌳',
                  groupValue: _nivelSeleccionado,
                  onChanged: (String? value) {
                    setState(() {
                      _nivelSeleccionado = value ?? '';
                    });
                  },
                ),
                RadioListTile<String>(
                  title: const Text('Avanzado 🌟'),
                  value: 'Avanzado 🌟',
                  groupValue: _nivelSeleccionado,
                  onChanged: (String? value) {
                    setState(() {
                      _nivelSeleccionado = value ?? '';
                    });
                  },
                ),
                RadioListTile<String>(
                  title: const Text('Experto 🚀'),
                  value: 'Experto 🚀',
                  groupValue: _nivelSeleccionado,
                  onChanged: (String? value) {
                    setState(() {
                      _nivelSeleccionado = value ?? '';
                    });
                  },
                ),
              ],
            ),
            const SizedBox(height: 16),
            
            // Switches
            const Text(
              '⚙️ Configuraciones:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            SwitchListTile(
              title: const Text('🔔 Notificaciones'),
              value: _notificaciones,
              onChanged: (bool value) {
                setState(() {
                  _notificaciones = value;
                });
              },
            ),
            SwitchListTile(
              title: const Text('🌙 Modo oscuro'),
              value: _modoOscuro,
              onChanged: (bool value) {
                setState(() {
                  _modoOscuro = value;
                });
              },
            ),
            SwitchListTile(
              title: const Text('🔄 Sincronización'),
              value: _sincronizacion,
              onChanged: (bool value) {
                setState(() {
                  _sincronizacion = value;
                });
              },
            ),
            const SizedBox(height: 16),
            
            // Dropdown
            const Text(
              '🌍 País:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            DropdownButtonFormField<String>(
              decoration: const InputDecoration(
                border: OutlineInputBorder(),
                labelText: 'Selecciona un país',
                prefixIcon: Icon(Icons.public),
              ),
              value: _paisSeleccionado,
              items: _paises.map((String pais) {
                return DropdownMenuItem<String>(
                  value: pais,
                  child: Text(pais),
                );
              }).toList(),
              onChanged: (String? newValue) {
                setState(() {
                  _paisSeleccionado = newValue;
                });
              },
            ),
            const SizedBox(height: 16),
            
            // Slider (bonus)
            const Text(
              '🎚️ Slider (0-100):',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Slider(
              value: 50,
              min: 0,
              max: 100,
              divisions: 10,
              label: '50',
              onChanged: (double value) {
                // Slider interactivo
              },
            ),
            const SizedBox(height: 16),
            
            // Botón para mostrar selecciones
            SizedBox(
              width: double.infinity,
              child: ElevatedButton.icon(
                onPressed: _mostrarSelecciones,
                icon: const Icon(Icons.check),
                label: const Text('✅ Mostrar Mis Selecciones'),
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(vertical: 16),
                ),
              ),
            ),
            const SizedBox(height: 16),
            
            // Resultado
            if (_resultado.isNotEmpty)
              Container(
                width: double.infinity,
                padding: const EdgeInsets.all(12),
                decoration: BoxDecoration(
                  color: Theme.of(context).colorScheme.surfaceVariant,
                  borderRadius: BorderRadius.circular(8),
                ),
                child: Text(
                  _resultado,
                  style: const TextStyle(fontSize: 14),
                ),
              ),
          ],
        ),
      ),
    );
  }

  void _mostrarSelecciones() {
    final resultado = StringBuffer();
    resultado.writeln('📋 TUS SELECCIONES:\n');

    // CheckBoxes
    final lenguajesSeleccionados = _lenguajes.entries
        .where((entry) => entry.value)
        .map((entry) => entry.key)
        .toList();

    if (lenguajesSeleccionados.isNotEmpty) {
      resultado.writeln('💻 Lenguajes favoritos:');
      for (String lenguaje in lenguajesSeleccionados) {
        resultado.writeln('   • $lenguaje');
      }
      resultado.writeln();
    } else {
      resultado.writeln('💻 Lenguajes: Ninguno seleccionado\n');
    }

    // RadioButton
    resultado.writeln('📊 Nivel de experiencia: ${_nivelSeleccionado.isEmpty ? "No seleccionado" : _nivelSeleccionado}\n');

    // Switches
    resultado.writeln('⚙️ Configuraciones:');
    resultado.writeln('   🔔 Notificaciones: ${_notificaciones ? "Activadas" : "Desactivadas"}');
    resultado.writeln('   🌙 Modo oscuro: ${_modoOscuro ? "Activado" : "Desactivado"}');
    resultado.writeln('   🔄 Sincronización: ${_sincronizacion ? "Activada" : "Desactivada"}\n');

    // Dropdown
    resultado.writeln('🌍 País: ${_paisSeleccionado ?? "No seleccionado"}');

    setState(() {
      _resultado = resultado.toString();
    });
  }
}