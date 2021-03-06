# Tweening
A simple to use basic tween library.

### Features:
- value interpolation tweens (see LibGdx Interpolation classes)
- sequence tweens
- delay tweens
- callbacks for interaction with tween starts, -progress and -ends

### Examples:
Simulating day-night cycles.
```
long start = timer.getTimeValue();

SequenceTween dayNightTweens = new SequenceTween();
dayNightTweens.setLoop(true);

// dawn tween: increase value from 0.0 to 1.0 in timed steps of 0.1
FixedStepTween dawnTween = new FixedStepTween(Interpolation.linear, 0, 1, 0.1f, Timing.millisToNano(10));
dawnTween.setUpdateCallback((time, value) -> System.out.println(Timing.nanoToSecs(time - start) + " dawn: " + value));
dayNightTweens.addTween(dawnTween);

// day tween: simply wait for for 3 seconds
DelayTween dayTween = new DelayTween(Timing.secsToNano(3));
dayTween.setStartCallback((time, value) -> System.out.println(Timing.nanoToSecs(time - start) + " day"));
dayNightTweens.addTween(dayTween);

// dusk tween: decrease value from 1.0 to 0.0 in timed steps of 0.1
FixedStepTween duskTween = new FixedStepTween(Interpolation.linear, 1, 0, -0.1f, Timing.millisToNano(10));
duskTween.setUpdateCallback((time, value) -> System.out.println(Timing.nanoToSecs(time - start) + " dusk: " + value));
dayNightTweens.addTween(duskTween);

// night tween: simply wait for 3 seconds
DelayTween nightTween = new DelayTween(Timing.secsToNano(3));
nightTween.setStartCallback((time, value) -> System.out.println(Timing.nanoToSecs(time - start) + " night"));
dayNightTweens.addTween(nightTween);

while (!dayNightTweens.isDone()) {
    dayNightTweens.update(timer.getTimeValue());
}

```
