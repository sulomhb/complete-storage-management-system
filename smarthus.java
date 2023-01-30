import java.util.HashMap;
import java.util.Scanner;
/* 

DEL 1
1. Varenummer – består av bokstaver og tall
2. Beskrivelse – en tekst som beskriver kort om varen
3. Pris – Heltall
4. Merkenavn – en tekst som inneholder merke (Hunton, Pergo, Egger osv)
5. Vekt – i kilogram, som et desimaltall
6. Lengde - som et desimaltall
7. Høyde - som et desimaltall
8. Farge – beskrevet som tekst
9. Antall på lager - antall varer på lager. Skal aldri være mindre enn 0.
10.Kategori - et tall som representerer kategori av varen. Bruk følgende: (1) Gulv
laminater, (2) Vinduer (3) Dører og (4) Trelast

 */

class Item {
    public String productNr;
    public String description;
    public Integer price;
    public String brandName;
    public Double weight;
    public Double length;
    public Double height;
    public String color;
    public Integer quantityInStock;
    Category category;

    public enum Category {
        FLOORS, WINDOWS, DOORS, TREELOAD, UNDEFINED;
    }

    public Item(String _productNr, String _description, Integer _price, String _brandName, Double _weight, Double _length,
            Double _height, String _color, Integer _quantityInstock, Integer _category) {

        // Alle items må ha disse verdiene for å bli registrert. Du kan ikke legge inn
        // et Item uten å registrere disse tingene først.
        productNr = _productNr;
        description = _description;
        price = _price;
        brandName = _brandName;
        length = _length;
        weight = _weight;
        height = _height;
        color = _color;

        // Hvis brukeren skriver inn et tall som er større en 0, så registrerer vi det.
        // Hvis bruker prøver å legge inn et ugyldig tall som f.eks -1, så setter vi
        // beholdningen lik 0, og sier ifra om at dette er ikke mulig.

        if (_quantityInstock > 0) {
            quantityInStock = _quantityInstock;
        } else {
            quantityInStock = 0;
            System.out.println("Invalid quantity");
        }

        // Hvis bruker skriver kategori 1, så registrerer vi tilsvarende verdi i
        // varelageret som er da "Gulv laminater".
        switch (_category) {
            case 1:
                category = Category.FLOORS;
                break;
            case 2:
                category = Category.WINDOWS;
                break;
            case 3:
                category = Category.DOORS;
                break;
            case 4:
                category = Category.TREELOAD;
                break;
            default:
                category = Category.UNDEFINED;
                ;
                break;
        }
    };

}

/*
 * DEL 2
 * 1. Skrive ut all varer på lageret
 * 2. Søke etter en gitt vare basert på Varenummer og/eller Beskrivelse
 * 3. Legge en ny vare til registeret. Her skal all informasjon fra 1-10 felter
 * (gitt over)
 * innhentes fra bruker input
 * 4. Øke varebeholdningen til eksisterende vare. M.a.o. du har en vare med et
 * gitt
 * antall på lager (f.eks. 10 stk laminatgulv). Du mottar så en ny forsyning av
 * laminatgulv som så skal registreres inn på lageret (f.eks. 20 stk).
 * 5. Ta ut varer fra varebeholdningen (eksisterende vare). M.a.o. du har en
 * vare
 * med et gitt antall på lageret (f.eks. 20 stk laminatgulv). Du tar så ut 5 stk
 * fra
 * lageret.
 * 6. Slette en vare fra varelageret (fordi den for eksempel er utgått eller
 * ikke i
 * produksjon lenger). M.a.o. du skal ikke lenger ha varen "Laminatgulv" i
 * butikken din lenger. NB! Ikke det samme som å sette antall varer til 0
 * 7. Endre rabatt, pris og/eller varebeskrivelse for en vare
 * Du velger selv hvordan du vil presentere disse valgene for brukeren (meny,
 * kommando el.l.).
 */

