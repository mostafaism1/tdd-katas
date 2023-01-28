package com.github.mostafaism1.tddkatas.bowlingkata;

class DefaultFrame implements Frame {
  private Frame previous;
  private int rollCount;
  private int firstRollScore;
  private int secondRollScore;
  private int bonusScore;

  DefaultFrame(Frame previous) {
    this.previous = previous;
    rollCount = 0;
    firstRollScore = 0;
    secondRollScore = 0;
    bonusScore = 0;
  }

  @Override
  public void roll(int pins) {
    rollCount++;
    firstRollScore = rollCount == 1 ? pins : firstRollScore;
    secondRollScore = rollCount == 2 ? pins : secondRollScore;
    previous.addBonus(computeBonusForPrevious(pins));
    previous.previous().addBonus(computeBonusForPreviousPrevious(pins));
  }

  @Override
  public int score() {
    return firstRollScore + secondRollScore + bonusScore;
  }

  @Override
  public boolean isOpen() {
    return rollCount < 2 && !isStrike();
  }

  @Override
  public boolean isSpare() {
    return (firstRollScore + secondRollScore) == 10 && !isStrike();
  }

  @Override
  public boolean isStrike() {
    return firstRollScore == 10;
  }

  @Override
  public void addBonus(int bonus) {
    this.bonusScore += bonus;
  }

  @Override
  public Frame previous() {
    return previous;
  }

  private int computeBonusForPrevious(int pins) {
    return shouldAddBonusToPrevious() ? pins : 0;
  }

  private int computeBonusForPreviousPrevious(int pins) {
    return shouldAddBonusToPreviousPrevious() ? pins : 0;
  }

  private boolean shouldAddBonusToPrevious() {
    return previous.isStrike() || previous.isSpare() && rollCount == 1;
  }

  private boolean shouldAddBonusToPreviousPrevious() {
    return (
      previous.previous().isStrike() && previous.isStrike() && rollCount == 1
    );
  }
}
