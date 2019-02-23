package by.grizzly.recommendations.telegram.message;

import by.grizzly.recommendations.content.Content;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageKeyboard {

	public ReplyKeyboardMarkup start() {
		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		keyboardMarkup.setOneTimeKeyboard(false);
		keyboardMarkup.setResizeKeyboard(true);

		List<KeyboardRow> rows = new ArrayList<>();

		KeyboardRow leaveReccomend = new KeyboardRow();
		leaveReccomend.add(Content.btnLeaveRecommendation);
		rows.add(leaveReccomend);

		keyboardMarkup.setKeyboard(rows);

		return keyboardMarkup;
	}
}
