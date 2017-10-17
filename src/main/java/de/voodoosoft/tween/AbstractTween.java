package de.voodoosoft.tween;

public abstract class AbstractTween implements Tween {
	private TweenCallback startCallback;
	private TweenCallback updateCallback;
	private TweenCallback endCallback;

	public void setStartCallback(TweenCallback startCallback) {
		this.startCallback = startCallback;
	}

	public void setUpdateCallback(TweenCallback updateCallback) {
		this.updateCallback = updateCallback;
	}

	public void setEndCallback(TweenCallback endCallback) {
		this.endCallback = endCallback;
	}

	protected void onStart(long time, float value) {
		if (startCallback != null) {
			startCallback.onCallback(time, value);
		}
	}

	protected void onUpdate(long time, float value) {
		if (updateCallback != null) {
			updateCallback.onCallback(time, value);
		}
	}

	protected void onEnd(long time, float value) {
		if (endCallback != null) {
			endCallback.onCallback(time, value);
		}
	}
}
