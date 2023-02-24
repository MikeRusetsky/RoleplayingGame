public class Unit implements Fighter {
   private String name; // имя
   private int hp; //здоровье
   private int golds; //золото
   private int skill; //ловкость
   private int quality; //опыт
   private int power; //сила

    public Unit(String name, int hp, int golds, int skill, int quality, int power) {
        this.name = name;
        this.hp = hp;
        this.golds = golds;
        this.skill = skill;
        this.quality = quality;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getGolds() {
        return golds;
    }

    public void setGolds(int golds) {
        this.golds = golds;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public int getRandomSkill () {
        return (int) (Math.random() * 100);
    }

    @Override
    public int attack () {
        if (skill * 3 > getRandomSkill())
            return power;
        else return 0;
    }

    @Override
    public String toString () {
        return String.format("%s, Здоровье: %d",name,hp);
    }
}

