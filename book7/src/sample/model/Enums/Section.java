package sample.model.Enums;


/**
 * Enumération indiquant le type de contenu d'un cahier
 */
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
