import 'package:flutter/material.dart';

class Task {
  final String id;
  String texto;
  bool completada;

  Task({
    required this.id,
    required this.texto,
    this.completada = false,
  });
}

class ListsPage extends StatefulWidget {
  const ListsPage({super.key});

  @override
  State<ListsPage> createState() => _ListsPageState();
}

class _ListsPageState extends State<ListsPage> {
  final _controller = TextEditingController();
  final List<Task> _tareas = [];

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('📋 Listas Interactivas'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Column(
        children: [
          // Header con título y explicación
          Container(
            width: double.infinity,
            padding: const EdgeInsets.all(16.0),
            child: Column(
              children: [
                const Text(
                  '📋 Listas Interactivas',
                  style: TextStyle(
                    fontSize: 24,
                    fontWeight: FontWeight.bold,
                  ),
                  textAlign: TextAlign.center,
                ),
                const SizedBox(height: 16),
                const Text(
                  '💡 Las listas permiten mostrar y gestionar colecciones de elementos de manera eficiente y organizada.',
                  style: TextStyle(fontSize: 16),
                  textAlign: TextAlign.center,
                ),
                const SizedBox(height: 16),
                
                // Input para agregar tareas
                Row(
                  children: [
                    Expanded(
                      child: TextField(
                        controller: _controller,
                        decoration: const InputDecoration(
                          labelText: 'Nueva tarea...',
                          border: OutlineInputBorder(),
                          prefixIcon: Icon(Icons.add_task),
                        ),
                        onSubmitted: (_) => _agregarTarea(),
                      ),
                    ),
                    const SizedBox(width: 8),
                    ElevatedButton.icon(
                      onPressed: _agregarTarea,
                      icon: const Icon(Icons.add),
                      label: const Text('Agregar'),
                    ),
                  ],
                ),
                const SizedBox(height: 16),
                
                // Botones de acción
                Row(
                  children: [
                    Expanded(
                      child: OutlinedButton.icon(
                        onPressed: _agregarEjemplos,
                        icon: const Icon(Icons.lightbulb),
                        label: const Text('Ejemplos'),
                      ),
                    ),
                    const SizedBox(width: 8),
                    Expanded(
                      child: OutlinedButton.icon(
                        onPressed: _limpiarLista,
                        icon: const Icon(Icons.clear_all),
                        label: const Text('Limpiar'),
                        style: OutlinedButton.styleFrom(
                          foregroundColor: Colors.red,
                        ),
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                
                // Contador
                Container(
                  width: double.infinity,
                  padding: const EdgeInsets.all(12),
                  decoration: BoxDecoration(
                    color: Theme.of(context).colorScheme.surfaceVariant,
                    borderRadius: BorderRadius.circular(8),
                  ),
                  child: Text(
                    '${_tareas.length} elementos (${_tareas.where((t) => t.completada).length} completadas)',
                    style: const TextStyle(fontSize: 16),
                    textAlign: TextAlign.center,
                  ),
                ),
              ],
            ),
          ),
          
          // Lista de tareas
          Expanded(
            child: _tareas.isEmpty
                ? const Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Icon(
                          Icons.inbox,
                          size: 64,
                          color: Colors.grey,
                        ),
                        SizedBox(height: 16),
                        Text(
                          '📝 No hay tareas aún',
                          style: TextStyle(
                            fontSize: 18,
                            color: Colors.grey,
                          ),
                        ),
                        Text(
                          'Agrega algunas tareas o usa los ejemplos',
                          style: TextStyle(
                            fontSize: 14,
                            color: Colors.grey,
                          ),
                        ),
                      ],
                    ),
                  )
                : ListView.builder(
                    padding: const EdgeInsets.all(16),
                    itemCount: _tareas.length,
                    itemBuilder: (context, index) {
                      final tarea = _tareas[index];
                      return Card(
                        margin: const EdgeInsets.only(bottom: 8),
                        child: ListTile(
                          leading: Checkbox(
                            value: tarea.completada,
                            onChanged: (bool? value) {
                              setState(() {
                                tarea.completada = value ?? false;
                              });
                            },
                          ),
                          title: Text(
                            tarea.texto,
                            style: TextStyle(
                              decoration: tarea.completada
                                  ? TextDecoration.lineThrough
                                  : TextDecoration.none,
                              color: tarea.completada
                                  ? Colors.grey
                                  : null,
                            ),
                          ),
                          trailing: IconButton(
                            icon: const Icon(Icons.delete, color: Colors.red),
                            onPressed: () => _eliminarTarea(index),
                          ),
                        ),
                      );
                    },
                  ),
          ),
        ],
      ),
    );
  }

  void _agregarTarea() {
    final texto = _controller.text.trim();
    if (texto.isNotEmpty) {
      setState(() {
        _tareas.add(Task(
          id: DateTime.now().millisecondsSinceEpoch.toString(),
          texto: texto,
        ));
      });
      _controller.clear();
      _mostrarSnackBar('✅ Tarea agregada');
    } else {
      _mostrarSnackBar('⚠️ Escribe algo primero');
    }
  }

  void _eliminarTarea(int index) {
    setState(() {
      _tareas.removeAt(index);
    });
    _mostrarSnackBar('🗑️ Tarea eliminada');
  }

  void _limpiarLista() {
    if (_tareas.isNotEmpty) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: const Text('Confirmar'),
            content: const Text('¿Estás seguro de que quieres eliminar todas las tareas?'),
            actions: [
              TextButton(
                onPressed: () => Navigator.of(context).pop(),
                child: const Text('Cancelar'),
              ),
              TextButton(
                onPressed: () {
                  Navigator.of(context).pop();
                  setState(() {
                    _tareas.clear();
                  });
                  _mostrarSnackBar('🧹 Lista limpiada');
                },
                child: const Text('Confirmar'),
              ),
            ],
          );
        },
      );
    } else {
      _mostrarSnackBar('ℹ️ La lista ya está vacía');
    }
  }

  void _agregarEjemplos() {
    final ejemplos = [
      'Estudiar Flutter 📱',
      'Practicar Dart 💙',
      'Crear una app increíble 🚀',
      'Leer documentación 📚',
      'Hacer ejercicio 💪',
      'Tomar descansos ☕',
    ];

    setState(() {
      for (String ejemplo in ejemplos) {
        _tareas.add(Task(
          id: '${DateTime.now().millisecondsSinceEpoch}_${ejemplo.hashCode}',
          texto: ejemplo,
        ));
      }
    });

    _mostrarSnackBar('📝 Ejemplos agregados');
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