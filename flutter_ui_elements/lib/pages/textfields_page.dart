import 'package:flutter/material.dart';

class TextFieldsPage extends StatefulWidget {
  const TextFieldsPage({super.key});

  @override
  State<TextFieldsPage> createState() => _TextFieldsPageState();
}

class _TextFieldsPageState extends State<TextFieldsPage> {
  final _nombreController = TextEditingController();
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  final _numeroController = TextEditingController();
  final _comentarioController = TextEditingController();
  
  String _resultado = '';
  bool _passwordVisible = false;

  @override
  void dispose() {
    _nombreController.dispose();
    _emailController.dispose();
    _passwordController.dispose();
    _numeroController.dispose();
    _comentarioController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('📝 Campos de Texto'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Título
            const Text(
              '📝 Campos de Texto (TextFields)',
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 16),
            
            // Explicación
            const Text(
              '💡 Los campos de texto permiten al usuario introducir y editar texto. Son fundamentales para formularios y entrada de datos.',
              style: TextStyle(fontSize: 16),
            ),
            const SizedBox(height: 24),
            
            // TextField Normal
            const Text(
              '🔤 Texto Normal:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            TextField(
              controller: _nombreController,
              decoration: const InputDecoration(
                labelText: 'Escribe tu nombre',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.person),
              ),
            ),
            const SizedBox(height: 16),
            
            // TextField Email
            const Text(
              '📧 Email:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            TextField(
              controller: _emailController,
              keyboardType: TextInputType.emailAddress,
              decoration: const InputDecoration(
                labelText: 'ejemplo@email.com',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.email),
              ),
            ),
            const SizedBox(height: 16),
            
            // TextField Password
            const Text(
              '🔒 Contraseña:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            TextField(
              controller: _passwordController,
              obscureText: !_passwordVisible,
              decoration: InputDecoration(
                labelText: 'Ingresa tu contraseña',
                border: const OutlineInputBorder(),
                prefixIcon: const Icon(Icons.lock),
                suffixIcon: IconButton(
                  icon: Icon(
                    _passwordVisible ? Icons.visibility : Icons.visibility_off,
                  ),
                  onPressed: () {
                    setState(() {
                      _passwordVisible = !_passwordVisible;
                    });
                  },
                ),
              ),
            ),
            const SizedBox(height: 16),
            
            // TextField Número
            const Text(
              '🔢 Número:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            TextField(
              controller: _numeroController,
              keyboardType: TextInputType.number,
              decoration: const InputDecoration(
                labelText: 'Ingresa tu edad',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.numbers),
              ),
            ),
            const SizedBox(height: 16),
            
            // TextField Multilinea
            const Text(
              '📝 Texto Largo (Multilinea):',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            TextField(
              controller: _comentarioController,
              maxLines: 4,
              decoration: const InputDecoration(
                labelText: 'Escribe un comentario largo...',
                border: OutlineInputBorder(),
                alignLabelWithHint: true,
              ),
            ),
            const SizedBox(height: 16),
            
            // Botón para mostrar info
            SizedBox(
              width: double.infinity,
              child: ElevatedButton.icon(
                onPressed: _mostrarInformacion,
                icon: const Icon(Icons.check),
                label: const Text('✅ Mostrar Información Ingresada'),
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

  void _mostrarInformacion() {
    final nombre = _nombreController.text.trim();
    final email = _emailController.text.trim();
    final password = _passwordController.text;
    final numero = _numeroController.text.trim();
    final comentario = _comentarioController.text.trim();

    final resultado = StringBuffer();
    resultado.writeln('📋 INFORMACIÓN INGRESADA:\n');
    
    if (nombre.isNotEmpty) {
      resultado.writeln('👤 Nombre: $nombre');
    }
    
    if (email.isNotEmpty) {
      resultado.writeln('📧 Email: $email');
    }
    
    if (password.isNotEmpty) {
      resultado.writeln('🔒 Contraseña: ${"*" * password.length} (${password.length} caracteres)');
    }
    
    if (numero.isNotEmpty) {
      resultado.writeln('🔢 Edad: $numero años');
    }
    
    if (comentario.isNotEmpty) {
      resultado.writeln('📝 Comentario: $comentario');
    }
    
    if (resultado.length == '📋 INFORMACIÓN INGRESADA:\n\n'.length) {
      resultado.write('⚠️ No se ha ingresado información en ningún campo.');
    }

    setState(() {
      _resultado = resultado.toString();
    });
  }
}