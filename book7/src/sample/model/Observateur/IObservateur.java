package sample.model.Observateur;


/**
 * Patron observateur. Représente un observateur
 */
public interface IObservateur {
    void update(Observable obs, Object o);
}
