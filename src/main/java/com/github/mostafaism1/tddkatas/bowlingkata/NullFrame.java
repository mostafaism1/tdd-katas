package com.github.mostafaism1.tddkatas.bowlingkata;

enum NullFrame implements Frame {
  INSTANCE;

  @Override
  public void roll(int pins) {}

  @Override
  public int score() {
    return 0;
  }

  @Override
  public boolean isOpen() {
    return false;
  }

  @Override
  public boolean isSpare() {
    return false;
  }

  @Override
  public boolean isStrike() {
    return false;
  }

  @Override
  public void addBonus(int bonus) {}

  @Override
  public Frame previous() {
    return INSTANCE;
  }
}