class ItemStock {
    /*
     * Vi lager en HashMap som gir oss en key-value pair.
     * 
     * {
     * "servan" : {"alder" : 10, "addresse" : "porsgrunn", "blackhammer" : "XXL"}
     * "suleyman" : --- "" --- samme greie hele veien. Du kan ha så mye du vil
     * }
     * 
     * Når vi skriver allItems.get("servan"), så får vi opp alt om servan ->
     * {"alder" :
     * 10, "addresse" : "porsgrunn", "blackhammer" : "XXL"}
     * Dette er mye raskere enn å finne index hvis vi lager array, og enklere å
     * bruke.
     * 
     */
    HashMap<String, Item> allItems = new HashMap<String, Item>();

    /* Oppgave 1 */
    public void printAllItems() {
        /* Vi printer ut alt vi har i lagert vårt "allItems" */
        allItems.entrySet().forEach(entry -> {
            String printLineForItem = String.format(
                    "Product: %s, Description: %s, Price: %d, Brand Name: %s, Length: %.2f, Height: %.2f, Weight: %.2f, Color: %s, In Stock: %d, Category: %s",
                    entry.getKey(),
                    entry.getValue().description,
                    entry.getValue().price,
                    entry.getValue().brandName,
                    entry.getValue().length,
                    entry.getValue().weight,
                    entry.getValue().height,
                    entry.getValue().color,
                    entry.getValue().quantityInStock,
                    entry.getValue().category);
            System.out.println(printLineForItem);
        });
    };

    /* Oppgave 2 */
    public void searchItemGivenProductNumber(String productNumber) {
        /* Printer ut varen som brukeren skriver produktnummeret til. */
        String printLineForItem = String.format(
                "Product: %s, Description: %s, Price: %d, Brand Name: %s, Length: %.2f, Height: %.2f, Weight: %.2f, Color: %s, In Stock: %d, Category: %s",
                allItems.get(productNumber).productNr,
                allItems.get(productNumber).description,
                allItems.get(productNumber).price,
                allItems.get(productNumber).brandName,
                allItems.get(productNumber).length,
                allItems.get(productNumber).height,
                allItems.get(productNumber).weight,
                allItems.get(productNumber).color,
                allItems.get(productNumber).quantityInStock,
                allItems.get(productNumber).category);
        System.out.println(printLineForItem);
    };

    /* Oppgave 3 */
    public void addNewProductToStock(Item item) {
        /* Putt det nye produktet brukeren skriver inn i lageret vårt. */
        allItems.put(item.productNr, item);
    };

    /* Oppgave 4 */
    public void increaseProductQuantity(String productNumber, Integer addToStock) {
        if (allItems.get(productNumber).productNr != null) {
            /*
             * Vi bruker produktnummeret og finner varen ved bruk av (productNumber),
             * deretter øker vi beholdning med det brukeren vil øke det med (addToStock)
             */
            allItems.get(productNumber).quantityInStock += addToStock;
        } else {
            System.out.println("This product does not exist.");
        }

    };

    /* Oppgave 5 */
       /*
             * Vi bruker produktnummeret og finner varen ved bruk av (productNumber),
             * deretter reduserer vi beholdning med det brukeren vil redusere det med
             * (removeFromStock)
             */
    public void removeProductFromStock(String productNumber, Integer removeFromStock) {
        if (allItems.get(productNumber).productNr != null) {
         
            if( allItems.get(productNumber).quantityInStock - removeFromStock < 0) {
                System.out.println("We do not have enough of this item in stock.");
                return;
            } else {
            System.out.println("Successfully removed:" + removeFromStock + " of " + productNumber);
            allItems.get(productNumber).quantityInStock -= removeFromStock;
            }
        } else {
            System.out.println("This product does not exist.");
        }
    };

    /* Oppgave 6 */
    public void deleteProductFromStock(String productNumber) {
        /* Sletter det oppgitte produktet fra lageret vårt. */
        if (allItems.get(productNumber).productNr != null) {
            allItems.remove(productNumber);
        } else {
            System.out.println("This product does not exist.");
        }

    };

