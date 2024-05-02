package telran.time;

public enum TimeUnit {
HOUR(3600), MINUTE(60), SECOND(1);
	int value;
	private TimeUnit(int value) { // по умолчанию он private
		//выглядит как конструктор, но значения определенные
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public TimePoint between(TimePoint point1, TimePoint point2) {
//		my HW		
//		TimeUnit common = point2.getTimeUnit();
//		TimePoint point1AfterConvert = point1.convert(common);
//		int resultAmount = point2.getAmount() - point1AfterConvert.getAmount();
//		TimePoint result = new TimePoint(resultAmount, common);
//		return result.convert(this);
		
		int diffAmount = point2.convert(this).getAmount() -
				point1.convert(this).getAmount();
		
		return new TimePoint(diffAmount, this);
		
	}
	
}
