package gaia.config;

import gaia.GrimoireOfGaia;
import org.apache.commons.lang3.tuple.Pair;
import io.github.fabricators_of_create.porting_lib.config.ModConfigSpec;

public final class GaiaConfig {
    public static final class Client {
        public final ModConfigSpec.BooleanValue genderNeutral;
        public final ModConfigSpec.BooleanValue hideFoodEffectTooltips;
        public final ModConfigSpec.BooleanValue hideFoodXpTooltips;

        private Client(ModConfigSpec.Builder builder) {
            builder.comment("Client settings").push("client");
            genderNeutral = builder.comment("When enabled makes the mobs look more gender neutral").define("genderNeutral", false);
            hideFoodEffectTooltips = builder.comment("Hide food effect tooltips").define("hideFoodEffectTooltips", false);
            hideFoodXpTooltips = builder.comment("Hide food XP tooltips").define("hideFoodXpTooltips", false);
            builder.pop();
        }
    }

    public static final class Common {
        public final ModConfigSpec.BooleanValue disableInvisibility;
        public final ModConfigSpec.BooleanValue allPassiveMobsHostile;
        public final ModConfigSpec.BooleanValue passiveHostileMobs;
        public final ModConfigSpec.BooleanValue friendlyPersistence;

        public final ModConfigSpec.IntValue tier1maxHealth;
        public final ModConfigSpec.IntValue tier1attackDamage;
        public final ModConfigSpec.IntValue tier1baseDefense;
        public final ModConfigSpec.IntValue tier2maxHealth;
        public final ModConfigSpec.IntValue tier2attackDamage;
        public final ModConfigSpec.IntValue tier2baseDefense;
        public final ModConfigSpec.IntValue tier3maxHealth;
        public final ModConfigSpec.IntValue tier3attackDamage;
        public final ModConfigSpec.IntValue tier3baseDefense;

        public final ModConfigSpec.BooleanValue baseDamage;
        public final ModConfigSpec.BooleanValue shieldsBlockPiercing;
        public final ModConfigSpec.BooleanValue baseDamageArchers;

        public final ModConfigSpec.BooleanValue spawnDaysPassed;
        public final ModConfigSpec.IntValue spawnDaysSet;
        public final ModConfigSpec.BooleanValue spawnLevel3Rain;
        public final ModConfigSpec.BooleanValue disableYRestriction;
        public final ModConfigSpec.BooleanValue spawnWeather;

        private Common(ModConfigSpec.Builder builder) {
            builder.comment("General Settings").push("General");
            disableInvisibility = builder.define("disableInvisibility", false);
            allPassiveMobsHostile = builder.define("allPassiveMobsHostile", false);
            passiveHostileMobs = builder.define("passiveHostileMobs", false);
            friendlyPersistence = builder.define("friendlyPersistence", false);
            builder.pop();

            builder.comment("Attribute settings").push("Attributes");
            builder.comment("Tier 1").push("Tier1");
            tier1maxHealth = builder.defineInRange("tier1maxHealth", 40, 1, Integer.MAX_VALUE);
            tier1attackDamage = builder.defineInRange("tier1attackDamage", 4, 1, Integer.MAX_VALUE);
            tier1baseDefense = builder.defineInRange("tier1baseDefense", 2, 0, Integer.MAX_VALUE);
            builder.pop();

            builder.comment("Tier 2").push("Tier2");
            tier2maxHealth = builder.defineInRange("tier2maxHealth", 80, 1, Integer.MAX_VALUE);
            tier2attackDamage = builder.defineInRange("tier2attackDamage", 8, 1, Integer.MAX_VALUE);
            tier2baseDefense = builder.defineInRange("tier2baseDefense", 4, 0, Integer.MAX_VALUE);
            builder.pop();

            builder.comment("Tier 3").push("Tier3");
            tier3maxHealth = builder.defineInRange("tier3maxHealth", 160, 1, Integer.MAX_VALUE);
            tier3attackDamage = builder.defineInRange("tier3attackDamage", 12, 1, Integer.MAX_VALUE);
            tier3baseDefense = builder.defineInRange("tier3baseDefense", 8, 0, Integer.MAX_VALUE);
            builder.pop();
            builder.pop();

            builder.comment("Damage settings").push("Damage");
            baseDamage = builder.define("baseDamage", true);
            shieldsBlockPiercing = builder.define("shieldsBlockPiercing", true);
            baseDamageArchers = builder.define("baseDamageArchers", true);
            builder.pop();

            builder.comment("Spawn settings").push("Spawn");
            spawnDaysPassed = builder.define("spawnDaysPassed", false);
            spawnDaysSet = builder.defineInRange("spawnDaysSet", 3, 1, Integer.MAX_VALUE);
            spawnLevel3Rain = builder.define("spawnLevel3Rain", false);
            disableYRestriction = builder.define("disableYRestriction", false);
            spawnWeather = builder.define("spawnWeather", false);
            builder.pop();
        }
    }

    public static final ModConfigSpec clientSpec;
    public static final Client CLIENT;
    public static final ModConfigSpec commonSpec;
    public static final Common COMMON;

    static {
        Pair<Client, ModConfigSpec> clientPair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT = clientPair.getLeft();
        clientSpec = clientPair.getRight();

        Pair<Common, ModConfigSpec> commonPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON = commonPair.getLeft();
        commonSpec = commonPair.getRight();
    }

    private GaiaConfig() {}
}