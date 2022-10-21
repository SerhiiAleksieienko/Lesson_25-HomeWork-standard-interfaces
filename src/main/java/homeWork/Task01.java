package homeWork;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;


import static homeWork.AmountCardsInDeck.amountBigDeck;
import static homeWork.AmountCardsInDeck.amountLitlDeck;



public class Task01 {
    public static void main(String[] args) {
        System.out.println("***************************************");           // работа с колодой 36 карт
        Deck deck36 = new Deck(amountLitlDeck, "На 36"); // создание колоды
        System.out.println("Состав созданной колоды на 36 карт:");
        System.out.println(deck36);                             //вывод созданной колоды

        System.out.println("_______________________________________");
        System.out.println("Состав перемешанной колоды на 36 карт:");
        deck36.shuffle();                                       // перемешивание колоды
        System.out.println(deck36);                             // вывод перемешанной колоды
        System.out.println("_______________________________________");
        System.out.println("Состав отсортированной колоды на 36 карт:");
        deck36.sortDesk();                                     // сортировка колоды
        System.out.println(deck36);                             // вывод отсортированной колоды

        System.out.println();
        System.out.println("**************************************");    // работа с колодой 54 карты
        Deck deck54 = new Deck(amountBigDeck, "На 54");    // создание колоды
        System.out.println("Состав созданной колоды на 54 карты:");
        System.out.println(deck54);                             //вывод созданной колоды

        System.out.println("_______________________________________");
        System.out.println("Состав перемешанной колоды на 54 карты:");
        deck54.shuffle();                                       // перемешивание колоды
        System.out.println(deck54);                             // вывод перемешанной колоды
        System.out.println("______________________________________");
        System.out.println("Проверка сравнения карт на перемешанной колоде на 54 карты:");

        System.out.println("Карта1: " + deck54.getCards()[5]);
        System.out.println("Карта2: " + deck54.getCards()[10]);
        int n = deck54.getCards()[5].compareCards(deck54.getCards()[10]); //результат сравнение карт
        if (n > 0) {
            System.out.println("Карта 1 сильнее");
        } else {
            System.out.println("Карта 2 сильнее");
        }


        System.out.println("__________________________________________");
        System.out.println("Состав отсортированной колоды на 54 карты:");
        deck54.sortDesk();                                     // сортировка колоды
        System.out.println(deck54);                             // вывод отсортированной колоды

        System.out.println("__________________________________________");
        System.out.println("Проверка вывода через перечисление");

        for (Object card: deck54){
            System.out.println("card = " + card);
        }

    }
}

enum Suite {

    HEARTS("Hearts", 1),
    TREF("Tref", 2),
    PIKI("Piki", 3),
    BUBI("Bubi", 4);

    private final String suite;
    private final int numberSuite;

    Suite(String suite, int numberSuite) {
        this.numberSuite = numberSuite;
        this.suite = suite;
    }


    @Override
    public String toString() {
        return suite;
    }

    public String getSuite() {
        return suite;
    }

    public int getNumberSuite() {
        return numberSuite;
    }
}

enum CardName {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    VALET("Walet", 11),
    DAMA("Dama", 12),
    KOROL("Korol", 13),
    TUZ("Tuz", 14),
    JOKER("Joker", 100);


    private final String cardName; // наименование карты
    private final int rang; //ранг карты, который по умолчанию имеет каждая карта

    CardName(String cardName, int rang) {
        this.cardName = cardName;
        this.rang = rang;
    }

    public int getRang() {
        return rang;
    }

    public String getCardName() {
        return cardName;
    }
}

class Card implements Comparable {       //создание каждой карты
    private Suite suite;                        // масть карты
    private CardName cardName;                  // наиманование карты
    private final int sortNumber;       // номер для сортировке колоды в правильном порядке

    private int rangCard;

