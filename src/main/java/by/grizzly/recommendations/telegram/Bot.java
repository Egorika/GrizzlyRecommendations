package by.grizzly.recommendations.telegram;

import by.grizzly.recommendations.entity.State;
import by.grizzly.recommendations.entity.User;
import by.grizzly.recommendations.service.UserService;
import by.grizzly.recommendations.telegram.message.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {

	@Value("${botToken}")
	private String botToken;

	@Value("${botUsername}")
	private String botUsername;

	private UserService userService;
	private MessageHandler handler;

	public Bot(UserService userService, MessageHandler handler) {
		this.userService = userService;
		this.handler = handler;
	}


	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage()) {
			handleMessage(update.getMessage());
		}
	}

	private void handleMessage(Message message) {
		List<PartialBotApiMethod> execute = new ArrayList<>();

		User user = userService.findByTelegramId(message.getChatId());
		if (user == null) {
			user = new User();
			user.setTelegramId(message.getChatId());
			user.setState(State.NEW);

			userService.save(user);
		}

		switch (user.getState()) {
			case NEW:
				execute.addAll(handler.start(message, user));
				break;
		}

		executeAll(execute);
	}

	public void executeOne(PartialBotApiMethod request) {
		try {
			if (request instanceof SendPhoto) {
				execute((SendPhoto) request);
			} else if (request instanceof SendDocument) {
				execute((SendDocument) request);
			} else if (request instanceof SendVideo) {
				execute((SendVideo) request);
			} else if (request instanceof EditMessageMedia) {
				execute((EditMessageMedia) request);
			} else if (request instanceof EditMessageCaption) {
				execute((EditMessageCaption) request);
			} else if (request instanceof EditMessageText) {
				execute((EditMessageText) request);
			} else {
				execute((BotApiMethod) request);
			}
		} catch (TelegramApiException e) {
			log.error(e.getMessage());
		}

	}

	public void executeAll(List<PartialBotApiMethod> executeList) {
		log.info("to execute: " + executeList.size());
		for (PartialBotApiMethod request : executeList) {
			executeOne(request);
		}
	}

	@Override
	public String getBotUsername() {
		return botUsername;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}
}
