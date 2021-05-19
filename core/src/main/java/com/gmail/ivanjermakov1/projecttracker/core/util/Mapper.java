package com.gmail.ivanjermakov1.projecttracker.core.util;

import com.gmail.ivanjermakov1.projecttracker.core.dto.ActivityDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.TaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.UserDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Activity;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.twilio.rest.chat.v1.service.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
	
	private static ModelMapper modelMapper;
	
	static {
		modelMapper = new ModelMapper();
		modelMapper
				.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(false)
				.setMatchingStrategy(MatchingStrategies.LOOSE)
				.setAmbiguityIgnored(true);
		
		modelMapper
				.createTypeMap(Task.class, TaskDto.class)
				.addMappings(mapper -> {
					mapper.map(s -> s.getParent().getId(), TaskDto::setParentTaskId);
					mapper.map(s -> s.getTaskInfo().getDescription(), TaskDto::setDescription);
					mapper.skip((TaskDto destination, TaskDto value) -> destination.getLastActivity().setTask(value));
				});
		
		modelMapper
				.createTypeMap(Activity.class, ActivityDto.class)
				.setPostConverter(context -> {
					context.getDestination().getTask().setLastActivity(null);
					return context.getDestination();
				});
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