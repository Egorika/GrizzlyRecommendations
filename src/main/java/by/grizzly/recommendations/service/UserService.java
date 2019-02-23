package by.grizzly.recommendations.service;

import by.grizzly.recommendations.entity.User;
import by.grizzly.recommendations.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findByTelegramId(Long telegramId) {
		return userRepository.findByTelegramId(telegramId);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}
}
