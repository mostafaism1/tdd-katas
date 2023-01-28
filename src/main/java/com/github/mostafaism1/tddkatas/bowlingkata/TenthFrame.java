package com.github.mostafaism1.tddkatas.bowlingkata;

import java.util.ArrayList;
import java.util.List;

public class TenthFrame implements Frame {
  private int rollCount;
  private List<Frame> frames;

  public TenthFrame(Frame previous) {
    rollCount = 0;
    frames = new ArrayList<>();
    frames.add(new DefaultFrame(previous));
    for (int i = 0; i < 2; i++) {
      frames.add(new DefaultFrame(frames.get(i)));
    }
  }

  @Override
  public void roll(int pins) {
    rollCount++;
    Frame currentFrame = findFirstOpenFrame();
    currentFrame.roll(pins);
  }

  @Override
  public int score() {
    return mainFrame().score();
  }

  @Override
  public boolean isOpen() {
    return mainFrame().isOpen() || (isSpare() || isStrike()) && rollCount < 3;
  }

  @Override
  public boolean isSpare() {
    return mainFrame().isSpare();
  }

  @Override
  public boolean isStrike() {
    return mainFrame().isStrike();
  }

  @Override
  public void addBonus(int bonus) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Frame previous() {
    return mainFrame().previous();
  }

  private Frame mainFrame() {
    return frames.get(0);
  }

  private Frame findFirstOpenFrame() {
    return frames.stream().filter(Frame::isOpen).findFirst().get();
  }
}
