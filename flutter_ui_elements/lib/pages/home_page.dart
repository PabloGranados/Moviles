import 'package:flutter/material.dart';
import 'textfields_page.dart';
import 'buttons_page.dart';
import 'selection_page.dart';
import 'lists_page.dart';
import 'info_page.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('📱 UI Elements Demo'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        centerTitle: true,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            // Título principal
            const Text(
              '📱 UI Elements Demo',
              style: TextStyle(
                fontSize: 28,
                fontWeight: FontWeight.bold,
              ),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 16),
            
            // Descripción
            const Text(
              '💡 Explora diferentes elementos de interfaz de usuario de Flutter. Cada sección demuestra componentes específicos con ejemplos interactivos.',
              style: TextStyle(fontSize: 16),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 32),
            
            // Botones de navegación
            _buildNavigationButton(
              context: context,
              icon: Icons.text_fields,
              title: '📝 Campos de Texto',
              subtitle: '(TextFields)',
              onTap: () => _navigateToPage(context, const TextFieldsPage()),
            ),
            const SizedBox(height: 16),
            
            _buildNavigationButton(
              context: context,
              icon: Icons.smart_button,
              title: '🔘 Botones Interactivos',
              subtitle: '(Buttons, IconButtons)',
              onTap: () => _navigateToPage(context, const ButtonsPage()),
            ),
            const SizedBox(height: 16),
            
            _buildNavigationButton(
              context: context,
              icon: Icons.check_box,
              title: '☑️ Elementos de Selección',
              subtitle: '(CheckBox, Radio, Switch)',
              onTap: () => _navigateToPage(context, const SelectionPage()),
            ),
            const SizedBox(height: 16),
            
            _buildNavigationButton(
              context: context,
              icon: Icons.list,
              title: '📋 Listas Interactivas',
              subtitle: '(ListView, ListTile)',
              onTap: () => _navigateToPage(context, const ListsPage()),
            ),
            const SizedBox(height: 16),
            
            _buildNavigationButton(
              context: context,
              icon: Icons.info,
              title: 'ℹ️ Elementos de Información',
              subtitle: '(Text, Image, Progress)',
              onTap: () => _navigateToPage(context, const InfoPage()),
            ),
            const SizedBox(height: 24),
            
            // Footer
            const Text(
              '✨ Toca cualquier botón para explorar ese elemento de UI',
              style: TextStyle(
                fontSize: 14,
                fontStyle: FontStyle.italic,
                color: Colors.grey,
              ),
              textAlign: TextAlign.center,
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildNavigationButton({
    required BuildContext context,
    required IconData icon,
    required String title,
    required String subtitle,
    required VoidCallback onTap,
  }) {
    return SizedBox(
      width: double.infinity,
      height: 80,
      child: ElevatedButton(
        onPressed: onTap,
        style: ElevatedButton.styleFrom(
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 16),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
        ),
        child: Row(
          children: [
            Icon(icon, size: 32),
            const SizedBox(width: 16),
            Expanded(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    title,
                    style: const TextStyle(
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  Text(
                    subtitle,
                    style: const TextStyle(
                      fontSize: 14,
                      color: Colors.grey,
                    ),
                  ),
                ],
              ),
            ),
            const Icon(Icons.arrow_forward_ios),
          ],
        ),
      ),
    );
  }

  void _navigateToPage(BuildContext context, Widget page) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => page),
    );
  }
}