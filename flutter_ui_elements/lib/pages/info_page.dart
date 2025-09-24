import 'package:flutter/material.dart';
import 'dart:async';

class InfoPage extends StatefulWidget {
  const InfoPage({super.key});

  @override
  State<InfoPage> createState() => _InfoPageState();
}

class _InfoPageState extends State<InfoPage> with TickerProviderStateMixin {
  late AnimationController _progressController;
  late AnimationController _rotationController;
  
  bool _progresando = false;
  double _progresoActual = 0.0;
  int _contadorVisitas = 0;
  String _estadoConexion = '📡 Estado: Conectado perfectamente 🟢';
  String _fechaHora = '';
  Timer? _progressTimer;
  Timer? _clockTimer;

  @override
  void initState() {
    super.initState();
    _progressController = AnimationController(
      duration: const Duration(seconds: 5),
      vsync: this,
    );
    
    _rotationController = AnimationController(
      duration: const Duration(seconds: 2),
      vsync: this,
    )..repeat();
    
    _contadorVisitas++;
    _actualizarFechaHora();
    _iniciarReloj();
  }

  @override
  void dispose() {
    _progressController.dispose();
    _rotationController.dispose();
    _progressTimer?.cancel();
    _clockTimer?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('ℹ️ Elementos de Información'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Título
            const Text(
              'ℹ️ Elementos de Información',
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 16),
            
            // Explicación
            const Text(
              '💡 Los elementos de información muestran datos al usuario de manera clara y organizada, incluyendo texto, imágenes y indicadores de progreso.',
              style: TextStyle(fontSize: 16),
            ),
            const SizedBox(height: 24),
            
            // Textos con diferentes estilos
            const Text(
              '📝 Diferentes estilos de texto:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Card(
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      'Texto normal con tamaño estándar',
                      style: TextStyle(fontSize: 16),
                    ),
                    const SizedBox(height: 8),
                    const Text(
                      'Texto en negrita',
                      style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 8),
                    const Text(
                      'Texto en cursiva',
                      style: TextStyle(fontSize: 16, fontStyle: FontStyle.italic),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      'Texto con color personalizado',
                      style: TextStyle(
                        fontSize: 16,
                        color: Theme.of(context).colorScheme.primary,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 16),
            
            // Íconos interactivos
            const Text(
              '🖼️ Íconos interactivos:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Card(
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    Column(
                      children: [
                        IconButton(
                          onPressed: () => _cambiarEstado('📡 Estado: Información mostrada ℹ️'),
                          icon: const Icon(Icons.info, size: 32, color: Colors.blue),
                        ),
                        const Text('Info'),
                      ],
                    ),
                    Column(
                      children: [
                        IconButton(
                          onPressed: () => _cambiarEstado('📡 Estado: ¡Favorito marcado! ⭐'),
                          icon: const Icon(Icons.star, size: 32, color: Colors.orange),
                        ),
                        const Text('Favorito'),
                      ],
                    ),
                    Column(
                      children: [
                        IconButton(
                          onPressed: () => _cambiarEstado('📡 Estado: ¡Alerta activada! ⚠️'),
                          icon: const Icon(Icons.warning, size: 32, color: Colors.red),
                        ),
                        const Text('Alerta'),
                      ],
                    ),
                    Column(
                      children: [
                        AnimatedBuilder(
                          animation: _rotationController,
                          builder: (context, child) {
                            return Transform.rotate(
                              angle: _rotationController.value * 2 * 3.14159,
                              child: IconButton(
                                onPressed: () => _cambiarEstado('📡 Estado: ¡Sincronizando! 🔄'),
                                icon: const Icon(Icons.sync, size: 32, color: Colors.green),
                              ),
                            );
                          },
                        ),
                        const Text('Sync'),
                      ],
                    ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 16),
            
            // ProgressBar
            const Text(
              '📊 Indicadores de progreso:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Card(
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  children: [
                    Text(
                      'Progreso: ${(_progresoActual * 100).toInt()}%',
                      style: const TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 8),
                    LinearProgressIndicator(
                      value: _progresoActual,
                      minHeight: 8,
                    ),
                    const SizedBox(height: 16),
                    Row(
                      children: [
                        Expanded(
                          child: ElevatedButton.icon(
                            onPressed: _progresando ? null : _iniciarProgreso,
                            icon: const Icon(Icons.play_arrow),
                            label: const Text('Iniciar'),
                          ),
                        ),
                        const SizedBox(width: 8),
                        Expanded(
                          child: ElevatedButton.icon(
                            onPressed: _progresando ? _detenerProgreso : null,
                            icon: const Icon(Icons.stop),
                            label: const Text('Detener'),
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 16),
                    if (_progresando)
                      const Column(
                        children: [
                          CircularProgressIndicator(),
                          SizedBox(height: 8),
                          Text('Procesando...'),
                        ],
                      ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 16),
            
            // Información dinámica
            const Text(
              '🕐 Información dinámica:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Card(
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: [
                        const Icon(Icons.access_time, color: Colors.blue),
                        const SizedBox(width: 8),
                        Expanded(child: Text(_fechaHora)),
                      ],
                    ),
                    const Divider(),
                    Row(
                      children: [
                        const Icon(Icons.visibility, color: Colors.green),
                        const SizedBox(width: 8),
                        Expanded(
                          child: Text('Veces que has visitado esta pantalla: $_contadorVisitas'),
                        ),
                      ],
                    ),
                    const Divider(),
                    Row(
                      children: [
                        const Icon(Icons.wifi, color: Colors.orange),
                        const SizedBox(width: 8),
                        Expanded(child: Text(_estadoConexion)),
                      ],
                    ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 16),
            
            // Botón actualizar
            SizedBox(
              width: double.infinity,
              child: ElevatedButton.icon(
                onPressed: () {
                  setState(() {
                    _contadorVisitas++;
                  });
                  _actualizarEstado();
                },
                icon: const Icon(Icons.refresh),
                label: const Text('🔄 Actualizar Información'),
              ),
            ),
          ],
        ),
      ),
    );
  }

  void _iniciarProgreso() {
    setState(() {
      _progresando = true;
      _progresoActual = 0.0;
    });

    _progressTimer = Timer.periodic(const Duration(milliseconds: 100), (timer) {
      setState(() {
        _progresoActual += 0.02;
      });

      if (_progresoActual >= 1.0) {
        _completarProgreso();
      }
    });
  }

  void _detenerProgreso() {
    _progressTimer?.cancel();
    setState(() {
      _progresando = false;
    });
  }

  void _completarProgreso() {
    _progressTimer?.cancel();
    setState(() {
      _progresando = false;
      _progresoActual = 1.0;
      _estadoConexion = '📡 Estado: ¡Progreso completado! ✅';
    });

    // Reiniciar después de 2 segundos
    Timer(const Duration(seconds: 2), () {
      if (mounted) {
        setState(() {
          _progresoActual = 0.0;
        });
      }
    });
  }

  void _cambiarEstado(String nuevoEstado) {
    setState(() {
      _estadoConexion = nuevoEstado;
    });
  }

  void _actualizarFechaHora() {
    final now = DateTime.now();
    final fecha = '${now.day.toString().padLeft(2, '0')}/${now.month.toString().padLeft(2, '0')}/${now.year}';
    final hora = '${now.hour.toString().padLeft(2, '0')}:${now.minute.toString().padLeft(2, '0')}:${now.second.toString().padLeft(2, '0')}';
    
    setState(() {
      _fechaHora = 'Fecha y hora actual: $fecha $hora';
    });
  }

  void _iniciarReloj() {
    _clockTimer = Timer.periodic(const Duration(seconds: 1), (timer) {
      _actualizarFechaHora();
    });
  }

  void _actualizarEstado() {
    final estados = [
      '📡 Estado: Conectado perfectamente 🟢',
      '📡 Estado: Conexión estable 🔵',
      '📡 Estado: Señal buena 🟡',
      '📡 Estado: Todo funcionando 🚀'
    ];
    
    setState(() {
      _estadoConexion = estados[DateTime.now().millisecond % estados.length];
    });
  }
}