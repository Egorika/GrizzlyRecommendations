package by.grizzly.recommendations.telegram.message;

import by.grizzly.recommendations.content.Content;
import by.grizzly.recommendations.entity.User;
import by.grizzly.recommendations.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageHandler {

	private UserService userService;
	private MessageResponse response;
	private MessageKeyboard keyboard;

	public MessageHandler(UserService userService, MessageResponse response, MessageKeyboard keyboard) {
		this.userService = userService;
		this.response = response;
		this.keyboard = keyboard;
	}

	public List<PartialBotApiMethod> start(Message message, User user) {
		List<PartialBotApiMethod> execute = new ArrayList<>();
		String text = message.getText();

		if (text.equals(Content.btnLeaveRecommendation)) {

		} else {
			execute.add(response.sendMessage(message.getChatId(), Content.msgHello, keyboard.start()));
		}

		return execute;
	}
}
