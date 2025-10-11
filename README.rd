# Progetto Gestione Configuratori - Modifiche e Aggiornamenti

## 📋 Panoramica del Progetto
Sistema di gestione per configuratori e volontari con interfaccia a menu, persistenza dati JSON e sistema di autenticazione.

## 🔄 Log delle Modifiche Principali

### **1. Ristrutturazione Architetturale**

#### **Refactoring delle Classi**
- **Spostamento `GestioneConfiguratori.java`** → `Versione1/Gestori/GestioneConfiguratori.java`
- **Creazione package `Gestori`** per organizzare meglio le classi di business logic
- **Aggiunta `GestoreVolontari.java`** nel package `Gestori` per gestire i volontari

#### **Separazione delle Responsabilità**
- **`Main.java`** → Entry point unico del programma
- **`Menu.java`** → Classe di utilità per menu principale (rimosso il main)
- **`MenuInterno.java`** → Nuovo menu per operazioni post-login del configuratore

### **2. Sistema di Menu Migliorato**

#### **Menu Principale (`Menu.java`)**
```
=== MENU ===
1. Login
2. Stampa configuratori  
0. Esci
```

#### **Menu Interno Configuratore (`MenuInterno.java`)**
```
=== MENU CONFIGURATORE ===
1. Setup configuratore
2. Stampa informazioni complete
3. Stampa luoghi e volontari
0. Logout
```

**Funzionalità Implementate:**
- **Setup interattivo** per aggiungere luoghi e volontari
- **Visualizzazione completa** dei dati configuratore
- **Gestione persistenza** automatica dei dati
- **Validazione input** con gestione errori

### **3. Gestione Dati e Persistenza**

#### **Separazione File JSON**
- **`configuratori.json`** → Dati dei configuratori
- **`volontari.json`** → Database volontari separato

#### **Miglioramenti Utilità (`Utilità.java`)**
- **`leggiJSonConfiguratori()`** → Metodo specifico per configuratori
- **`stampaConfiguratori()`** → Visualizzazione formattata
- **Gestione errori** migliorata per I/O file

### **4. Correzioni e Bug Fix**

#### **Risoluzione NullPointerException**
- **Problema:** Lista `luoghiInteresse` null quando si aggiungevano luoghi
- **Soluzione:** Inizializzazione corretta delle liste nel costruttore `Configuratore`

#### **Correzione Classpath**
- **Problema:** VS Code non trovava le classi compilate
- **Soluzione:** Aggiornamento `.vscode/launch.json` con classpath corretto:
  ```json
  "classPaths": [
      "bin",
      "lib/gson-2.13.1.jar"
  ]
  ```

#### **Script di Compilazione**
- **Creazione `compile.sh`** → Script automatico per compilazione:
  ```bash
  javac -cp lib/gson-2.13.1.jar -d bin Versione1/*.java Versione1/Entità/*.java
  ```

### **5. Miglioramenti UX/UI**

#### **Interfaccia Utente**
- **Menu interattivi** con navigazione intuitiva
- **Messaggi informativi** per feedback utente
- **Gestione logout** con return al menu principale
- **Validazione input** con re-prompt in caso di errori

#### **Flusso Applicazione**
```
Main → Menu → Login → MenuInterno → [Operazioni] → Logout → Menu
```

### **6. Struttura File Finale**

```
IngegneriaDelSoftware/
├── README.rd
├── compile.sh                    # Script compilazione
├── configuratori.json            # Dati configuratori
├── volontari.json               # Dati volontari  
├── lib/
│   └── gson-2.13.1.jar          # Libreria JSON
├── bin/                         # File compilati
│   └── Versione1/
├── .vscode/
│   └── launch.json              # Configurazione VS Code
└── Versione1/
    ├── Main.java                # Entry point
    ├── Menu.java                # Menu principale
    ├── MenuInterno.java         # Menu configuratore
    ├── Utilità.java            # Metodi di utilità
    ├── Entità/
    │   ├── AmbitoTerritoriale.java
    │   ├── Configuratore.java
    │   ├── Luogo.java
    │   └── Volontario.java
    └── Gestori/
        ├── GestioneConfiguratori.java
        └── GestoreVolontari.java
```

### **7. Dipendenze e Tecnologie**

- **Java 21** (Temurin JDK)
- **Gson 2.13.1** per serializzazione JSON
- **Scanner** per input utente
- **ArrayList/List** per collezioni dati

### **8. Istruzioni di Utilizzo**

#### **Compilazione**
```bash
./compile.sh
```

#### **Esecuzione**
```bash
java -cp bin:lib/gson-2.13.1.jar Versione1.Main
```

#### **Credenziali Default Admin**
- **Username:** `admin`
- **Password:** `admin123`

### **9. Prossimi Sviluppi Pianificati**

- [ ] **Sistema autenticazione** con hash password
- [ ] **Gestione completa volontari** con CRUD operations
- [ ] **Validazione email** e telefono
- [ ] **Export/Import** dati in formati multipli
- [ ] **Logging** delle operazioni utente
- [ ] **Database** migration da JSON

---

## 📝 Note Tecniche

### **Pattern Architetturali Utilizzati**
- **MVC Pattern** (Model-View-Controller)
- **Singleton Pattern** per gestori dati
- **Factory Pattern** per creazione oggetti

### **Principi SOLID Applicati**
- **Single Responsibility** → Ogni classe ha una responsabilità specifica
- **Open/Closed** → Estensibile senza modificare codice esistente
- **Dependency Inversion** → Dipendenze verso astrazioni

---

**Versione:** 1.2.0  
**Data Ultimo Aggiornamento:** 11 Ottobre 2025  
**Autore:** Gregorio Neggia