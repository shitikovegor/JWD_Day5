package com.shitikov.task5.service.impl;

import org.testng.annotations.DataProvider;

public class TextDataProvider {
    private static final String text = "Unicode also bring new possibilities. One is thats each Unicode. " +
            "При обработке выражения строк, найденные по шаблону внутри групп, сохраняются " +
            "в отдельной области памяти и получают номер, начиная с единицы.";

    @DataProvider(name = "changeCharacterData")
    public static Object[][] createChangeCharacterData() {
        String actual = text;
        String expected = "UnicTde also brinT new possTbilities. One is thatT each UnicTde. " +
                "При обраTотке выраTения строT, найдTнные по шаблTну внутTи групT, сохрTняются " +
                "в отдеTьной облаTти памяTи и полуTают номеT, начиTая с единTцы.";

        return new Object[][]{{actual, expected}};
    }

    @DataProvider(name = "changeSubsequentLetterData")
    public static Object[][] createSubsequentLetterData() {
        String actual = text;
        String expected = "Unicode also bring new possibilities. One is thats each Unicode. " +
                "При оброботке вырожения строк, найденные по шаблону внутри групп, сохроняются " +
                "в отдельной области памяти и получают номер, начиная с единицы.";

        return new Object[][]{{actual, expected}};
    }

    @DataProvider(name = "changeWordData")
    public static Object[][] createСhangeWordData() {
        String actual = text;
        String expected = "Unicode also Unic new possibilities. One is Unic each Unicode. " +
                "При обработке выражения Unic, найденные по шаблону внутри Unic, " +
                "сохраняются в отдельной области памяти и получают Unic, начиная с единицы.";

        return new Object[][]{{actual, expected}};
    }

    @DataProvider(name = "deleteNotLettersData")
    public static Object[][] createDeleteNotLettersData() {
        String actual = "Когда-то Ja!va was a you+ng... - One of% go to ' " +
                "12monkeys кто-то flat*/ * is@ won+der^&(ful!!! But ^&SD can't re$ad?";
        String expected = "Когда-то Ja va was a you ng      One of  go to     monkeys " +
                "кто-то flat     is  won der   ful    But   SD can't re ad";

        return new Object[][]{{actual, expected}};
    }

    @DataProvider(name = "deleteWordsData")
    public static Object[][] createDeleteWordData() {
        String actual = "I am trying to do is to process the source files before its compilation " +
                "if and only if this profile is active. My process will change the source files on the fly. " +
                "Если файл не открыт, будет вызов ошибки.";
        String expected = "I am trying to do is to process the source files before its compilation if and only " +
                "if  profile is active. My process  change the source files on the fly. Если  не открыт, будет вызов ошибки.";

        return new Object[][]{{actual, expected}};
    }
}
