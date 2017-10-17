package de.voodoosoft.tween;

/**
 * Tween that does nothing.
 */
public class NopTween implements Tween {
	@Override
	public void reset() {
	}

	@Override
	public void update(long time) {
	}

	@Override
	public boolean isDone() {
		return true;
	}

	@Override
	public boolean isUpdated() {
		return false;
	}
}
