package telran.time;

public class TimePoint implements Comparable<TimePoint> {
	int amount;
	TimeUnit timeUnit;

	public TimePoint(int amount, TimeUnit timeUnit) {
		this.amount = amount;
		this.timeUnit = timeUnit;
	}

	public int getAmount() {
		return amount;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public TimePoint convert(TimeUnit unit) {
		// returns new TimePoint with a given TimeUnit
		int resultAmount = amount * timeUnit.getValue() / unit.getValue();
		return new TimePoint(resultAmount, unit);
	}

	public TimePoint with(TimePointAdjuster adjuster) {
		// returns new TimePoint based on any TimePointAdjuster
		return adjuster.adjust(this);
	}

	@Override
	public int compareTo(TimePoint o) {
		return Integer.compare(this.convert(TimeUnit.SECOND).getAmount(), o.convert(TimeUnit.SECOND).getAmount());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimePoint other = (TimePoint) obj;
		return this.convert(TimeUnit.SECOND).getAmount() == other.convert(TimeUnit.SECOND).getAmount();
	}
	
}
