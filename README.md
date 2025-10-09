# 📋 TODO List - Progetto Ingegneria del Software 2024-25

> **Sistema Gestione Visite Guidate - Gruppo di 3 Persone**

## 🎯 Panoramica del Progetto

**Obiettivo**: Sviluppare un sistema software incrementale/iterativo in Java per la gestione di visite guidate

**Struttura**: 4 versioni progressive + documentazione UML completa + demo funzionante

---

## 📅 Milestone e Scadenze

| Versione | Completamento Target | Deliverable Principali |
|----------|---------------------|------------------------|
| **V1** | Settimana 2 | Configuratore + Setup dati + UML |
| **V2** | Settimana 4 | Gestione Volontari + Disponibilità |
| **V3** | Settimana 7 | Pianificazione automatica + Algoritmi complessi |
| **V4** | Settimana 9 | Interfaccia Fruitore + Sistema completo |
| **Demo** | Settimana 10 | Preparazione esame + Dati persistenti |

> ⚠️ **SCADENZA FINALE**: Consegna entro la data della prova orale dell'appello precedente

---

## 👥 Divisione del Lavoro

### 🔍 **PERSONA 1** - Analisi e Documentazione UML
**Responsabilità**: Requisiti, Casi d'uso, Documentazione, Manuali

<details>
<summary><strong>📝 VERSIONE 1 - Setup Configuratore</strong></summary>

#### Analisi Requisiti V1
- [ ] Studiare requisiti funzionali configuratore
- [ ] Identificare setup iniziale (ambito territoriale, luoghi, volontari)
- [ ] Definire flussi di autenticazione

#### Casi d'uso Testuali V1  
- [ ] UC: Autenticazione configuratore
- [ ] UC: Creazione ambito territoriale
- [ ] UC: Aggiunta luoghi di interesse
- [ ] UC: Registrazione volontari
- [ ] UC: Gestione tipi visita
- [ ] UC: Visualizzazione dati esistenti
- [ ] UC: Gestione date precluse

#### Diagrammi UML V1
- [ ] Diagramma casi d'uso V1
- [ ] Identificazione attori e relazioni
- [ ] Documentazione vincoli e precondizioni

</details>

<details>
<summary><strong>🔧 VERSIONE 2 - Estensione Volontari</strong></summary>

#### Casi d'uso Aggiuntivi V2
- [ ] UC: Autenticazione volontario
- [ ] UC: Dichiarazione disponibilità mensile
- [ ] UC: Modifica disponibilità esistente
- [ ] UC: Visualizzazione proprie disponibilità

#### Aggiornamento UML V2
- [ ] Integrare nuovi casi d'uso nel diagramma
- [ ] Evidenziare modifiche rispetto a V1
- [ ] Aggiornare relazioni tra attori

</details>

<details>
<summary><strong>⚙️ VERSIONE 3 - Algoritmi Complessi</strong></summary>

#### Documentazione Algoritmi
- [ ] Algoritmo pianificazione mensile visite
- [ ] Logica gestione vincoli temporali
- [ ] Regole assegnazione volontari
- [ ] Gestione effetti collaterali rimozioni

#### Casi d'uso Complessi V3
- [ ] UC: Pianificazione automatica mensile
- [ ] UC: Rimozione luoghi (con effetti collaterali)
- [ ] UC: Rimozione volontari (con gestione conflitti)
- [ ] UC: Rimozione tipi visita

</details>

<details>
<summary><strong>👤 VERSIONE 4 - Sistema Completo</strong></summary>

#### Casi d'uso Fruitore
- [ ] UC: Visualizzazione calendario visite
- [ ] UC: Iscrizione a visita confermata
- [ ] UC: Disdetta prenotazione
- [ ] UC: Gestione codice prenotazione

#### Finalizzazione UML
- [ ] Diagramma casi d'uso completo
- [ ] Documentazione finale tutti gli attori

</details>

<details>
<summary><strong>📚 TASK FINALI</strong></summary>

#### Documentazione Utente
- [ ] Manuale installazione ambiente
- [ ] Manuale utilizzo sistema
- [ ] Documentazione help inline
- [ ] Guida risoluzione problemi comuni

#### Raccolta Materiali
- [ ] Unificare documentazione in file unico
- [ ] Controllo formattazione e coerenza
- [ ] Revisione finale contenuti

</details>

---

### 🏗️ **PERSONA 2** - Architettura e Design
**Responsabilità**: Progettazione classi, Architettura, Implementazione core

<details>
<summary><strong>🏛️ VERSIONE 1 - Architettura Base</strong></summary>

