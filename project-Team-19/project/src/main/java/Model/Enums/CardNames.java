package Model.Enums;

public enum CardNames {

    TRAP_HOLE("Model.Card.Monster.NormalMonster"),
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

    BATTEL_OX(""),
    AXE_RAIDER(""),
    YOMI_SHIP(""),
    HORN_IMP(""),
    SILVER_FANG(""),
    SUIJIN(""),
    FIREYAROU(""),
    CURTAIN_OF_THE_DARD_ONES(""),
    FERAL_IMP(""),
    DARK_MAGICIAN(""),
    WATTKID(""),
    BABY_DRAGON(""),
    HERO_OF_THE_EAST(""),
    BATTLE_WARRIOR(""),
    CRAWLING_DRAGON(""),
    FLAME_MANIPULATOR(""),
    BLUE_EYES_WHITE_DRAGON(""),
    CRAB_TURTLE(""),
    SKULL_GUARDIAN(""),
    SLOT_MACHINE(""),
    HANIWA(""),
    MAN_EATER_BUG(""),
    GATE_GUARDIAN(""),
    SCANNER(""),
    BITRON(""),
    MARSHMALLON(""),
    BEAST_KING_BARBAROS(""),
    TEXTCHANGER(""),
    LEOTRON(""),
    THE_CALCULATOR(""),
    ALEXANDRITE_DRAGON(""),
    MIRAGE_DRAGON(""),
    HERALD_OF_CREATION(""),
    EXPLODER_DRAGON(""),
    WARRIOR_DAI_GREPHER(""),
    DARK_BLADE(""),
    WATTAILDRAGON(""),
    TERRATIGER__THE_EMPOWERED_WARRIOR(""),
    THE_TRICKY(""),
    SPIRAL_SERPENT("");

    String className;

    CardNames(String className) {
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }


}
