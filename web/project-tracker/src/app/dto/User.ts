import {UserInfo} from './UserInfo';
import {UserCredentials} from './UserCredentials';

export class User {
	id: number;
	joined: Date;
	userInfo: UserInfo;
	userCredentials: UserCredentials;
}
