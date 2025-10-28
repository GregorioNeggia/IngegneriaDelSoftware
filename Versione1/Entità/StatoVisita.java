package Versione1.Entità;

public enum StatoVisita {
    PROPOSTA,      // Visita proposta, in attesa di iscrizioni
    COMPLETA,      // Raggiunto il numero massimo di partecipanti
    CONFERMATA,    // Confermata (superato il minimo)
    CANCELLATA,    // Cancellata (non raggiunto il minimo)
    EFFETTUATA     // Effettuata (va in archivio storico)
}
