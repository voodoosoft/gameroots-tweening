package de.voodoosoft.tween;

import com.badlogic.gdx.math.Interpolation;
import org.junit.Test;



public class TweenTest {
	private SystemTimer timer;

	public TweenTest() {
		timer = new SystemTimer();
	}

	@Test
	public void testDayNight() {
		long start = timer.getTimeValue();

		SequenceTween dayNightTweens = new SequenceTween();
		dayNightTweens.setLoop(true);

		// dawn tween: increase value from 0.0 to 1.0 in timed steps of 0.1
		long updateInterval = Timing.millisToNano(10);
		FixedStepTween dawnTween = new FixedStepTween(0, 1, 0.1f, updateInterval);
		dawnTween.setUpdateCallback((time, value) -> System.out.println(Timing.nanoToSecs(time - start) + " dawn: " + value));
		dayNightTweens.addTween(dawnTween);

		// day tween: simply wait for for 3 seconds
		DelayTween dayTween = new DelayTween(Timing.secsToNano(3));
		dayTween.setStartCallback((time, value) -> System.out.println(Timing.nanoToSecs(time - start) + " day"));
		dayNightTweens.addTween(dayTween);

		// dusk tween: decrease value from 1.0 to 0.0 in timed steps of 0.1
		FixedStepTween duskTween = new FixedStepTween(1,0, -0.1f, updateInterval);
		duskTween.setUpdateCallback((time, value) -> System.out.println(Timing.nanoToSecs(time - start) + " dusk: " + value));
		dayNightTweens.addTween(duskTween);

		// night tween: simply wait for 3 seconds
		DelayTween nightTween = new DelayTween(Timing.secsToNano(3));
		nightTween.setStartCallback((time, value) -> System.out.println(Timing.nanoToSecs(time - start) + " night"));
		dayNightTweens.addTween(nightTween);

		// run for 5 seconds
		while (!dayNightTweens.isDone() && (timer.getTimeValue() - start < Timing.secsToNano(5))) {
			dayNightTweens.update(timer.getTimeValue());
		}
	}

	@Test
	public void testSine() {
		long updateInterval = Timing.millisToNano(1);

		FixedStepTween sineUpTween = new FixedStepTween(0, 1, 0.1f, updateInterval);
		sineUpTween.setUpdateCallback((time, value) -> System.out.println(tweenValueToString(value)));
		FixedStepTween sineDownTween = new FixedStepTween(1, 0, -0.1f, updateInterval);
		sineDownTween.setUpdateCallback((time, value) -> System.out.println(tweenValueToString(value)));

		SequenceTween sequence = new SequenceTween();
		sequence.addTween(sineUpTween);
		sequence.addTween(sineDownTween);

		while (!sequence.isDone()) {
			sequence.update(timer.getTimeValue());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private String tweenValueToString(float tweenValue) {
		int value = (int) (tweenValue * 10);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < value-1; i++) {
			sb.append(" ");
		}
		sb.append(".");
		sb.append("          ");

		return sb.substring(0, 10);
	}
}
