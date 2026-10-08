package gaia.config;

public final class GaiaConfig {
    public static final Client CLIENT = new Client();
    public static final Common COMMON = new Common();

    public static final class Value<T> {
        private T value;
        public Value(T value) { this.value = value; }
        public T get() { return value; }
        public void set(T value) { this.value = value; }
    }

    public static final class Client {
        public final Value<Boolean> genderNeutral = new Value<>(false);
        public final Value<Boolean> hideFoodEffectTooltips = new Value<>(false);
        public final Value<Boolean> hideFoodXpTooltips = new Value<>(false);
    }

    public static final class Common {
        public final Value<Boolean> disableInvisibility = new Value<>(false);
        public final Value<Boolean> allPassiveMobsHostile = new Value<>(false);
        public final Value<Boolean> passiveHostileMobs = new Value<>(false);
        public final Value<Boolean> friendlyPersistence = new Value<>(false);

        public final Value<Integer> tier1maxHealth = new Value<>(40);
        public final Value<Integer> tier1attackDamage = new Value<>(4);
        public final Value<Integer> tier1baseDefense = new Value<>(2);
        public final Value<Integer> tier2maxHealth = new Value<>(80);
        public final Value<Integer> tier2attackDamage = new Value<>(8);
        public final Value<Integer> tier2baseDefense = new Value<>(4);
        public final Value<Integer> tier3maxHealth = new Value<>(160);
        public final Value<Integer> tier3attackDamage = new Value<>(12);
        public final Value<Integer> tier3baseDefense = new Value<>(8);

        public final Value<Boolean> baseDamage = new Value<>(true);
        public final Value<Boolean> shieldsBlockPiercing = new Value<>(true);
        public final Value<Boolean> baseDamageArchers = new Value<>(true);

        public final Value<Boolean> spawnDaysPassed = new Value<>(false);
        public final Value<Integer> spawnDaysSet = new Value<>(3);
        public final Value<Boolean> spawnLevel3Rain = new Value<>(false);
        public final Value<Boolean> disableYRestriction = new Value<>(false);
        public final Value<Boolean> spawnWeather = new Value<>(false);
    }

    private GaiaConfig() {}
}
