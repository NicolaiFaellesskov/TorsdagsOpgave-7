package supermarked.robot;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Vare {
    private long id;
    private String name;
    private LocalDate priceDate;
    private BigDecimal price;
    private boolean isBudgetBrand;
    private BigDecimal quantity;
    private String unit;
    private boolean isWeighted;
    private boolean isBio;
    private boolean isAvailable;

    private BigDecimal oldPrice;

    public Vare(String idString, String name, String priceDateString, String priceString, String isBudgetBrandString, String quantityString, String unit, String isWeightedString, String isBioString, String isAvailableString) {
        //Konveterer Strings til de rigtige DataTyper XD
        this.id = Long.parseLong(idString);
        this.name = name;
        this.priceDate = LocalDate.parse(priceDateString);
        this.price = new BigDecimal(priceString);
        this.isBudgetBrand = Boolean.parseBoolean(isBudgetBrandString);
        this.quantity = new BigDecimal(quantityString);
        this.unit = unit;
        this.isWeighted = Boolean.parseBoolean(isWeightedString);
        this.isBio = Boolean.parseBoolean(isBioString);
        this.isAvailable = Boolean.parseBoolean(isAvailableString);
    }



    public void setPrice(BigDecimal price) {
        if (this.oldPrice == null) {
            this.oldPrice = this.price; // gem den gamle pris første gang
        }
        this.price = price; // overskriv med ny pris
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }
    public BigDecimal getPrice(){
        return price;

    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public BigDecimal getQuantity(){
        if(unit.equals("g")) {
            return quantity;
        }
        return BigDecimal.ZERO;
    }

    /*@Override //Brugte toString i starten, men fandt ud af at bruge getMetode til tingene var nemmer, når det kom til beregning af priser.
    public String toString() {

        return name + "  |   " + price;

    }*/
}
