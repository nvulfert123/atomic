import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger count3 = new AtomicInteger(0);
    private static AtomicInteger count4 = new AtomicInteger(0);
    private static AtomicInteger count5 = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException{
        Random random = new Random();
        String[] texts = new String[100000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        //сгенерированное слово является палиндромом, т. е. читается одинаково как слева направо, так и справа налево, например, abba;
        new Thread(()->{
                for (String text : texts) {
                    if (isPalindrome(text)) {
                        int length = text.length();
                        switch (length) {
                            case 3:
                                count3.incrementAndGet();
                                break;
                            case 4:
                                count4.incrementAndGet();
                                break;
                            case 5:
                                count3.incrementAndGet();
                                break;
                        }
                    }
                }
        }).start();

        //сгенерированное слово состоит из одной и той же буквы, например, aaa;
        new Thread(()->{
            for (String text : texts) {
                if (isSameLetter(text)) {
                    int length = text.length();
                    switch (length) {
                        case 3:
                            count3.incrementAndGet();
                            break;
                        case 4:
                            count4.incrementAndGet();
                            break;
                        case 5:
                            count5.incrementAndGet();
                            break;
                    }
                }
            }
        }).start();

        //буквы в слове идут по возрастанию: сначала все a (при наличии), затем все b (при наличии), затем все c и т. д. Например, aaccc.
        new Thread(()->{
            for (String text : texts) {
                if (isIncreasingOrder(text)) {
                    int length = text.length();
                    switch (length) {
                        case 3:
                            count3.incrementAndGet();
                            break;
                        case 4:
                            count4.incrementAndGet();
                            break;
                        case 5:
                            count5.incrementAndGet();
                            break;
                    }
                }
            }

        }).start();

        System.out.println("Красивых слов с длиной 3: " + count3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + count4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + count5.get() + " шт");
    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();

    }

    private static boolean isPalindrome(String text) {
        int length = text.length();
        for (int i = 0; i < length / 2; i++) {
            if (text.charAt(i) != text.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSameLetter(String text) {
        char firstLetter = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != firstLetter) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIncreasingOrder(String text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) > text.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

}