package com.gmail.ivanjermakov1.projecttracker.core.util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
	
	public static <T> List<T> sortList(List<? extends T> list, Comparator<T> comparator) {
		return list
				.stream()
				.sorted(comparator)
				.collect(Collectors.toList());
	}
	
}
