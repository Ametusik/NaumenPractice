package ru.Amet_Kurtumerov.tgBot.services;

import com.pengrad.telegrambot.TelegramBot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.Amet_Kurtumerov.tgBot.entity.Category;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBotConnection {
    private class TelegramUpdatesListener implements UpdatesListener {
        @Override
        public int process(List<Update> updates) {
            updates.forEach(this::processUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }

        private void processUpdate(Update update) {
            if (update.callbackQuery() != null) {
                // логика по работе с callback
            } else if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();
                Long chatId = update.message().chat().id();

                if (messageText.equals("/start")) {
                    // Получаем список категорий с ParentCategory = null
                    List<Category> rootCategories = entitiesService.getRootCategories();

                    // Создаем клавиатуру для кнопок категорий
                    List<InlineKeyboardButton> categoryButtonsRow = new ArrayList<>();
                    for (Category category : rootCategories) {
                        InlineKeyboardButton button = new InlineKeyboardButton(category.getName()).callbackData("category_" + category.getId());
                        categoryButtonsRow.add(button);
                    }

                    // Создаем отдельную строку для кнопки "Оформить заказ"
                    List<InlineKeyboardButton> orderButtonRow = new ArrayList<>();
                    InlineKeyboardButton orderButton = new InlineKeyboardButton("Оформить заказ").callbackData("order");
                    orderButtonRow.add(orderButton);

                    // Создаем разметку клавиатуры
                    InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                    markup.addRow(categoryButtonsRow.toArray(new InlineKeyboardButton[0]));
                    markup.addRow(orderButtonRow.toArray(new InlineKeyboardButton[0]));

                    // Отправляем сообщение с клавиатурой пользователю
                    SendMessage sendMessage = new SendMessage(chatId, "Выберите категорию или оформите заказ:");
                    sendMessage.replyMarkup(markup);
                    bot.execute(sendMessage);
                } else {
                    // Другая логика по обработке текстовых сообщений
                }
            }
        }
    }

    private final EntitiesService entitiesService;
    private TelegramBot bot;

    public TelegramBotConnection(EntitiesService entitiesService) {
        this.entitiesService = entitiesService;
    }

    @PostConstruct
    public void createConnection() {
        bot = new TelegramBot("6774151902:AAFDyXb_RsXFGl37C-Kbs_9KOry9e-70yVg");
        bot.setUpdatesListener(new TelegramUpdatesListener());
    }
}