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
import ru.Amet_Kurtumerov.tgBot.entity.OrderProduct;
import ru.Amet_Kurtumerov.tgBot.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelegramBotConnection {
    private final EntitiesService entitiesService;
    private TelegramBot bot;
    private List<OrderProduct> cart;  // List to store cart items

    public TelegramBotConnection(EntitiesService entitiesService) {
        this.entitiesService = entitiesService;
        this.cart = new ArrayList<>();
    }

    @PostConstruct
    public void createConnection() {
        bot = new TelegramBot("6774151902:AAFDyXb_RsXFGl37C-Kbs_9KOry9e-70yVg");
        bot.setUpdatesListener(updates -> {
            updates.forEach(this::processUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void processUpdate(Update update) {
        if (update.callbackQuery() != null) {
            String callbackData = update.callbackQuery().data();
            Long chatId = update.callbackQuery().message().chat().id();

            if (callbackData.startsWith("category_")) {
                Long categoryId = Long.parseLong(callbackData.substring("category_".length()));
                Category selectedCategory = entitiesService.findCategoryById(categoryId);
                if (selectedCategory != null) {
                    sendSubcategories(chatId, selectedCategory);
                } else {
                    SendMessage sendMessage = new SendMessage(chatId, "Категория не найдена.");
                    bot.execute(sendMessage);
                }
            } else if (callbackData.startsWith("subcategory_")) {
                Long subcategoryId = Long.parseLong(callbackData.substring("subcategory_".length()));
                Category selectedSubcategory = entitiesService.findCategoryById(subcategoryId);
                if (selectedSubcategory != null) {
                    sendSubcategoriesOrProducts(chatId, selectedSubcategory);
                } else {
                    SendMessage sendMessage = new SendMessage(chatId, "Подкатегория не найдена.");
                    bot.execute(sendMessage);
                }
            } else if (callbackData.startsWith("add_to_cart_")) {
                Long productId = Long.parseLong(callbackData.substring("add_to_cart_".length()));
                Product product = entitiesService.findProductById(productId);
                if (product != null) {
                    addToCart(chatId, product);
                    SendMessage sendMessage = new SendMessage(chatId, "Товар добавлен в корзину.");
                    bot.execute(sendMessage);
                } else {
                    SendMessage sendMessage = new SendMessage(chatId, "Продукт не найден.");
                    bot.execute(sendMessage);
                }
            } else if (callbackData.equals("order")) {
                handleCheckout(chatId);
            } else if (callbackData.equals("back_to_menu")) {
                List<Category> rootCategories = entitiesService.getRootCategories();
                InlineKeyboardMarkup markup = createMenuMarkup(rootCategories);
                markup.addRow(new InlineKeyboardButton("Оформить заказ").callbackData("order"));
                SendMessage sendMessage = new SendMessage(chatId, "Выберите категорию или оформите заказ:");
                sendMessage.replyMarkup(markup);
                bot.execute(sendMessage);
            }
        } else if (update.message() != null && update.message().text() != null) {
            String messageText = update.message().text().trim();
            Long chatId = update.message().chat().id();

            if (messageText.equals("/start")) {
                List<Category> rootCategories = entitiesService.getRootCategories();
                InlineKeyboardMarkup markup = createMenuMarkup(rootCategories);
                markup.addRow(new InlineKeyboardButton("Оформить заказ").callbackData("order"));
                SendMessage sendMessage = new SendMessage(chatId, "Выберите категорию или оформите заказ:");
                sendMessage.replyMarkup(markup);
                bot.execute(sendMessage);
            } else {
                // Other logic for text messages handling
            }
        }
    }

    private InlineKeyboardMarkup createMenuMarkup(List<Category> categories) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();

        for (Category category : categories) {
            InlineKeyboardButton button = new InlineKeyboardButton(category.getName()).callbackData("category_" + category.getId());
            buttonsRow.add(button);
        }

        markup.addRow(buttonsRow.toArray(new InlineKeyboardButton[0]));

        return markup;
    }

    private void sendSubcategories(Long chatId, Category category) {
        List<Category> subcategories = entitiesService.findCategoriesByParent(category);
        if (!subcategories.isEmpty()) {
            InlineKeyboardMarkup markup = createSubcategoriesMenu(subcategories);
            markup.addRow(new InlineKeyboardButton("В начальное меню").callbackData("back_to_menu"));
            SendMessage sendMessage = new SendMessage(chatId, "Выберите подкатегорию:");
            sendMessage.replyMarkup(markup);
            bot.execute(sendMessage);
        } else {
            SendMessage sendMessage = new SendMessage(chatId, "В данной категории нет подкатегорий.");
            bot.execute(sendMessage);
        }
    }

    private InlineKeyboardMarkup createSubcategoriesMenu(List<Category> subcategories) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();

        for (Category subcategory : subcategories) {
            InlineKeyboardButton button = new InlineKeyboardButton(subcategory.getName()).callbackData("subcategory_" + subcategory.getId());
            buttonsRow.add(button);
        }

        markup.addRow(buttonsRow.toArray(new InlineKeyboardButton[0]));

        return markup;
    }

    private void sendSubcategoriesOrProducts(Long chatId, Category category) {
        List<Category> subcategories = entitiesService.findCategoriesByParent(category);
        if (!subcategories.isEmpty()) {
            InlineKeyboardMarkup markup = createSubcategoriesMenu(subcategories);
            markup.addRow(new InlineKeyboardButton("В начальное меню").callbackData("back_to_menu"));
            SendMessage sendMessage = new SendMessage(chatId, "Выберите подкатегорию:");
            sendMessage.replyMarkup(markup);
            bot.execute(sendMessage);
        } else {
            List<Product> products = entitiesService.getProductsByCategoryId(category.getId());
            if (!products.isEmpty()) {
                InlineKeyboardMarkup markup = createProductsMarkup(products);
                markup.addRow(new InlineKeyboardButton("В начальное меню").callbackData("back_to_menu"));
                SendMessage sendMessage = new SendMessage(chatId, "Выберите продукт:");
                sendMessage.replyMarkup(markup);
                bot.execute(sendMessage);
            } else {
                SendMessage sendMessage = new SendMessage(chatId, "В данной категории нет продуктов.");
                bot.execute(sendMessage);
            }
        }
    }

    private InlineKeyboardMarkup createProductsMarkup(List<Product> products) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();

        for (Product product : products) {
            InlineKeyboardButton button = new InlineKeyboardButton(product.getName() + " - " + product.getPrice())
                    .callbackData("add_to_cart_" + product.getId());
            buttonsRow.add(button);
        }

        markup.addRow(buttonsRow.toArray(new InlineKeyboardButton[0]));

        return markup;
    }

    private void addToCart(Long chatId, Product product) {
        // Check if the product is already in the cart
        boolean found = false;
        for (OrderProduct orderProduct : cart) {
            if (orderProduct.getProduct().getId().equals(product.getId())) {
                orderProduct.setCountProduct(orderProduct.getCountProduct() + 1); // Increment count
                found = true;
                break;
            }
        }

        if (!found) {
            // Add new OrderProduct to cart
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setCountProduct(1); // Set initial count to 1
            cart.add(orderProduct);
        }
    }

    private void handleCheckout(Long chatId) {
        if (cart.isEmpty()) {
            SendMessage sendMessage = new SendMessage(chatId, "Корзина пуста. Добавьте товары для оформления заказа.");
            bot.execute(sendMessage);
        } else {
            BigDecimal totalCost = cart.stream()
                    .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCountProduct())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            String messageText = cart.stream()
                    .map(item -> String.format("%s - Количество: %d - Цена за шт: %s - Общая стоимость: %s",
                            item.getProduct().getName(), item.getCountProduct(), item.getProduct().getPrice(),
                            item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCountProduct()))))
                    .collect(Collectors.joining("\n"));

            messageText += String.format("\n\nОбщая стоимость всех товаров: %s", totalCost);

            SendMessage sendMessage = new SendMessage(chatId, messageText);
            bot.execute(sendMessage);

            // Optionally, clear the cart after checkout
            cart.clear();
        }
    }
}
