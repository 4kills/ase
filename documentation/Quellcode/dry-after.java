// ... 
// Stelle 1 (Zeile 81-88): 

        if (!success) {
            checkLose();
            return GameResult.LOSE;
        }

        endGameWith(GameResult.WIN);
        return GameResult.WIN;
    }        

// ...
// Stelle 2 (Zeile 104-109):

        if (state.getInvalidator().isDepleted()
                && !state.getCamp().canBuildAnything()
                && state.getPhase() != GamePhase.ENCOUNTER) {
            endGameWith(GameResult.LOSE);
            return state.getInvalidator().getLastDraw();
        }

// ...
// Stelle 3 (Zeile 181-187):
    
    private void checkLose() {
        if (state.getInvalidator().isDepleted()
                && !state.getCamp().canBuildAnything()
                && state.getPhase() == GamePhase.SCAVENGE) {
            endGameWith(GameResult.LOSE);
        }
    }
// ...
// Neue Methode (189-193):

    private void endGameWith(GameResult result) {
        state.setPhase(GamePhase.END);
        state.setStatus(GameStatus.ENDED);
        state.setResult(result);
    }
// ...