    /* Oppgave 7 */
    public void changePriceOfProduct(String productNumber, Integer newPrice) {
        /*
         * Vi bruker produktnummeret og finner varen ved bruk av (productNumber),
         * deretter endrer vi pris med det brukeren vil endre det med (newPrice)
         */
        if (allItems.get(productNumber).productNr != null) {
            allItems.get(productNumber).price = newPrice;
        } else {
            System.out.println("This product does not exist.");
        }
    };

    public void changeDescriptionOfProduct(String productNumber, String newDescription) {
        /*
         * Vi bruker produktnummeret og finner varen ved bruk av (productNumber),
         * deretter endrer vi description med det brukeren vil endre det med
         * (newDescription)
         */
        if (allItems.get(productNumber).productNr != null) {
            allItems.get(productNumber).description = newDescription;
        } else {
            System.out.println("This product does not exist.");
        }
    };

};

class Menu {
    public void BrukerInput(ItemStock stock, String chosenMenuOption) {

        String productNumber;
        switch (chosenMenuOption) {
            case "1":
                System.out.println("Her er oversikt over alle varene som er på lager:\n");
                stock.printAllItems();
                break;

            case "2":
                Scanner inputOptionTwo = new Scanner(System.in);
                System.out.print("Skriv inn produktnummer: ");
                while (inputOptionTwo.hasNextLine()) {
                    productNumber = inputOptionTwo.nextLine(); // Vi trenger produktnummer for å søke etter vare
                    stock.searchItemGivenProductNumber(productNumber);
                    break;
                }
                break;

            case "3":
                String productNr;
                String description;
                Integer price;
                String brandName;
                Double weight;
                Double length;
                Double height;
                String color;
                Integer quantity;
                Integer category;


                Scanner inputOptionThree = new Scanner(System.in);
                System.out.print("Skriv inn produktnummer til den nye varen: ");
                while (inputOptionThree.hasNextLine()) {
                    /*
                     * Bruker må skrive inn all informasjonen til produktet han ønsker å legge til
                     * på lageret.
                     */
                    productNr = inputOptionThree.nextLine();

                    System.out.print("Skriv inn merke til den nye varen:");
                    brandName = inputOptionThree.nextLine();

                    System.out.print("Skriv inn beskrivelse til den nye varen:");
                    description = inputOptionThree.nextLine();

                    System.out.print("Skriv inn pris til den nye varen:");
                    price = inputOptionThree.nextInt();

                    System.out.print("Skriv inn vekt til den nye varen:");
                    weight = inputOptionThree.nextDouble();

                    System.out.print("Skriv inn lengde til den nye varen:");
                    length = inputOptionThree.nextDouble();

                    System.out.print("Skriv inn høyde til den nye varen:");
                    height = inputOptionThree.nextDouble();

                    System.out.print("Skriv inn antall av den nye varen:");
                    quantity = inputOptionThree.nextInt();

                    System.out.print("Skriv inn kategori til den nye varen:");
                    category = inputOptionThree.nextInt();
                    inputOptionThree.nextLine();                      // add this

                    System.out.print("Skriv inn farge til den nye varen:");
                    color = inputOptionThree.nextLine();

                    System.out.print("\n");

                    /*
                     * ----------------------Nå har vi fått informasjonen vi trenger, da kan vi * legge varen inn i lageret. ----------------------------------
                     * 
                     */
                    Item newItem = new Item(productNr, description, price, brandName, weight, height, length, color, quantity, category);
                    stock.addNewProductToStock(newItem);
                    break;
                }
                break;

            case "4":
                Scanner inputOptionFour = new Scanner(System.in);
                System.out.print("Skriv inn produktnummer: ");
                while (inputOptionFour.hasNextLine()) {
                    productNumber = inputOptionFour.nextLine(); // Vi trenger produktnummer for å øke beholdningen til den spesifikke varen.
                    System.out.print("Skriv inn antall: ");
                    String addToStock = inputOptionFour.nextLine();
                    stock.increaseProductQuantity(productNumber, Integer.parseInt(addToStock));
                    break;
                }
                break;

            case "5":
                Scanner inputOptionFive = new Scanner(System.in);
                System.out.print("Skriv inn produktnummer: ");
                while (inputOptionFive.hasNextLine()) {
                    productNumber = inputOptionFive.nextLine(); // Vi trenger produktnummer for å redusere beholdningen
                                                                // til den spesifikke varen.
                    System.out.print("Skriv inn antall: ");
                    Integer removeFromStock = inputOptionFive.nextInt(); // Vi trenger antallet brukeren ønsker å fjerne
                                                                         // fra lageret.
                    stock.removeProductFromStock(productNumber, removeFromStock);
                    break;
                }
                break;

            case "6":
                Scanner inputOptionSix = new Scanner(System.in);
                System.out.print("Skriv inn produktnummer: ");
                while (inputOptionSix.hasNextLine()) {
                    productNumber = inputOptionSix.nextLine(); // Vi trenger produktnummer for å fjerne den spesifikke          // varen.
                    stock.deleteProductFromStock(productNumber);
                    break;                    
                }
                break;

            case "7":

                Scanner inputOptionSeven = new Scanner(System.in);
                System.out.print(
                            "1. Ønsker du å endre pris på en vare?\n" 
                            + "2. Ønsker du å endre beskrivelse på en vare?\n");
                while (inputOptionSeven.hasNextLine()) {
                    
                    Integer option = inputOptionSeven.nextInt();
                    inputOptionSeven.nextLine();
                    if (option == 1) {
                        System.out.print("Skriv inn produktnummer: ");
                        productNumber = inputOptionSeven.nextLine(); // Vi trenger produktnummer for å fjerne den
                                                                     // spesifikke varen.
                        System.out.print("Skriv inn den nye prisen til produktet: ");
                        Integer _price = inputOptionSeven.nextInt(); // Vi trenger produktnummer for å fjerne den    // spesifikke varen.
                        stock.changePriceOfProduct(productNumber, _price);
                        break;
                    }

                    else if (option == 2) {
                        System.out.print("Skriv inn produktnummer: ");
                        productNumber = inputOptionSeven.nextLine(); // Vi trenger produktnummer for å fjerne den
                                                                     // spesifikke varen.
                        System.out.print("Skriv inn den nye beskrivelsen til produktet: ");
                        String newDescription = inputOptionSeven.nextLine(); // Vi trenger produktnummer for å fjerne
                                                                             // den spesifikke varen.
                        stock.changeDescriptionOfProduct(productNumber, newDescription);
                        break;
                    } else {
                        System.out.print("Ugyldig valg. Du må velge alternativ 1 eller 2.");
                    }
                    inputOptionSeven.close();
                }
                break;
            case "8":
                System.out.println("Avsluttet menyvalg");
                System.exit(0);
                break;
        }
    }

};

