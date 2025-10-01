import 'package:flutter/material.dart';

class ButtonsPage extends StatefulWidget {
  const ButtonsPage({super.key});

  @override
  State<ButtonsPage> createState() => _ButtonsPageState();
}

class _ButtonsPageState extends State<ButtonsPage> {
  int _contadorClics = 0;
  bool _corazonActivo = false;
  bool _toggleActivo = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('🔘 Botones Interactivos'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Título
            const Text(
              '🔘 Botones Interactivos',
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 16),
            
            // Explicación
            const Text(
              '💡 Los botones permiten al usuario realizar acciones específicas. Son elementos interactivos fundamentales en cualquier interfaz.',
              style: TextStyle(fontSize: 16),
            ),
            const SizedBox(height: 24),
            
            // Botón Normal
            const Text(
              '🔵 Botón Normal (ElevatedButton):',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                onPressed: () => _incrementarContador('¡Botón normal presionado! 👍'),
                child: const Text('Presiona aquí'),
              ),
            ),
            const SizedBox(height: 16),
            
            // Botón con Ícono
            const Text(
              '⭐ Botón con Ícono:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton.icon(
                onPressed: () => _incrementarContador('¡Me gusta presionado! ⭐'),
                icon: const Icon(Icons.star),
                label: const Text('Me Gusta'),
              ),
            ),
            const SizedBox(height: 16),
            
            // Botón Outlined
            const Text(
              '📝 Botón Outlined:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            SizedBox(
              width: double.infinity,
              child: OutlinedButton(
                onPressed: () => _incrementarContador('Botón outlined activado 📝'),
                child: const Text('Botón Outlined'),
              ),
            ),
            const SizedBox(height: 16),
            
            // Botón de Texto
            const Text(
              '📄 Botón de Texto:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            SizedBox(
              width: double.infinity,
              child: TextButton(
                onPressed: () => _incrementarContador('Botón de texto activado 📄'),
                child: const Text('Botón de Texto'),
              ),
            ),
            const SizedBox(height: 16),
            
            // IconButtons
            const Text(
              '🖼️ Botones de Ícono:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                Column(
                  children: [
                    IconButton(
                      onPressed: () {
                        setState(() {
                          _corazonActivo = !_corazonActivo;
                          _contadorClics++;
                        });
                        _mostrarSnackBar(_corazonActivo ? '¡Favorito agregado! ❤️' : 'Favorito removido 💔');
                      },
                      icon: Icon(
                        _corazonActivo ? Icons.favorite : Icons.favorite_border,
                        size: 32,
                        color: _corazonActivo ? Colors.red : null,
                      ),
                    ),
                    const Text('Favorito'),
                  ],
                ),
                Column(
                  children: [
                    IconButton(
                      onPressed: () => _incrementarContador('¡Compartiendo contenido! 📤'),
                      icon: const Icon(Icons.share, size: 32),
                    ),
                    const Text('Compartir'),
                  ],
                ),
                Column(
                  children: [
                    IconButton(
                      onPressed: () => _incrementarContador('Mostrando información adicional ℹ️'),
                      icon: const Icon(Icons.info, size: 32),
                    ),
                    const Text('Info'),
                  ],
                ),
              ],
            ),
            const SizedBox(height: 16),
            
            // Switch Toggle
            const Text(
              '🔄 Switch Toggle:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(
                  _toggleActivo ? 'Función activada 🟢' : 'Función desactivada 🔴',
                  style: const TextStyle(fontSize: 16),
                ),
                Switch(
                  value: _toggleActivo,
                  onChanged: (value) {
                    setState(() {
                      _toggleActivo = value;
                      _contadorClics++;
                    });
                    _mostrarSnackBar(value ? '¡Función activada! 🟢' : 'Función desactivada 🔴');
                  },
                ),
              ],
            ),
            const SizedBox(height: 16),
            
            // Floating Action Button
            const Text(
              '🎯 Floating Action Button:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Center(
              child: FloatingActionButton.extended(
                onPressed: () => _incrementarContador('¡FAB presionado! 🎯'),
                icon: const Icon(Icons.add),
                label: const Text('Acción Flotante'),
              ),
            ),
            const SizedBox(height: 16),
            
            // Contador de clics
            const Text(
              '📊 Contador de Interacciones:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(12),
              decoration: BoxDecoration(
                color: Theme.of(context).colorScheme.surfaceVariant,
                borderRadius: BorderRadius.circular(8),
              ),
              child: Text(
                'Total de clics: $_contadorClics',
                style: const TextStyle(fontSize: 16),
                textAlign: TextAlign.center,
              ),
            ),
            const SizedBox(height: 16),
            
            // Botón Reset
            SizedBox(
              width: double.infinity,
              child: OutlinedButton.icon(
                onPressed: _resetContador,
                icon: const Icon(Icons.refresh),
                label: const Text('🔄 Reiniciar Contador'),
              ),
            ),
          ],
        ),
      ),
    );
  }

  void _incrementarContador(String mensaje) {
    setState(() {
      _contadorClics++;
    });
    _mostrarSnackBar(mensaje);
  }

  void _resetContador() {
    setState(() {
      _contadorClics = 0;
      _corazonActivo = false;
      _toggleActivo = false;
    });
    _mostrarSnackBar('¡Contador reiniciado! 🔄');
  }

  void _mostrarSnackBar(String mensaje) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(mensaje),
        duration: const Duration(seconds: 1),
      ),
    );
  }
}