package app.Model.While;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class While {
    private String variable; //переменная
    private String sign;     //знак в условии ("<",">","=")
    private static final String PARAGRAPH_SPLIT_REGEX = "(?m)(?=^)";

    String inputString = " while (i<5) {\n" +
            "    a;\n" +
            "    o=2;\n" +
            "    i=3;\n" +
            "}";


    protected void analizer() {
        String[] strings = inputString.split(PARAGRAPH_SPLIT_REGEX);
        if (strings[0].matches("(\\s*)while(\\s*)[(](\\s*)(.*)(\\s*)[)](\\s*)[{](\\s*)") == false) {
            System.out.println("Некорректное начало цикла!");
            System.exit(0);
        }
        if (strings[strings.length - 1].matches("(\\s*)}(\\s*)") == false) {
            System.out.println("Некорректный конец тела цикла!");
            System.exit(0);
        }


        try {
            String cycleWhile = strings[0];
            Pattern pattern = Pattern.compile("[(](.*)[)]");
            Matcher matcher = pattern.matcher(cycleWhile);
            while (matcher.find()) {
                String[] mass = {"<", ">", "="};
                System.out.println("Условие: " + cycleWhile.substring(matcher.start(), matcher.end()));
                String condition = cycleWhile.substring(matcher.start(), matcher.end());
                for (int i = 0; i < mass.length; i++) {
                    if (condition.contains(mass[i])) {
                        sign = mass[i];
                        System.out.println("Оператор в условии: " + sign);
                    }
                }
                String conditionVariable = condition.substring(condition.indexOf("") + 1, condition.indexOf(sign));
                if (!conditionVariable.equals("")) {
                    variable = conditionVariable;
                    System.out.println("Переменная в условии: " + variable);
                } else {
                    System.out.println("Переменная в условии отсутствует!");
                    System.exit(0);
                }

                String digitCondition = condition.substring(condition.indexOf(sign) + 1, condition.indexOf(")"));
                if (digitCondition.isEmpty()) {
                    System.out.println("В Вашем условии нет идентификатора!");
                    System.exit(0);
                }
                try {
                    int finalDigitCondition = Integer.parseInt(digitCondition);
                    if (finalDigitCondition < 1000 && finalDigitCondition > -1000)
                        System.out.println("Идентификатор в условии в нужном интервале");
                } catch (NumberFormatException e) {
                    System.out.println("Идентификатор в условии должен быть целым числом");
                    System.exit(0);
                }
            }
            for (int i = 1; i < strings.length - 1; i++) {
                String value = strings[i];
                Pattern commonPattern = Pattern.compile("(.*)(\\s*)[;]");
                Matcher commonMatcher = commonPattern.matcher(value);
                Pattern patternID = Pattern.compile("(.*)=(.*)(\\s*)[;]");
                Matcher matcherID = patternID.matcher(value);

                if (commonMatcher.find()) {
                    while (matcherID.find()) {
                        System.out.println("Строка с идентификатором в теле цикла: " + value.substring(commonMatcher.start(), commonMatcher.end()));
                        String id = value.substring(value.indexOf("=") + 1, value.indexOf(";"));

                        String[] words = value.split("(\\s*)");
                        for (String word : words) {
                            if (!word.equals("=") && !word.equals(";") && !word.equals(id)) {
                                if (word.equals(variable)) {
                                    System.out.println("Переменная: " + variable);
                                    System.out.println("Цикл выполнился");    //Тут мы и узнаём выполнился ли цикл или нет
                                    System.exit(0);
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Некорректное тело цикла!");
                    System.exit(0);
                }
            }
        }catch (NullPointerException e){
            System.out.println("Идентификатор в условии не существует!");
        }
    }
}
