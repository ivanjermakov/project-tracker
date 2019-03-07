package com.gmail.ivanjermakov1.projecttracker.core.util;

import com.gmail.ivanjermakov1.projecttracker.core.dto.UserCredentialsDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.UserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.UserInfoDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;

public class Mapper {
	
	public static UserDto map(User user) {
		return new UserDto(
				user.getId(),
				user.getJoined(),
				new UserInfoDto(
						user.getUserInfo().getName(),
						user.getUserInfo().getBio(),
						user.getUserInfo().getUrl(),
						user.getUserInfo().getCompany(),
						user.getUserInfo().getLocation()
				),
				new UserCredentialsDto(
						user.getUserCredentials().getLogin()
				)
		);
	}
	
}