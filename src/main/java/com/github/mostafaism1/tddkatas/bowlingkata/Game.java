package com.github.mostafaism1.tddkatas.bowlingkata;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
  private List<Frame> frames;

  public Game() {
    frames = new ArrayList<>();
    frames.add(NullFrame.INSTANCE);
    for (int i = 0; i < 9; i++) {
      frames.add(new DefaultFrame(frames.get(i)));
    }
    frames.add(new TenthFrame(frames.get(8)));
  }

  public void roll(int pins) {
    Frame currentFrame = findFirstOpenFrame()
      .orElseThrow(() -> new IllegalStateException("Game has ended."));
    currentFrame.roll(pins);
  }

  public int score() {
    return frames.stream().mapToInt(Frame::score).sum();
  }

  private Optional<Frame> findFirstOpenFrame() {
    return frames.stream().filter(Frame::isOpen).findFirst();
  }
}
