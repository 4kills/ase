public class ArgumentParser {

    public CraftingPlan parseConstructible(final String type) {
        switch (type) {
            case "axe":
                return CraftingPlan.AXE;
            case "club":
                return CraftingPlan.CLUB;
            case "shack":
                return CraftingPlan.SHACK;
            case "fireplace":
                return CraftingPlan.FIREPLACE;
            case "sailingraft":
                return CraftingPlan.SAILINGRAFT;
            case "hangglider":
                return CraftingPlan.HANGGLIDER;
            case "steamboat":
                return CraftingPlan.STEAMBOAT;
            case "ballon":
                return CraftingPlan.BALLON;
            default:
                throw new UnsupportedOperationException(type + " has not been implemented");
        }
    }

    public Dice parseDice(final Matcher mchr) {
        final String roll;
        final DiceType diceType;

        if (mchr.group(1) != null) {
            diceType = DiceType.FOUR_SIDED;
            roll = mchr.group(2);
        } else if (mchr.group(3) != null) {
            diceType = DiceType.SIX_SIDED;
            roll = mchr.group(4);
        } else {
            diceType = DiceType.EIGHT_SIDED;
            roll = mchr.group(6);
        }

        return new Dice(diceType, Integer.parseInt(roll));
    }

    public Card parseCard(final String type) {
        switch (type) {
            case "wood":
                return Card.WOOD;
            case "metal":
                return Card.METAL;
            case "plastic":
                return Card.PLASTIC;
            case "spider":
                return Card.SPIDER;
            case "snake":
                return Card.SNAKE;
            case "tiger":
                return Card.TIGER;
            case "thunderstorm":
                return Card.THUNDERSTORM;
            default:
                throw new UnsupportedOperationException(type + " has not been implemented");
        }
    }
}