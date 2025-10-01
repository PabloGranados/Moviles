import 'package:flutter/material.dart';
import 'pages/home_page.dart';

void main() {
  runApp(const UIElementsApp());
}

class UIElementsApp extends StatelessWidget {
  const UIElementsApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'UI Elements Demo - Flutter',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const HomePage(),
      debugShowCheckedModeBanner: false,
    );
  }
}