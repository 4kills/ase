package de.dhbw.karlsruhe.ase.application;

import de.dhbw.karlsruhe.ase.domain.cards.Card;
import de.dhbw.karlsruhe.ase.domain.crafting.Camp;
import de.dhbw.karlsruhe.ase.domain.dice.DiceType;
import de.dhbw.karlsruhe.ase.domain.dice.Roll;
import de.dhbw.karlsruhe.ase.domain.dice.RollInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

public class RollHandlerTest {

    @ParameterizedTest
    @MethodSource("encounter")
    void encounter(int bonusDamage, Card card, Roll roll, RollHandler.OutcomeType expectedOutcome)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // setup
        Method encounter = RollHandler.class.getDeclaredMethod("encounter", Roll.class);
        encounter.setAccessible(true);

        // create mock
        var camp = new CampMock(bonusDamage);

        // inject mock
        var handler = new RollHandler(camp, card);

        // act
        encounter.invoke(handler, roll);

        // assert
        Assertions.assertEquals(expectedOutcome, handler.getOutcome());
    }

    private static List<Arguments> encounter() {
        return List.of(
                Arguments.of(0, Card.TIGER, new Roll(DiceType.EIGHT_SIDED, new RollInteger(4)), RollHandler.OutcomeType.LOSE),
                Arguments.of(1, Card.TIGER, new Roll(DiceType.EIGHT_SIDED, new RollInteger(4)), RollHandler.OutcomeType.SURVIVED),
                Arguments.of(10_000, Card.TIGER, new Roll(DiceType.EIGHT_SIDED, new RollInteger(1)), RollHandler.OutcomeType.SURVIVED),
                Arguments.of(1, Card.SNAKE, new Roll(DiceType.SIX_SIDED, new RollInteger(1)), RollHandler.OutcomeType.LOSE)
        );
    }

    @ParameterizedTest
    @MethodSource("endeavor")
    void endeavor(boolean rescueSuccess, RollHandler.OutcomeType expectedOutcome, GamePhase expectedGamePhase)
            throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        // setup
        Method endeavor = RollHandler.class.getDeclaredMethod("endeavor", Roll.class);
        endeavor.setAccessible(true);
        Field currentEndeavor = Camp.class.getDeclaredField("currentEndeavor");
        currentEndeavor.setAccessible(true);

        var camp = new Camp(new UUID(0, 0));
        var handler = new RollHandler(camp, Card.THUNDERSTORM); // card doesn't matter

        // inject mock here
        currentEndeavor.set(camp, new RescueMock(rescueSuccess));

        // act
        var gamePhase = endeavor.invoke(handler, new Object[]{null});

        // assert
        Assertions.assertEquals(expectedOutcome, handler.getOutcome());
        Assertions.assertEquals(expectedGamePhase, gamePhase);
    }

    private static List<Arguments> endeavor() {
        return List.of(
                Arguments.of(false, RollHandler.OutcomeType.LOSE, GamePhase.SCAVENGE),
                Arguments.of(true, RollHandler.OutcomeType.WIN, GamePhase.END)
        );
    }
}
