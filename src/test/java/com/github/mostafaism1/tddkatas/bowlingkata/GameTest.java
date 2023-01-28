package com.github.mostafaism1.tddkatas.bowlingkata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
  private Game game;

  @BeforeEach
  void setup() {
    game = new Game();
  }

  @Test
  void noRolls() {
    assertEquals(0, game.score());
  }

  @Test
  void singleRollOnFirstFrame() {
    rollOne();
    assertEquals(1, game.score());
  }

  @Test
  void twoRollsOnFirstFrameAndNoBonus() {
    rollOne();
    rollOne();
    assertEquals(2, game.score());
  }

  @Test
  void spareOnFirstFrame() {
    rollSpare();
    rollOne();
    rollOne();
    assertEquals(13, game.score());
  }

  @Test
  void strikeOnFirstFrame() {
    rollStrike();
    rollOne();
    rollOne();
    assertEquals(14, game.score());
  }

  @Test
  void consecutiveSpares() {
    rollSpare();
    rollSpare();
    rollOne();
    rollOne();
    assertEquals(28, game.score());
  }

  @Test
  void consecutiveStrikes() {
    rollStrike();
    rollStrike();
    rollOne();
    rollOne();
    assertEquals(35, game.score());
  }

  @Test
  void spareThenStrike() {
    rollSpare();
    rollStrike();
    rollOne();
    rollOne();
    assertEquals(34, game.score());
  }

  @Test
  void strikeThenSpare() {
    rollStrike();
    rollSpare();
    rollOne();
    rollOne();
    assertEquals(33, game.score());
  }

  @Test
  void perfectGame() {
    for (int i = 0; i < 12; i++) {
      rollStrike();
    }
    assertEquals(300, game.score());
  }

  @Test
  void rollingBeyondGameLimit() {
    for (int i = 0; i < 20; i++) {
      rollOne();
    }
    assertThrows(IllegalStateException.class, this::rollOne);
  }

  private void rollOne() {
    game.roll(1);
  }

  private void rollSpare() {
    game.roll(5);
    game.roll(5);
  }

  private void rollStrike() {
    game.roll(10);
  }
}
