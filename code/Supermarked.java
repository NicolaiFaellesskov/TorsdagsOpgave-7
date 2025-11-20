package supermarked.robot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Supermarked {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileIO fileIO = new FileIO();

        List<Vare> normaleVarer = fileIO.readFile("data/varer.csv");
        if (normaleVarer.isEmpty()) {
            System.out.println("Ingen varer blev indlæst fra filen (data/varer.csv)!");
            return;
        }

        Map<Long, Vare> vareMap = new LinkedHashMap<>();
        for (Vare v : normaleVarer) {
            vareMap.put(v.getId(), v);
        }

        List<Vare> tilbudVarer = fileIO.readFile("data/tilbud.csv");
        if (tilbudVarer.isEmpty()) {
            System.out.println("Ingen varer blev indlæst fra filen (data/varer.csv)!");
            return;
        }

        for (Vare t : tilbudVarer) {
            long id = t.getId();
            if (vareMap.containsKey(id)) {
                vareMap.get(id).setPrice(t.getPrice());
            } else {
                vareMap.put(id, t);// Hvis nu, der er et nyt id nummer. altså varenummer, så oppretter den nye varer. med tilbud objektet.
            }
        }

        Robot robot = new Robot();

        Collection<Vare> kurv = robot.fyldIKurv(normaleVarer);
        System.out.println("Antal: " + " Name                                      " + " Pris:  ");

        Map<Long, Integer> tæller = new LinkedHashMap<>();

        for (Vare v : kurv) {
            Long id = v.getId();
            tæller.put(id, tæller.getOrDefault(id, 0) + 1);
        }

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalSaved = BigDecimal.ZERO;
        BigDecimal TotalWeight = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : tæller.entrySet()) {
            Long id = entry.getKey();
            Integer numberInt = entry.getValue();
            BigDecimal number = BigDecimal.valueOf(numberInt);//konvetere til BigDecimal, Da Map skal bruge BigDecimal til Multiply.

            Vare vare = vareMap.get(id); // Laver vare til et Vareobjektet fra Map

            BigDecimal numberTimesPrice = vare.getPrice().multiply(number);

            TotalWeight = TotalWeight.add(vare.getQuantity());


            BigDecimal numberOfDeal = BigDecimal.ZERO;


            total = total.add(numberTimesPrice);


            if (vare.getOldPrice() != null) {
                numberOfDeal = (vare.getOldPrice().subtract(vare.getPrice()).multiply(number));
                BigDecimal numberTimesOldPrice = vare.getOldPrice().multiply(number);

                if (numberInt > 1) {
                    System.out.println("        " + vare.getName() + "  |  " + vare.getOldPrice());
                }
                System.out.println(number + "   x   " + vare.getName() + "  |  " + numberTimesOldPrice);
                if (numberInt > 1) {
                    System.out.println("Tilbud  " + vare.getName() + "  |  " + vare.getPrice());
                }
                System.out.println("Tilbud  " + vare.getName() + "  |   " + numberTimesPrice);

                System.out.println(("                                                        -" + numberOfDeal + " sparet"));
                System.out.println(" ");
            }
            if (vare.getOldPrice() == null) {
                if (numberInt > 1) {
                    System.out.println("        " + vare.getName() + "  |  " + vare.getPrice());
                }

                System.out.println(number + "   x   " + vare.getName() + "   |   " + numberTimesPrice);
                System.out.println(" ");
            }
            totalSaved = totalSaved.add(numberOfDeal);
        }
        BigDecimal TotalWeightInKg = TotalWeight.divide(BigDecimal.valueOf(1000));


        System.out.println("===============================================");

        System.out.println("FØR TILBUD: " + total);
        BigDecimal procent = totalSaved.divide(total, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));//Bare for sjov :)
        if (procent.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("SPARET: " + totalSaved + "   Svarer til: " + procent + "%" + " Besparet på hele beløbet");
        }
        System.out.println("EFTER TILBUD. TOTAL: " + total.subtract(totalSaved));

        System.out.println("HERAF MOMS (25%): " + total.multiply(BigDecimal.valueOf(0.20)));


        System.out.println("Vil du se hvad dine ting vejer ialt?"); //Bare forsjov :)
        System.out.println("Vælg J/N");
        String input = scanner.nextLine();
        switch (input) {
            case ("J"):
                System.out.println("Vægt: " + TotalWeightInKg + "kg."); //Vægten består af vægten af en ml eller gram. Har fjernet stk, da stk har forskellige vægte. Så de er ikke til at regne med.
            case ("N"):
            System.exit(1);
        }

    }
}