#### Progettazione Sistema
- [ ] Definire architettura stand-alone
- [ ] Progettare separazione logica back-end/front-end
- [ ] Definire pattern architetturali utilizzati
- [ ] Pianificare estensibilità futura

#### Diagramma Classi V1
- [ ] Classe `Configuratore`
- [ ] Classe `Luogo` (con coordinate, descrizione)
- [ ] Classe `TipoVisita` (durata, numero partecipanti)
- [ ] Classe `Volontario` (con competenze)
- [ ] Relazioni e molteplicità
- [ ] Attributi e metodi principali

#### Implementazione Core V1
- [ ] Sistema autenticazione configuratore
- [ ] Gestione persistenza dati (file/database)
- [ ] Classi entità principali
- [ ] Validazione input dati

</details>

<details>
<summary><strong>🔄 VERSIONE 2 - Estensione Features</strong></summary>

#### Aggiornamento Architettura V2
- [ ] Aggiornare diagramma classi per volontari
- [ ] Classe `Disponibilita` (giorni/orari)
- [ ] Metodi gestione disponibilità
- [ ] Integrazione con sistema esistente

#### Implementazione V2
- [ ] Sistema autenticazione volontario
- [ ] Interfaccia dichiarazione disponibilità
- [ ] Validazione sovrapposizioni temporali
- [ ] Persistenza nuovi dati

</details>

<details>
<summary><strong>🧠 VERSIONE 3 - Logica Avanzata</strong></summary>

#### Algoritmi Pianificazione
- [ ] Classe `PianificatoreVisite`
- [ ] Algoritmo ottimizzazione assegnazioni
- [ ] Gestione vincoli multipli (tempo, competenze, disponibilità)
- [ ] Sistema notifiche conflitti

#### Gestione Rimozioni
- [ ] Algoritmo cascata effetti rimozione luoghi
- [ ] Gestione cancellazione volontari con visite assegnate
- [ ] Sistema backup/rollback operazioni critiche
- [ ] Validazione consistenza dati

#### Refactoring
- [ ] Ottimizzare design per performance
- [ ] Implementare pattern Strategy per algoritmi
- [ ] Preparare architettura per V4

</details>

<details>
<summary><strong>🎨 VERSIONE 4 - Interfaccia Completa</strong></summary>

#### Front-end Fruitore
- [ ] Classe `Fruitore`
- [ ] Interfaccia calendario visite
- [ ] Sistema prenotazioni con codici
- [ ] Gestione disdette e modifiche

#### Finalizzazione
- [ ] Diagramma classi finale completo
- [ ] Documentazione design decisions
- [ ] Code review architettura
- [ ] Preparazione per demo

</details>

---

### ⚡ **PERSONA 3** - Implementazione e Testing
**Responsabilità**: Codice, Testing, Demo, Ambiente tecnico

<details>
<summary><strong>🔧 VERSIONE 1 - Setup Tecnico</strong></summary>

#### Setup Progetto
- [ ] Configurare ambiente Java (JDK, IDE)
- [ ] Setup sistema build (Maven/Gradle)
- [ ] Struttura directory progetto
- [ ] Configurare Git repository
- [ ] Setup CI/CD se necessario

#### Persistenza e I/O
- [ ] Sistema salvataggio/caricamento dati
- [ ] Interfaccia utente testuale
- [ ] Gestione file configurazione
- [ ] Logging e debugging tools

#### Testing V1
- [ ] Unit test classi base
- [ ] Test autenticazione configuratore
- [ ] Test CRUD operazioni base
- [ ] Validazione persistenza dati

</details>

<details>
<summary><strong>🔄 VERSIONE 2 - Features Volontari</strong></summary>

#### Implementazione V2
- [ ] Sistema gestione volontari completo
- [ ] Validazione input disponibilità
- [ ] Interfaccia utente per volontari
- [ ] Integrazione con dati esistenti

#### Testing V2
- [ ] Test autenticazione volontario
- [ ] Test gestione disponibilità
- [ ] Test integrazione V1-V2
- [ ] Regression testing
- [ ] Performance testing base

</details>

<details>
<summary><strong>🧪 VERSIONE 3 - Testing Complesso</strong></summary>

#### Testing Algoritmi
- [ ] Test pianificazione mensile
- [ ] Test edge cases algoritmi
- [ ] Test gestione rimozioni e cascate
- [ ] Test vincoli temporali complessi
- [ ] Test scenarios limite (no disponibilità, conflitti)

