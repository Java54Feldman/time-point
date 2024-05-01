package telran.time;

import java.util.Comparator;

import telran.util.Arrays;

public class FutureProximityAdjuster implements TimePointAdjuster {
	TimePoint[] timePoints; //sorted

	public FutureProximityAdjuster(TimePoint[] points) {
		TimePoint[] arrayTemp = Arrays.copy(points);
		Arrays.bubbleSort(arrayTemp, Comparator.naturalOrder());
		this.timePoints = arrayTemp;
	}

	@Override
	public TimePoint adjust(TimePoint point) {
		// ближайшее событие по шкале справа
		TimePoint result;
		int i = 0;
		boolean flag = true;
		while ((i < timePoints.length) && flag) {
			if (timePoints[i].compareTo(point) > 0) flag = false;
			i++;
		}
		result = flag && (i == timePoints.length) ? null : timePoints[i - 1];
		return result;
	}

}
