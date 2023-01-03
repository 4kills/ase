package de.dhbw.karlsruhe.ase.application;

import de.dhbw.karlsruhe.ase.domain.crafting.Rescue;
import de.dhbw.karlsruhe.ase.domain.dice.Roll;

record RescueMock(boolean endeavorReturnValue) implements Rescue {

    @Override
    public boolean endeavor(Roll roll) {
        return endeavorReturnValue;
    }
}