class Main {
    public static void main(String[] args) {

        /*------------------------------  Del 1 ------------------------------------------- */
        // Tester om vareklassen fungerer ved å gi den tilfeldige verdier:
        Item item = new Item("VDQ2131", "This is a description", 100, "Nike", 2.1, 5.1, 3.2, "Black", 50, 3);
        /*
         * System.out.println(item.productNr);
         * System.out.println(item.description);
         * System.out.println(item.quantityInStock);
         * System.out.println(item.category); // Bruker funksjonen vår som finner ut av
         * hvilken kategori varen er i. I
         */ // dette tilfellet har vi gitt kategori 1.

        /*------------------------------  Del 2 ------------------------------------------- */

        /* VI TESTER KODEN VÅR VED Å LEGGE INN TILFELDIGE VARER */

        ItemStock stock = new ItemStock();
        Item itemExampleOne = new Item("VDQ2131", "This is description 1", 100, "Nike", 2.1, 3.2, 2.3, "Black", 50, 3);
        Item itemExampleTwo = new Item("KH8882", "This is description 2", 200, "Adidas", 5.1, 3.5, 11.2, "White", 50, 1);

        // 2. Legg til nytt produkt
        System.out.println("LEGG TIL NYTT PRODUKT:\n");
        stock.addNewProductToStock(itemExampleOne);
        stock.addNewProductToStock(itemExampleTwo);
        System.out.println("\n");

        // Vi legger til produkter først ^, for å sjekke om denne fungerer:

        // 1. Skriv ut alle produkter */
        System.out.println("SKRIV UT ALLE PRODUKTER:\n");
        stock.printAllItems();
        System.out.println("\n");

        // 3. Søk etter produkt med produktnummer
        System.out.println("SØK ETTER PRODUKT:\n");
        stock.searchItemGivenProductNumber("KH8882");
        System.out.println("\n");

        // 4. Øk beholdning gitt produktnummer. Vi øker VDQ2131 varen med 100 stykker
        // som eksempel. Da bør vi ha 150 stk.
        System.out.println("ØK BEHOLDNING TIL PRODUKT:\n");
        stock.increaseProductQuantity("VDQ2131", 100);
        stock.searchItemGivenProductNumber("VDQ2131"); // Vi printer denne varen ut for å sjekke om det er økt.
        System.out.println("\n");

        // 5. Vi fjerner 70 stykker av varen vi la til 100 på. Dermed bør vi sitte igjen
        // med 80 stk.
        System.out.println("REDUSER BEHOLDNING TIL PRODUKT:\n");
        stock.removeProductFromStock("VDQ2131", 70);
        stock.searchItemGivenProductNumber("VDQ2131"); // Vi printer denne varen ut for å sjekke om det er fjernet 70
                                                       // stk fra beholdning.
        System.out.println("\n");

        // 6. Vi sletter en vare fra lager.
        System.out.println("SLETT VARE FRA BEHOLDNING:\n");
        stock.deleteProductFromStock("KH8882");
        stock.printAllItems(); // Vi printer ut det vi har på lageret for å se om denne varen er slettet.
        System.out.println("\n");

        // 7. Vi endrer på prisen og beskrivelsen til en vare. Denne kostet 100, vi
        // endrer til at den koster 600.
        System.out.println("ENDRE PRIS TIL PRODUKT:\n");
        stock.changePriceOfProduct("VDQ2131", 600);
        stock.changeDescriptionOfProduct("VDQ2131", "Blackhammer");
        stock.searchItemGivenProductNumber("VDQ2131"); // Vi printer denne varen ut for å sjekke om prisen er endret. Vi
                                                       // kan også bruke printAllItems() for å sjekke.

        /* BRUKERGRENSESNITT */

        Menu userMenu = new Menu();
        while (true) {
            System.out.println(
                    "\n Hva vil du gjøre i dag?\n"
                            +
                            "1. Skriv ut oversikt over alle produkter på lageret\n"
                            +
                            "2. Søk etter en gitt vare basert på varenummer og/eller beskrivelse\n"
                            +
                            "3. Legge en ny vare til registeret. Her skal all informasjon fra 1-10 felter (gitt over) innhentes fra bruker input\n"
                            +
                            "4. Øke varebeholdningen til eksisterende vare.\n"
                            +
                            "5. Ta ut varer fra varebeholdningen (eksisterende vare)\n"
                            +
                            "6. Slette en vare fra varelageret\n"
                            +
                            "7. Endre rabatt, pris og/eller varebeskrivelse for en vare\n"
                            +
                            "8. Avslutt menyvalg\n");

            Scanner sc = new Scanner(System.in);
            System.out.print("Skriv inn et alternativ (1-8): ");
            String chosenMenuOption = sc.nextLine(); // Det brukeren skriver blir lagret i denne variabelen. Dette
            try {
                userMenu.BrukerInput(stock, chosenMenuOption);
            } catch (Exception e) {
                System.out.println("Oi! Her gikk det galt! Sjekk at du skriver inn gyldig verdi!");
            }
        }
    };
};
