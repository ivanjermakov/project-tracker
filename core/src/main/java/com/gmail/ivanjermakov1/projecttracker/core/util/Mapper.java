package com.gmail.ivanjermakov1.projecttracker.core.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
	
	private static ModelMapper modelMapper;
	
	static {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(false)
				.setMatchingStrategy(MatchingStrategies.LOOSE);
	}
	
	public static <S, T> T map(S source, Class<T> targetClass) {
		return modelMapper.map(source, targetClass);
	}
	
	public static <S, T> List<T> mapAll(Collection<? extends S> sourceList, Class<T> targetClass) {
		return sourceList.stream()
				.map(e -> map(e, targetClass))
				.collect(Collectors.toList());
	}
	
}