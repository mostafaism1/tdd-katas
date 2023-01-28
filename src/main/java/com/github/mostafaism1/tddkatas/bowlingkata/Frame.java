package com.github.mostafaism1.tddkatas.bowlingkata;

interface Frame {
  void roll(int pins);
  int score();
  boolean isOpen();
  boolean isSpare();
  boolean isStrike();
  void addBonus(int bonus);
  Frame previous();
}
