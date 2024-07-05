package ru.Amet_Kurtumerov.tgBot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Amet_Kurtumerov.tgBot.entity.Category;
import ru.Amet_Kurtumerov.tgBot.entity.Client;
import ru.Amet_Kurtumerov.tgBot.entity.Product;
import ru.Amet_Kurtumerov.tgBot.repository.CategoryRepository;
import ru.Amet_Kurtumerov.tgBot.repository.ClientRepository;
import ru.Amet_Kurtumerov.tgBot.repository.ProductRepository;

import java.math.BigDecimal;

@SpringBootTest
public class FillingTests {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void createTwoClients() {
        Client client1 = new Client();
        client1.setAddress("address1");
        client1.setFullName("fullName1");
        client1.setExternalId(1L);
        client1.setPhoneNumber("+7978262626");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setAddress("address2");
        client2.setFullName("fullName2");
        client2.setExternalId(2L);
        client2.setPhoneNumber("+7978262626");
        clientRepository.save(client2);
    }

    @Test
    void createData() {
//        Основные категории
        Category pizza = new Category();
        pizza.setName("Пицца");
        pizza.setParentCategory(null);
        categoryRepository.save(pizza);

        Category rolls = new Category();
        rolls.setName("Роллы");
        rolls.setParentCategory(null);
        categoryRepository.save(rolls);

        Category burgers = new Category();
        burgers.setName("Бургеры");
        burgers.setParentCategory(null);
        categoryRepository.save(burgers);

        Category drinks = new Category();
        drinks.setName("Напитки");
        drinks.setParentCategory(null);
        categoryRepository.save(drinks);

        // Виды пицц

        Product margarita = new Product();
        margarita.setName("Маргарита");
        margarita.setDescription("Это маргарита");
        margarita.setCategory(pizza);
        margarita.setPrice(BigDecimal.valueOf(500));
        productRepository.save(margarita);

        Product peperoni = new Product();
        peperoni.setName("Пеперони");
        peperoni.setDescription("Это Пеперони");
        peperoni.setCategory(pizza);
        peperoni.setPrice(BigDecimal.valueOf(550));
        productRepository.save(peperoni);

        Product meaty = new Product();
        meaty.setName("Мясная");
        meaty.setDescription("Это мясная");
        meaty.setCategory(pizza);
        meaty.setPrice(BigDecimal.valueOf(600));
        productRepository.save(meaty);

        //Виды классических роллов

        Category classicRolls = new Category();
        classicRolls.setName("Классические роллы");
        classicRolls.setParentCategory(rolls);
        categoryRepository.save(classicRolls);

        Product philadelphia = new Product();
        philadelphia.setName("Филадельфия");
        philadelphia.setDescription("Это филадельфия");
        philadelphia.setCategory(classicRolls);
        philadelphia.setPrice(BigDecimal.valueOf(600));
        productRepository.save(philadelphia);

        Product california = new Product();
        california.setName("Калифорния");
        california.setDescription("Это калифорния");
        california.setCategory(classicRolls);
        california.setPrice(BigDecimal.valueOf(550));
        productRepository.save(california);

        Product caesar = new Product();
        caesar.setName("Цезарь");
        caesar.setDescription("Цезарь");
        caesar.setCategory(classicRolls);
        caesar.setPrice(BigDecimal.valueOf(500));
        productRepository.save(caesar);

        //Виды запеченных роллов

        Category bakedRolls = new Category();
        bakedRolls.setName("Запеченые роллы");
        bakedRolls.setParentCategory(rolls);
        categoryRepository.save(bakedRolls);

        Product anida = new Product();
        anida.setName("Анида");
        anida.setDescription("Это анида");
        anida.setCategory(bakedRolls);
        anida.setPrice(BigDecimal.valueOf(300));
        productRepository.save(anida);

        Product abi = new Product();
        abi.setName("Эби");
        abi.setDescription("Это эби");
        abi.setCategory(bakedRolls);
        abi.setPrice(BigDecimal.valueOf(400));
        productRepository.save(abi);

        Product syaku = new Product();
        syaku.setName("Сяку");
        syaku.setDescription("Это сяку");
        syaku.setCategory(bakedRolls);
        syaku.setPrice(BigDecimal.valueOf(350));
        productRepository.save(syaku);

        //Виды сладких роллов

        Category sweetRolls = new Category();
        sweetRolls.setName("Сладкие роллы");
        sweetRolls.setParentCategory(rolls);
        categoryRepository.save(sweetRolls);

        Product banana = new Product();
        banana.setName("Банана");
        banana.setDescription("Это банана");
        banana.setCategory(sweetRolls);
        banana.setPrice(BigDecimal.valueOf(150));
        productRepository.save(banana);

        Product zebra = new Product();
        zebra.setName("Зебра");
        zebra.setDescription("Это зебра");
        zebra.setCategory(sweetRolls);
        zebra.setPrice(BigDecimal.valueOf(100));
        productRepository.save(zebra);

        Product tuttiFrutti = new Product();
        tuttiFrutti.setName("Тутти фрутти");
        tuttiFrutti.setDescription("Это тутти фрутти");
        tuttiFrutti.setCategory(sweetRolls);
        tuttiFrutti.setPrice(BigDecimal.valueOf(200));
        productRepository.save(tuttiFrutti);

        //Виды сетов роллов

        Category sets = new Category();
        sets.setName("Сеты роллов");
        sets.setParentCategory(rolls);
        categoryRepository.save(sets);

        Product ninjaHot = new Product();
        ninjaHot.setName("Ниндзя хот");
        ninjaHot.setDescription("Это ниндзя хот");
        ninjaHot.setCategory(sets);
        ninjaHot.setPrice(BigDecimal.valueOf(1500));
        productRepository.save(ninjaHot);

        Product tempura = new Product();
        tempura.setName("Темпура");
        tempura.setDescription("Это темпура");
        tempura.setCategory(sets);
        tempura.setPrice(BigDecimal.valueOf(1000));
        productRepository.save(tempura);

        Product classic = new Product();
        classic.setName("Классический");
        classic.setDescription("Это классический");
        classic.setCategory(sets);
        classic.setPrice(BigDecimal.valueOf(2000));
        productRepository.save(classic);

        //Виды классических бургеров

        Category classicBurgers = new Category();
        classicBurgers.setName("Классические бургеры");
        classicBurgers.setParentCategory(burgers);
        categoryRepository.save(classicBurgers);

        Product hamburger = new Product();
        hamburger.setName("Гамбургер");
        hamburger.setDescription("Это гамбургер");
        hamburger.setCategory(classicBurgers);
        hamburger.setPrice(BigDecimal.valueOf(100));
        productRepository.save(hamburger);

        Product cheeseburger = new Product();
        cheeseburger.setName("Чизбургер");
        cheeseburger.setDescription("Это чизбургер");
        cheeseburger.setCategory(classicBurgers);
        cheeseburger.setPrice(BigDecimal.valueOf(150));
        productRepository.save(cheeseburger);

        Product chickenburger = new Product();
        chickenburger.setName("Чикенбургер");
        chickenburger.setDescription("Это чикенбургер");
        chickenburger.setCategory(classicBurgers);
        chickenburger.setPrice(BigDecimal.valueOf(100));
        productRepository.save(chickenburger);

        //Виды острых бургеров

        Category spicyBurgers = new Category();
        spicyBurgers.setName("Острые бургеры");
        spicyBurgers.setParentCategory(burgers);
        categoryRepository.save(spicyBurgers);

        Product spicy = new Product();
        spicy.setName("Спайси");
        spicy.setDescription("Это спайси");
        spicy.setCategory(spicyBurgers);
        spicy.setPrice(BigDecimal.valueOf(250));
        productRepository.save(spicy);

        Product diablo = new Product();
        diablo.setName("Диабло");
        diablo.setDescription("Это диабло");
        diablo.setCategory(spicyBurgers);
        diablo.setPrice(BigDecimal.valueOf(300));
        productRepository.save(diablo);

        Product mexican = new Product();
        mexican.setName("Мексиканский");
        mexican.setDescription("Это мексиканский");
        mexican.setCategory(spicyBurgers);
        mexican.setPrice(BigDecimal.valueOf(270));
        productRepository.save(mexican);

        //Виды газированных напитков

        Category carbonatedDrinks = new Category();
        carbonatedDrinks.setName("Газированные напитки");
        carbonatedDrinks.setParentCategory(drinks);
        categoryRepository.save(carbonatedDrinks);

        Product cola = new Product();
        cola.setName("Кола");
        cola.setDescription("Это кола");
        cola.setCategory(carbonatedDrinks);
        cola.setPrice(BigDecimal.valueOf(100));
        productRepository.save(cola);

        Product sprite = new Product();
        sprite.setName("Спрайт");
        sprite.setDescription("Это спрайт");
        sprite.setCategory(carbonatedDrinks);
        sprite.setPrice(BigDecimal.valueOf(100));
        productRepository.save(sprite);

        Product fanta = new Product();
        fanta.setName("Фанта");
        fanta.setDescription("Это фанта");
        fanta.setCategory(carbonatedDrinks);
        fanta.setPrice(BigDecimal.valueOf(100));
        productRepository.save(fanta);

        //Виды энергетических напитков

        Category energyDrinks = new Category();
        energyDrinks.setName("Энергетические напитки");
        energyDrinks.setParentCategory(drinks);
        categoryRepository.save(energyDrinks);

        Product burn = new Product();
        burn.setName("Берн");
        burn.setDescription("Это берн");
        burn.setCategory(energyDrinks);
        burn.setPrice(BigDecimal.valueOf(100));
        productRepository.save(burn);

        Product redbull = new Product();
        redbull.setName("Редбулл");
        redbull.setDescription("Это редбулл");
        redbull.setCategory(energyDrinks);
        redbull.setPrice(BigDecimal.valueOf(150));
        productRepository.save(redbull);

        Product flash = new Product();
        flash.setName("Флеш");
        flash.setDescription("Это флеш");
        flash.setCategory(energyDrinks);
        flash.setPrice(BigDecimal.valueOf(50));
        productRepository.save(flash);

        //Виды соков

        Category juices = new Category();
        juices.setName("Соки");
        juices.setParentCategory(drinks);
        categoryRepository.save(juices);

        Product orange = new Product();
        orange.setName("Апельсиновый");
        orange.setDescription("Это апельсиновый сок");
        orange.setCategory(juices);
        orange.setPrice(BigDecimal.valueOf(100));
        productRepository.save(orange);

        Product apple = new Product();
        apple.setName("Яблочный");
        apple.setDescription("Это яблочный сок");
        apple.setCategory(juices);
        apple.setPrice(BigDecimal.valueOf(100));
        productRepository.save(apple);

        Product tomato = new Product();
        tomato.setName("Томатный");
        tomato.setDescription("Это томатный сок");
        tomato.setCategory(juices);
        tomato.setPrice(BigDecimal.valueOf(100));
        productRepository.save(tomato);

        //Другие напитки

        Category otherDrinks = new Category();
        otherDrinks.setName("Другие напитки");
        otherDrinks.setParentCategory(drinks);
        categoryRepository.save(otherDrinks);

        Product coffee = new Product();
        coffee.setName("Кофе");
        coffee.setDescription("Это кофе");
        coffee.setCategory(otherDrinks);
        coffee.setPrice(BigDecimal.valueOf(100));
        productRepository.save(coffee);

        Product mojito = new Product();
        mojito.setName("Мохито");
        mojito.setDescription("Это мохито");
        mojito.setCategory(otherDrinks);
        mojito.setPrice(BigDecimal.valueOf(100));
        productRepository.save(mojito);

        Product tea = new Product();
        tea.setName("Чай");
        tea.setDescription("Это чай");
        tea.setCategory(otherDrinks);
        tea.setPrice(BigDecimal.valueOf(100));
        productRepository.save(tea);

    }
}
