package model.Enums;

public enum CardNames {

    TRAP_HOLE("model.Card.Monster.NormalMonster"),
    MIRROR_FORCE(""),
    MAGIC_CYLINDER(""),
    MIND_CRUSH(""),
    TORRENTIAL_TRIBUTE(""),
    TIME_SEAL(""),
    NEGATE_ATTACK(""),
    SOLEMN_WARNING(""),
    MAGIC_JAMMER(""),
    CALL_OF_THE_HAUNTED(""),
    VANITYS_EMPTINESS(""),
    WALL_OF_REVEALING_LIGHT(""),
    MONSTER_REBORN(""),
    TERRAFORMING(""),
    POT_OF_GREED(""),
    RAIGEKI(""),
    CHANGE_OF_HEART(""),
    SWORDS_OF_REVEALING_LIGHT(""),
    HARPIES_FEATHER_DUSTER(""),
    DARK_HOLE(""),
    SUPPLY_SQUAD(""),
    SPELL_ABSORPTION(""),
    MESSENGER_OF_PEACE(""),
    TWIN_TWISTERS(""),
    MYSTICAL_SPACE_TYPHOON(""),
    RING_OF_DEFENCE(""),
    YAMI(""),
    FOREST(""),
    CLOSED_FOREST(""),
    UMIIRUKA(""),
    SWORD_OF_DARK_DESTRUCTION(""),
    BLACK_PENDANT(""),
    UNITED_WE_STAND(""),
    MAGNUM_SHIELD(""),
    ADVANCED_RITUAL_ART(""),

    BATTEL_OX("model.Card.Monsters.NormalMonster"),
    AXE_RAIDER("model.Card.Monsters.NormalMonster"),
    YOMI_SHIP("model.Card.Monsters.YomiShip"),
    HORN_IMP("model.Card.Monsters.NormalMonster"),
    SILVER_FANG("model.Card.Monsters.NormalMonster"),
    SUIJIN("model.Card.Monsters.Suijin"),
    FIREYAROU("model.Card.Monsters.NormalMonster"),
    CURTAIN_OF_THE_DARD_ONES("model.Card.Monsters.NormalMonster"),
    FERAL_IMP("model.Card.Monsters.NormalMonster"),
    DARK_MAGICIAN("model.Card.Monsters.NormalMonster"),
    WATTKID("model.Card.Monsters.NormalMonster"),
    BABY_DRAGON("model.Card.Monsters.NormalMonster"),
    HERO_OF_THE_EAST("model.Card.Monsters.NormalMonster"),
    BATTLE_WARRIOR("model.Card.Monsters.NormalMonster"),
    CRAWLING_DRAGON("model.Card.Monsters.NormalMonster"),
    FLAME_MANIPULATOR("model.Card.Monsters.NormalMonster"),
    BLUE_EYES_WHITE_DRAGON("model.Card.Monsters.NormalMonster"),
    CRAB_TURTLE("model.Card.Monsters.CrabTurtle"),
    SKULL_GUARDIAN("model.Card.Monsters.SkullGuardian"),
    SLOT_MACHINE("model.Card.Monsters.NormalMonster"),
    HANIWA("model.Card.Monsters.NormalMonster"),
    MAN_EATER_BUG("model.Card.Monsters.ManEaterBug"),
    GATE_GUARDIAN("model.Card.Monsters.GateGuardian"),
    SCANNERMonster("model.Card.Monsters.ScannerMonster"),
    BITRON("model.Card.Monsters.NormalMonster"),
    MARSHMALLON("model.Card.Monsters.Marshmallon"),
    BEAST_KING_BARBAROS("model.Card.Monsters.BeastKingBarbaros"),
    TEXTCHANGER("model.Card.Monsters.Textchanger"),
    LEOTRON("model.Card.Monsters.NormalMonster"),
    THE_CALCULATOR("model.Card.Monsters.TheCalculator"),
    ALEXANDRITE_DRAGON("model.Card.Monsters.NormalMonster"),
    MIRAGE_DRAGON("model.Card.Monsters.MirageDragon"),
    HERALD_OF_CREATION("model.Card.Monsters.HeraldOfCreation"),
    EXPLODER_DRAGON("model.Card.Monsters.ExploderDragon"),
    WARRIOR_DAI_GREPHER("model.Card.Monsters.NormalMonster"),
    DARK_BLADE("model.Card.Monsters.NormalMonster"),
    WATTAILDRAGON("model.Card.Monsters.NormalMonster"),
    TERRATIGER__THE_EMPOWERED_WARRIOR("model.Card.Monsters.TerratigerTheEmpoweredWarrior"),
    THE_TRICKY("model.Card.Monsters.TheTricky"),
    SPIRAL_SERPENT("model.Card.Monsters.NormalMonster");

    String className;

    CardNames(String className) {
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

}
