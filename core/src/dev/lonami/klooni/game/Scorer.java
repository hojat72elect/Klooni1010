package dev.lonami.klooni.game;

import dev.lonami.klooni.Klooni;
import dev.lonami.klooni.serializer.BinSerializable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// Used to keep track of the current and maximum
// score, and to also display it on the screen.
// The maximum score is NOT saved automatically.
public class Scorer extends BaseScorer implements BinSerializable {


    private int highScore;
    //region Constructor

    // The board size is required when calculating the score
    public Scorer(final Klooni game, GameLayout layout) {
        super(game, layout, Klooni.getMaxScore());
        highScore = Klooni.getMaxScore();
    }
    //region Public methods

    public void saveScore() {
        if (isNewRecord()) {
            Klooni.setMaxScore(currentScore);
        }
    }

    @Override
    protected boolean isNewRecord() {
        return currentScore > highScore;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
    //region Serialization

    @Override
    public void write(DataOutputStream out) throws IOException {
        // currentScore, highScore
        out.writeInt(currentScore);
        out.writeInt(highScore);
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        currentScore = in.readInt();
        highScore = in.readInt();
    }

    
}
