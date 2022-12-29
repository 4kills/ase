// ... 
// Stelle 1 (Zeile 81-90): 

        if (!success) {
            checkLose();
            return GameResult.LOSE;
        }

        state.setPhase(GamePhase.END);
        state.setStatus(GameStatus.ENDED);
        state.setResult(GameResult.WIN);
        return GameResult.WIN;
    }        

// ...
// Stelle 2 (Zeile 106-113):

        if (state.getInvalidator().isDepleted()
                && !state.getCamp().canBuildAnything()
                && state.getPhase() != GamePhase.ENCOUNTER) {
            state.setPhase(GamePhase.END);
            state.setStatus(GameStatus.ENDED);
            state.setResult(GameResult.LOSE);
            return state.getInvalidator().getLastDraw();
        }

// ...
// Stelle 3 (Zeile 185-193):
    
    public void checkLose() {
        if (state.getInvalidator().isDepleted()
                && !state.getCamp().canBuildAnything()
                && state.getPhase() == GamePhase.SCAVENGE) {
            state.setPhase(GamePhase.END);
            state.setStatus(GameStatus.ENDED);
            state.setResult(GameResult.LOSE);
        }
    }
// ...