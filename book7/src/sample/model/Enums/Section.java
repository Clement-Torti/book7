package sample.model.Enums;


// ------------------------
// Rôle: Enumération indiquant le type de contenu d'un cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public enum Section {
    COURS {
        public String toString() {
            return "COURS";
        }
    },

    TD{
        public String toString() {
            return "TD";
        }
    },

    TP {
        public String toString() {
            return "TP";
        }
    }



    }
