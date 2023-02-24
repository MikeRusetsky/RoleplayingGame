public class Battle {

    public void fight(Unit player, Unit monster, GameModul.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("Ход" + turn + " ");

                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, player, fightCallback);
                } else {
                    isFightEnded = makeHit(player, monster, fightCallback);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public boolean makeHit(Unit defender, Unit attacker, GameModul.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHp = defender.getHp() - hit;

        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d здоровья!", defender.getName(), defenderHp));
        } else {
            System.out.println(String.format("%s промахнулся", attacker.getName()));
        }
        if (defenderHp <= 0 && defender instanceof Player) {
            System.out.println("Вы погибли в бою...");
            fightCallback.fightLost();
            return true;
        } else if (defenderHp <= 0) {
            System.out.println(String.format("Враг повержен, вы забираете %d золота и получаете %d опыта",
                    defender.getGolds(), defender.getQuality()));
            attacker.setQuality(attacker.getQuality() + defender.getQuality());
            attacker.setGolds(attacker.getGolds() + defender.getGolds());
            fightCallback.fightWin();
            return true;

        } else {
            defender.setHp(defenderHp);
            return false;
        }
    }
}


