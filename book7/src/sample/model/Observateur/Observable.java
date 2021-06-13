package sample.model.Observateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Patron observateur. Représente un observable
 */
public abstract class Observable {
    private List<IObservateur> observateurs = new ArrayList<>();

    public void attach(IObservateur obs) {
        observateurs.add(obs);
    }

    public void dettach(IObservateur obs) {
        observateurs.remove(obs);
    }

    public void notifier(Object o) {
        for(IObservateur obs: observateurs) {
            obs.update(this, o);
        }
    }
}