    public Card(Suite suite, CardName cardName, int sortNumber) {
        this.suite = suite;
        this.cardName = cardName;
        this.sortNumber = sortNumber;
        this.rangCard = cardName.getRang();
    }


    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < (8 - cardName.getCardName().length()); i++) { //добавляем пробелы в зависимости от длины имени карты, для выравнивания
            str = str + " ";
        }
        return "Card { " +
                cardName.getCardName() + str +
                suite.getSuite() +
                " }" + '\n';
    }


    public int compareCards(Object o) {             //сравнение карт по рангу карты. Сначала  масти, потом по рангу. Но пока особого смысла не имеет. Потом в привязке к игре переопределить.
        if (this.suite.getNumberSuite() == ((Card) o).suite.getNumberSuite()) { //проверка сопоставимости масти
            return this.getRangCard() - ((Card) o).getRangCard(); //если масть одна, то сравниваем по рангу
        } else
            return -5;                           //если масти разные сразу отрицательное значение. Типа для игры в дурака без козыря.
    }

    @Override
    public int compareTo(Object o) {            // переопределенный стандартный метод интерфейса Comparable для задания логики сортировки стандартным методом Arrays.sort
        return sortNumber - ((Card) o).sortNumber;
    }

    public int getRangCard() {
        return rangCard;
    }
}

class AmountCardsInDeck {                       // количество карт в колоде
    public static final int amountLitlDeck = 36; // количество карт в малой колоде карт
    public static final int amountBigDeck = 54; // количество карт в большой колоде карт

}

class Deck implements Iterable {                      //колода карт
    private String name;
    private int amount;
    private Card[] cards;

    Deck(int amount, String name) {                 //инициализация колоды
        this.name = name;
        CardName[] cardNames = CardName.values();   // создаем массив со всеми возможными наименованиями карт
        Suite[] suits = Suite.values();             // создаем массив со всеми значениями мастей
        if (amount == amountLitlDeck) {
            this.amount = amountLitlDeck;           // инициализация количества карт
            cards = new Card[amountLitlDeck];       // создаем массив карт с количеством 36 - малая колода
            for (int i = 0; i < cards.length; i++) {

                int n = cards.length / suits.length; // количество карт одной масти
                int j = i / n;                       // это индекс масти в колоде
                int k = i % n + 4;                     // это индекс наименования карты
                cards[i] = new Card(suits[j], cardNames[k], i);
            }


        } else if (amount == amountBigDeck) {
            this.amount = amountBigDeck;                    // инициализация количества карт
            cards = new Card[amountBigDeck];                // создаем массив карт с количеством 54 - малая колода
            for (int i = 0; i < cards.length; i++) {
                if (i < 52) {
                    int n = (cards.length - 2) / suits.length; // количество карт одной масти, 2 джокера
                    int j = i / n;                          // это индекс масти
                    int k = i % n;                         // это индекс наименования карты
                    cards[i] = new Card(suits[j], cardNames[k], i);
                } else {
                    cards[i] = new Card(suits[0], cardNames[cardNames.length - 1], i);
                }
            }

        } else {
            System.err.print("В колоде может быть только 36 или 54 карт");
        }
    }

    public Card[] getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Deck <" + name + ">" + '\n' +
                "amount=" + amount + '\n' +
                "cards=" + '\n' +
                Arrays.toString(cards) + '}';
    }

    void shuffle() {                        //перемешивание колоды с использованием класса CardComparatorShuffle
        CardComparatorShuffle cardComparator = new CardComparatorShuffle();
        Arrays.sort(cards, cardComparator);
    }

    void sortDesk() {                       // Сортировка колоды карт стандартным метором .sort
        Arrays.sort(cards);
    }


    @Override
    public Iterator iterator() {
        return new DeckIterator(cards);
    }
}

class CardComparatorShuffle implements Comparator { // компаратор для перемешивания - возвращает случайный результат, сравнение не проводит
    @Override
    public int compare(Object o1, Object o2) {
        Random rnd = new Random();
        int m = rnd.nextInt(-5, 5);
        return m;
    }
}

class DeckIterator implements Iterator { //создаем класс имплементирующий итератор
    private final Card[] cards; // массив карт такой же как и в Desk
    private int index;

    public DeckIterator(Card[] cards) {
        this.cards = cards;
        index = -1;
    }

    @Override
    public boolean hasNext() {
        return ++index < cards.length;
    }

    @Override
    public Object next() {
        return cards[index];
    }
}





