package by.grizzly.recommendations.telegram.message;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Component
public class MessageResponse {

	public SendMessage sendMessage(Long telegramId, String text, ReplyKeyboardMarkup keyboard) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setParseMode("Markdown");

		sendMessage.setChatId(telegramId);
		if (keyboard != null) {
			sendMessage.setReplyMarkup(keyboard);
		}
		sendMessage.setText(text);

		return sendMessage;
	}

	public SendPhoto sendPhoto(Long telegramId, String caption, String photo, ReplyKeyboardMarkup keyboard) {
		SendPhoto sendPhoto = new SendPhoto();
		sendPhoto.setParseMode("Markdown");

		sendPhoto.setChatId(telegramId);
		if (keyboard != null) {
			sendPhoto.setReplyMarkup(keyboard);
		}
		sendPhoto.setCaption(caption);
		sendPhoto.setPhoto(photo);

		return sendPhoto;
	}
}
