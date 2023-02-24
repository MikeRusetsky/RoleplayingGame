import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameModul {

    private static BufferedReader br;
    private static Unit unit;
    private static Battle battle;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();

        System.out.println("Введите имя персонажа :");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void command(String string) throws IOException {
        if (unit == null) {
            unit = new Unit(string, 100, 50, 50, 0, 10);
            System.out.println(String.format("Приветсвутем нового героя %s", unit.getName()));
            printNavigation();
        }

        switch (string) {
            case "1": {
                System.out.println("Торговец еще не пришел");
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
        }
        //Снова ждем команды от пользователя
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }

    private static void commitFight() {
        battle.fight(unit, createMonster(), new FightCallback() {

            @Override
            public void fightWin() {
                System.out.println(String.format("%s Победил, теперь у него %d опыта и %d золота, " +
                                "а также осталось %d едениц здоровья", unit.getName(), unit.getQuality(),
                        unit.getGolds(), unit.getHp()));
                System.out.println("Желаете продолжить поход или вернуться в город? Да / Нет");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void fightLost() {
            }
        });
    }


    private static Unit createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0)
            return new Goblin("Гоблин", 50, 10, 10, 100, 20);
        else return new Skeleton("Скелет", 25, 20, 20, 100, 10);
    }
    interface FightCallback {
        void fightWin();
        void fightLost();
    }
}
