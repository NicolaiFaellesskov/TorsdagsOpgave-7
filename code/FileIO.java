package supermarked.robot;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    private String priceString;

    public List<Vare> readFile(String fileName) { // metoden returnerer en liste af Vare-objekter og tage en fil som input.

        boolean skipFirstLine = true;
        List<Vare> vareList = new ArrayList<>();
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("indkøbsedel"));
            writer.write("HER SKAL BONNEN INDLÆSES");
            writer.close();


        } catch (IOException e) {

            System.out.println("Cant write file");
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String lineInformation;
            while ((lineInformation = reader.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue; //skipper første linje, da det er header.
                }

                String[] felter = lineInformation.split(";");

                String idString = felter[0];
                String name = felter[1];
                String priceDateString = felter[2];
                String priceString = felter[3];
                if(fileName.equals("data/varer.csv")) {
                    this.priceString = priceString; //Gemmer den gamle pris, så jeg kan hente forskellen mellem tilbud og Normal pris.
                }
                String isBudgetBrandString = felter[4];
                String quantityString = felter[5];
                String unit = felter[6];
                String isWeightedString = felter[7];
                String isBioString = felter[8];
                String isAvailableString = felter[9];

                Vare vare = new Vare(idString, name, priceDateString, priceString, isBudgetBrandString, quantityString, unit, isWeightedString, isBioString, isAvailableString);
                vareList.add(vare);

            }
        } catch (
                IOException e) {
            System.out.println("Cant read file");
        }
        return vareList;
    }
    /* //Gammel kode, tænkte at bruge det i FileIO, men fandt ud af, at det gør det nok lidt mere rodet, at have en get metode i FileIO istedet for Vare. :)
    public void setOldPrice() {

    }
    public Long getOldPrice(){
        Long price = Long.parseLong(priceString);
        return price;
    }*/
}
