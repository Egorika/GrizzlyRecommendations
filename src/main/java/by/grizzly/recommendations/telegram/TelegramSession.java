package by.grizzly.recommendations.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotSession;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Slf4j
public class TelegramSession {

	private Bot bot;
	private BotSession session;

	public TelegramSession(Bot bot) {
		this.bot = bot;
	}

	@PostConstruct
	public void init(){
		TelegramBotsApi api = new TelegramBotsApi();
		try {
			this.session = api.registerBot(bot);
		} catch (TelegramApiRequestException e) {
			log.error(e.getMessage());
		}
	}

	@PreDestroy
	public void destroy(){
		session.stop();
	}
}
