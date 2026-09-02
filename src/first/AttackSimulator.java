package first;

import java.util.concurrent.ThreadLocalRandom;

public class AttackSimulator {

    private static final int MIN_DAMAGE = 147;
    private static final int MAX_DAMAGE = 159;

    private static final double CRIT_MULTIPLIER = 2.00;

    // PRD для базового крит-шанса 35%
    private static final double PRD_C = 0.171;

    public static void main(String[] args) throws InterruptedException {

        int attacksWithoutCrit = 0;

        while (true) {
            int damage = MIN_DAMAGE;
            // int damage = ThreadLocalRandom.current()
            //                               .nextInt(MIN_DAMAGE, MAX_DAMAGE + 1);

            attacksWithoutCrit++;

            double critChance = Math.min(
                    attacksWithoutCrit * PRD_C,
                    1.0
            );

            boolean critical =
                    ThreadLocalRandom.current().nextDouble() < critChance;

            if (critical) {

                double critDamage = damage * CRIT_MULTIPLIER;

                System.out.printf(
                        "CRIT!  %.0f damage | chance: %.1f%%%n",

                        critDamage,
                        critChance * 100
                );

                attacksWithoutCrit = 0;

            }
            else {

                System.out.printf(
                        "HIT    %d damage | chance: %.1f%%%n",
                        damage,
                        critChance * 100
                );
            }

            Thread.sleep(210);
        }
    }
}