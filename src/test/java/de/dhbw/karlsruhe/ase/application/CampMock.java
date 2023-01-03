package de.dhbw.karlsruhe.ase.application;

import de.dhbw.karlsruhe.ase.domain.crafting.Camp;

import java.util.UUID;

class CampMock extends Camp {

    private final int bonusDamage;

    public CampMock(int bonusDamage) {
        super(new UUID(0, 0));
        this.bonusDamage = bonusDamage;
    }

    @Override
    public int getBonusDamage() {
        return bonusDamage;
    }
}