#### Preparazione Dati Demo
- [ ] **2 Configuratori** con credenziali test
- [ ] **2 Volontari** con disponibilità varie
- [ ] **3 Fruitori** (per test prenotazioni)
- [ ] **2 Luoghi** con descrizioni complete
- [ ] **3 Tipi visita** diversi
- [ ] **5 Visite proposte**, 2 confermate, 1 cancellata
- [ ] Dataset persistente per demo rapida

</details>

<details>
<summary><strong>🚀 VERSIONE 4 - Finalizzazione</strong></summary>

#### Implementazione Front-end
- [ ] Interfaccia fruitore completa
- [ ] Sistema iscrizioni con codici
- [ ] Gestione disdette
- [ ] Notifiche e feedback utente

#### Testing Finale
- [ ] End-to-end testing tutti i flussi
- [ ] User acceptance testing
- [ ] Performance testing carico
- [ ] Security testing basic
- [ ] Cross-platform testing

#### Preparazione Demo
- [ ] Script dimostrazione completa
- [ ] Setup portatile con ambiente
- [ ] Test su macchina pulita
- [ ] Backup dati demo
- [ ] Documentazione troubleshooting

</details>

<details>
<summary><strong>📦 TASK FINALI</strong></summary>

#### Packaging e Delivery
- [ ] Creazione eseguibili (.jar)
- [ ] Packaging codice sorgente
- [ ] Preparazione file README
- [ ] Upload materiali su Moodle
- [ ] Verifica integrità file consegnati

</details>

---

## 🤝 Task Condivisi - Tutto il Team

### Comunicazione e Coordinamento
- [ ] Setup canale comunicazione team (Discord/Telegram/Slack)
- [ ] Configurazione repository Git condiviso
- [ ] Calendario milestone condiviso
- [ ] Regular standup meetings (2x settimana)
- [ ] Documentazione decisioni architetturali

### Quality Assurance
- [ ] Code review incrociato
- [ ] Controllo aderenza requisiti documento
- [ ] Validazione completezza documentazione
- [ ] Sync periodici sullo stato avanzamento
- [ ] Backup regolari lavoro

### Preparazione Esame Orale
- [ ] **Studio materiale**: Tutti devono conoscere l'intero progetto
- [ ] **Preparazione domande**: Simulare domande possibili sui design choices
- [ ] **Rehearsal demo**: Provare presentazione completa
- [ ] **Divisione responsabilità**: Chi presenta cosa durante l'esame

---

## ⚠️ Note Critiche per il Team

### Requisiti Obbligatori
- ✅ **Precondizioni/Postcondizioni Java** - Implementarle nel codice (criterio valutazione)
- ✅ **Architettura Estendibile** - Pensare alle estensioni future nel documento
- ✅ **4 Versioni Progressive** - Ogni versione deve funzionare autonomamente
- ✅ **UML Aggiornato** - Diagrammi per ogni versione con evidenza modifiche

### Setup Demo
- 💻 **Hardware**: Portare portatile con cavo alimentazione + adattatore HDMI
- 💾 **Dati Persistenti**: Evitare input lunghi durante demo
- 🔧 **Backup Plan**: Avere setup alternativo pronto
- ⏱️ **Timing**: Demo deve durare max 15-20 minuti

### Gestione Rischi
- 🔄 **Versioning**: Tenere versioni funzionanti sempre disponibili
- 🧪 **Testing Continuo**: Non accumulare testing solo alla fine
- 📋 **Documentazione Continua**: Aggiornare UML man mano che si sviluppa
- 🤝 **Sync Team**: Comunicazione costante per evitare conflitti

---

## 📊 Checklist Finale Pre-Consegna

### Codice
- [ ] Tutti i file compilano senza errori/warning
- [ ] Precondizioni/postcondizioni implementate
- [ ] Commenti e documentazione inline
- [ ] Code style consistente
- [ ] No codice duplicato significativo

### Documentazione  
- [ ] Diagrammi UML per tutte le 4 versioni
- [ ] Casi d'uso completi e corretti
- [ ] Manuale installazione e uso
- [ ] Evidenze modifiche tra versioni
- [ ] File unified con tutta la documentazione

### Demo
- [ ] Sistema funziona end-to-end
- [ ] Dati demo precaricati e significativi
- [ ] Tutti i casi d'uso dimostrabili
- [ ] Performance accettabili
- [ ] Setup portatile testato

### Team
- [ ] Tutti conoscono ogni parte del progetto
- [ ] Responsabilità demo assegnate
- [ ] Domande possibili preparate
- [ ] Materiali backup pronti

---

*Creato per il corso di Ingegneria del Software 2024-25 - Prof.ssa Marina Zanella*
