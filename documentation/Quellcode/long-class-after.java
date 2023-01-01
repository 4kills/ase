public interface Parser<T, U> {
    T parse(U raw);
}

public final class CardParser implements Parser<Card, String> {

    @Override
    public Card parse(String raw) {
        for (var card : Card.values()) {
            if (card.toString().equals(raw)) {
                return card;
            }
        }

        throw new UnsupportedOperationException(raw + " has not been implemented");
    }
}

public final class CraftingPlanParser implements Parser<CraftingPlan, String> {

    @Override
    public CraftingPlan parse(final String raw) {
        for (var plan : CraftingPlan.values()) {
            if (plan.toString().equals(raw)) {
                return plan;
            }
        }

        throw new UnsupportedOperationException(raw + " has not been implemented");
    }
}

public final class DiceParser implements Parser<Dice, Matcher> {

    @Override
    public Dice parse(final Matcher raw) {
        final String roll;
        final DiceType diceType;

        if (raw.group(1) != null) {
            diceType = DiceType.FOUR_SIDED;
            roll = raw.group(2);
        } else if (raw.group(3) != null) {
            diceType = DiceType.SIX_SIDED;
            roll = raw.group(4);
        } else {
            diceType = DiceType.EIGHT_SIDED;
            roll = raw.group(6);
        }

        return new Dice(diceType, Integer.parseInt(roll));
    }

}