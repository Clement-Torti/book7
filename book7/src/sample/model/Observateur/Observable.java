package sample.model.Observateur;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<IObservateur> observateurs = new ArrayList<>();

    // Setters
    public void attach(IObservateur obs) {
        observateurs.add(obs);
    }

    public void dettach(IObservateur obs) {
        observateurs.remove(obs);
    }

    public void notifier() {
        for(IObservateur obs: observateurs) {
            obs.update(this);
        }
    }
}
