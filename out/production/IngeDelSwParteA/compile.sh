#!/bin/bash

# Script per compilare il progetto Java
echo "🔨 Compilazione in corso..."

# Pulisce la directory bin se esiste
if [ -d "bin" ]; then
    rm -rf bin/*
    echo "📁 Directory bin pulita"
fi

# Crea la directory bin se non esiste
mkdir -p bin

# Compila tutti i file Java
javac -cp lib/gson-2.13.1.jar -d bin Versione1/*.java Versione1/Entità/*.java

# Controlla se la compilazione è andata a buon fine
if [ $? -eq 0 ]; then
    echo "✅ Compilazione completata con successo!"
    echo "🚀 Puoi ora eseguire il programma con: java -cp bin:lib/gson-2.13.1.jar Versione1.Main"
else
    echo "❌ Errore durante la compilazione!"
    exit 1
fi