package de.voodoosoft.tween;

/**
 * Simple tween thats waits for a given time.
 */
public class DelayTween extends AbstractTween {
	private float value;
	private boolean done;
	private boolean updated;
	private long delay;
	private long delayed;
	private long lastUpdate;

	public DelayTween(long delay) {
		this.delay = delay;
	}

	public DelayTween(long delay, float value) {
		this.delay = delay;
		this.value = value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public long getDelayed() {
		return delayed;
	}

	@Override
	public void reset() {
		lastUpdate = 0;
		delayed = 0;
		done = false;
		updated = false;
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public boolean isUpdated() {
		return updated;
	}

	@Override
	public void update(long time) {
		updated = false;
		if (done) {
			return;
		}

		if (lastUpdate == 0) {
			lastUpdate = time;
			delayed = 0;
			updated = true;
			onStart(time, value);
			return;
		}

		onUpdate(time, value);

		long dt = time - lastUpdate;
		delayed += dt;
		lastUpdate = time;
		if (delayed >= delay) {
			done = true;
			onEnd(time, value);
		}
	}
}
