package utilities;

import java.time.Duration;
import java.time.Instant;

public class Stopwatch {
	private Instant start;
	private Instant stop;


	public Stopwatch() {
		super();
		resetStart();
	}
	
	public double timeElapsed() {
		return Duration.between(start, Instant.now()).toMillis();
	}
	
	public void resetStart() {
		setStart(Instant.now());
	}
	public Instant getStart() {
		return start;
	}

	public void setStart(Instant start) {
		this.start = start;
	}
	
	public void setStop() {
		this.stop = Instant.now();
	}
	
	public double getTotalTime() {
		return Duration.between(start, stop).toMillis();
	}
	

}
