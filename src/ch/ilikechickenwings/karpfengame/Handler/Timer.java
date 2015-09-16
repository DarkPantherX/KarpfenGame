package ch.ilikechickenwings.karpfengame.Handler;

public class Timer {

	private long startMilli;
	private int millis;

	public Timer(int millis) {
		setMillis(millis);
		setStartMilli(System.currentTimeMillis());
	}

	public double getProgress() {
		return (((double) millis) - (System.currentTimeMillis() - startMilli)) / (double) millis;
	}

	public boolean isReady() {
		long milli = (long) getMillis();
		return getStartMilli() + milli < System.currentTimeMillis();
	}

	private long getStartMilli() {
		return startMilli;
	}

	private void setStartMilli(long startMilli) {
		this.startMilli = startMilli;
	}

	private int getMillis() {
		return millis;
	}

	private void setMillis(int millis) {
		this.millis = millis;
	}

}
