package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.time.*;

class TimePointTest {
	@Test
	void testBetween() {
		TimePoint point1 = new TimePoint(10, TimeUnit.HOUR);
		TimePoint point2 = new TimePoint(3600 * 20, TimeUnit.SECOND);
		TimePoint point3 = TimeUnit.MINUTE.between(point1, point2);
		assertEquals(10 * 60, point3.getAmount());
		TimePoint point4 = TimeUnit.SECOND.between(point1, point2);
		assertEquals(10 * 3600, point4.getAmount());
		TimePoint point5 = TimeUnit.HOUR.between(point1, point2);
		assertEquals(10, point5.getAmount());
}

	@Test
	void convertTest() {
		TimePoint timePoint = new TimePoint(10, TimeUnit.HOUR);
		TimePoint pointActual = timePoint.convert(TimeUnit.SECOND);
		assertEquals(10 * 3600, pointActual.getAmount());
	}

	@Test
	void plusAdjusterTest() {
		TimePoint timePoint1 = new TimePoint(10, TimeUnit.HOUR);
		TimePoint timePoint2 = new TimePoint(60, TimeUnit.MINUTE);
		TimePoint actual = timePoint2.with(new PlusAdjuster(timePoint1));
		assertEquals(11 * 60, actual.getAmount());
		assertEquals(TimeUnit.MINUTE, actual.getTimeUnit());
	}

	@Test
	void timePointCompareToTest() {
		TimePoint timePoint1 = new TimePoint(10, TimeUnit.HOUR);
		TimePoint pointActual1 = new TimePoint(10 * 3600, TimeUnit.SECOND);
		assertEquals(0, timePoint1.compareTo(pointActual1));
		
		TimePoint timePoint2 = new TimePoint(10 + 1, TimeUnit.MINUTE);
		TimePoint pointActual2 = new TimePoint(10 * 60, TimeUnit.SECOND);
		assertEquals(1, timePoint2.compareTo(pointActual2));
		
		TimePoint timePoint3 = new TimePoint(10, TimeUnit.SECOND);
		TimePoint pointActual3 = new TimePoint(10, TimeUnit.HOUR);
		assertEquals(-1, timePoint3.compareTo(pointActual3));
	}

	@Test
	void timePointEqualsTest() {
		TimePoint timePoint1 = new TimePoint(10, TimeUnit.HOUR);
		TimePoint pointActual1 = new TimePoint(10 * 3600, TimeUnit.SECOND);
		assertTrue(timePoint1.equals(pointActual1));
		assertTrue(timePoint1.equals(timePoint1));
		
		TimePoint timePoint2 = new TimePoint(10 + 1, TimeUnit.MINUTE);
		TimePoint pointActual2 = new TimePoint(10 * 60, TimeUnit.SECOND);
		assertFalse(timePoint2.equals(pointActual2));
		
		TimePoint timePoint3 = new TimePoint(10, TimeUnit.SECOND);
		TimePoint pointActual3 = new TimePoint(10, TimeUnit.HOUR);
		assertFalse(timePoint3.equals(pointActual3));
		
		assertFalse(timePoint3.equals(5));
		assertFalse(timePoint3.equals(null));
	}

	@Test
	void futureProximityAdjusterTest() {
		TimePoint point1 = new TimePoint(2, TimeUnit.HOUR);
		TimePoint point2 = new TimePoint(2 * 60 + 1, TimeUnit.MINUTE);
		TimePoint point3 = new TimePoint(1, TimeUnit.HOUR);
		TimePoint point4 = new TimePoint(3 * 3600, TimeUnit.SECOND);
		TimePoint[] points = { point1, point2, point3, point4 };
		FutureProximityAdjuster timePoints = new FutureProximityAdjuster(points);
		
		TimePoint pointActual1 = timePoints.adjust(new TimePoint(125, TimeUnit.MINUTE));
		assertEquals(point4, pointActual1);
		TimePoint pointActual2 = timePoints.adjust(new TimePoint(5, TimeUnit.SECOND));
		assertEquals(point3, pointActual2);
		TimePoint pointActual3 = timePoints.adjust(new TimePoint(12, TimeUnit.HOUR));
		assertNull(pointActual3);
		TimePoint pointActual4 = timePoints.adjust(new TimePoint(3, TimeUnit.HOUR));
		assertNull(pointActual4);
	}

}
