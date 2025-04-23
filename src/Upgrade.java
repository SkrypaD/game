public enum Upgrade {
    HEALTH_BOOST(0, "health Boost") {
        @Override
        public void apply(Player player) {
            player.increaseHealth(10);
        }
    },
    DAMAGE_BOOST(1, "damage Boost") {
        @Override
        public void apply(Player player) {
            player.increaseDamage(5);
        }
    },
    SPEED_BOOST(2, "speed Boost") {
        @Override
        public void apply(Player player) {
            player.increasePlayerSpeed(1);
        }
    },
    ATTACK_SPEED_BOOST(3, "attack speed boost") {
        @Override
        public void apply(Player player) {
            player.increaseAttackSpeed(1);
        }
    },
    ROCKET_SPEED_BOOST(4, "rocket speed boost") {
        @Override
        public void apply(Player player) {
            player.increaseRocketSpeed(1);
        }
    };

    private final int id;
    private final String description;

    Upgrade(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public abstract void apply(Player player);

    public static Upgrade fromId(int id) {
        for (Upgrade type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid upgrade ID: " + id);
    }

    @Override
    public String toString() {
        return description;
    }
}
