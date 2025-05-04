package org.example.finalproject;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PropertyManagement implements  Serializable{
    private  Scanner input = new Scanner(System.in);
    private  Scanner stringInput = new Scanner(System.in);
    private  ArrayList<Seller> sellers = new ArrayList<>();
    private  ArrayList<Buyers> buyers = new ArrayList<>();
    private  ArrayList<Tenant> tenants = new ArrayList<>();
    private  ArrayList<Properties> properties = new ArrayList<>();



    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(ArrayList<Seller> sellers) {
        this.sellers = sellers;
    }

    public ArrayList<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(ArrayList<Tenant> tenants) {
        this.tenants = tenants;
    }

    public ArrayList<Buyers> getBuyers() {
        return buyers;
    }

    public void setBuyers(ArrayList<Buyers> buyers) {
        this.buyers = buyers;
    }

    public ArrayList<Properties> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Properties> properties) {
        this.properties = properties;
    }

    public void addProperty(Properties property) {
        properties.add(property);
    }


    public void addNewSeller() {
        // Code to add a new seller
        System.out.println("Enter the name of the seller:");
        String name = stringInput.nextLine();
        // Address input
        System.out.println("Enter address of the seller:");
        String address = stringInput.nextLine();
        // Phone number input
        System.out.println("Enter phone Number: ");
        String phoneNumber =stringInput.nextLine();
        ArrayList<Long> sellerPhoneNumbers = new ArrayList<>();

        // Additional info input
        System.out.println("Enter the CNIC of the seller:");
        String cnic = stringInput.nextLine();
        System.out.println("Enter the name of the seller's father:");
        String fatherName = stringInput.nextLine();
        System.out.println("Enter Password: ");
        String password = stringInput.nextLine();
        Seller seller = new Seller(name, address, phoneNumber, cnic, fatherName, password);
        sellers.add(seller);
    }

    public void addNewBuyer() {
        // Code to add a new buyer
        System.out.println("Enter the name of the buyer:");
        String name = stringInput.nextLine();

        // Address input
        String address = stringInput.nextLine();
        // Phone number input
        String phoneNumber = stringInput.nextLine();

        // Additional info input
        System.out.println("Enter the CNIC of the buyer:");
        String cnic = stringInput.nextLine();
        System.out.println("Enter the name of the buyer's father:");
        String fatherName = stringInput.nextLine();
        System.out.println("Enter Password: ");
        String password = stringInput.nextLine();
        Buyers buyer = new Buyers(name, address, phoneNumber, cnic, fatherName,password);
        buyers.add(buyer);
    }

    public void addNewTenant() {
        // Code to add a new tenant
        System.out.println("Enter the name of the tenant:");
        String name = stringInput.nextLine();
        String address = stringInput.nextLine();
        String phoneNumber = stringInput.nextLine();

        // Additional info input
        System.out.println("Enter the CNIC of the tenant:");
        String cnic = stringInput.nextLine();
        System.out.println("Enter the name of the tenant's father:");
        String fatherName = stringInput.nextLine();
        System.out.println("Enter Password: ");
        String password = stringInput.nextLine();
        Tenant tenant = new Tenant(name,  address, phoneNumber, cnic, fatherName,password);
        tenants.add(tenant);
    }

    public void addNewProperty() {

        System.out.println("Enter the property type:");
        String propertyType = stringInput.nextLine();
        System.out.println("Is the property available? (true/false):");
        boolean isAvailable = input.nextBoolean();
        System.out.println("Enter the price of the property:");
        double price = input.nextDouble();
        System.out.println("Property Id :");
        int propertyId = input.nextInt();
        String address = stringInput.nextLine();
        String saleOrRent = stringInput.nextLine();
        double size = input.nextDouble();
        Properties property = new Properties(propertyType, isAvailable, price, address, propertyId,saleOrRent,size);
        System.out.println("Is the seller of this property new or old?");
        String sellerOfProperty = stringInput.nextLine();
        if (sellerOfProperty.equalsIgnoreCase("new")) {
            addNewSeller();
            Seller sellersLast = sellers.getLast();
            property.setSeller(sellersLast);
            sellersLast.addProperty(property);


        } else {
            System.out.println("Enter the cnic of the seller : ");
            int cnic= input.nextInt();
            for (Seller s : sellers) {
                if (s.getCnic().equals(cnic) ) {
                    property.setSeller(s);
                    s.addProperty(property);
                }
            }
        }
        properties.add(property);
    }




    public void retrieveSellers() {

        for (Seller seller : sellers) {
            seller.displayInfo();
        }
    }

    public void retrieveBuyers() {

        for (Buyers buyer : buyers) {
            buyer.displayInfo();
        }
    }

    public void retrieveTenants() {

        for (Tenant tenant : tenants) {
            tenant.displayInfo();
        }
    }

    public void retrieveProperties() {
        for (Properties prop : properties) {

            prop.displayInfo();
        }
    }

    public void deleteProperty() throws IllegalArgumentException {
        System.out.println("Enter id of property to be deleted: ");
        int id = input.nextInt();
        if(id<=0)
        {throw new IllegalArgumentException("id cannot be negative or zero ");}
        else
        {for (Properties deletingProperty : properties) {
            if (id == deletingProperty.getPropertyId()) {
                properties.remove(deletingProperty);

            } else {
                System.out.println("Property id invalid");
            }
        }}
    }

    public void rentProperty() {
        System.out.println("Enter ID of property to be rented: ");
        int id = input.nextInt();

        for (Properties foundProperty : properties) {
            if (id == foundProperty.getPropertyId() && !foundProperty.isAvailable()) {
                System.out.println("Property Details:");
                foundProperty.displayInfo();
                System.out.println("Do you want to rent the property now? (Y/N)");
                String choice = input.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    System.out.println("Enter tenant CNIC: ");
                    int tenantcnic = input.nextInt();


                    for (Tenant foundTenant : tenants) {
                        if (foundTenant.getCnic().equals(tenantcnic)) {
                            //  foundProperty.isAvailible(false);
                            foundTenant.addProperties(foundProperty);
                            System.out.println("Property rented.");
                            foundTenant.setRentPerMonth(foundProperty.getPrice());
                            Transaction t1 = new Transaction(foundProperty.getPrice(), foundProperty.getSeller(), foundTenant, foundProperty);
                            t1.displayTransactionRent();
                            break;
                        } else {
                            System.out.println("Tenant ID not found. Transaction canceled.");
                        }
                    }
                }
            } else {
                System.out.println("Property with ID " + id + " does not exist or is already rented.");
                return;
            }
        }
    }
    public void addBuyer(Buyers buyer){
        buyers.add(buyer);
    }
    public void addSeller(Seller seller){
        sellers.add(seller);
    }
    public void addTenant(Tenant tenant){
        tenants.add(tenant);
    }

    public void initializeProperties() {
        Seller s = new Seller("Bilal", "Dream-Gardens-Lahore", "03216048854", "348", "Qadri", "ppg");
        sellers.add(s);

        Buyers b = new Buyers("Humais","h71","0302","123","nime","ppg");
        //buyers.add(b);

        Properties property1 = new Properties("House", true, 2500, "123 Main St", 1, "Sale", 2000);
        property1.setSeller(s);
        addProperty(property1);

        Properties property2 = new Properties("Apartment", true, 1500, "456 Eme St", 2, "Sale", 1500);
        property2.setSeller(s);
        addProperty(property2);

        Properties properties4 = new Properties("Flat", true, 33000, "eme", 102, "Sale", 342);
        properties4.setSeller(s);
        addProperty(properties4);

        Properties property3 = new Properties("Condo", true, 3000, "789 Oak St", 3, "Rent", 1800);
        property3.setSeller(s);
        addProperty(property3);

        Seller s1 = new Seller("Awais", "EME-LAHORE", "03015990229", "3840356224896", "Iqbal", "ssggop12");
        sellers.add(s1);
        Tenant tenant = new Tenant("Humais","EME","0301","3480","Tahir","ppp");
        tenants.add(tenant);
        Properties p1 = new Properties("FLat", true, 2000, "123 Main St", 10, "Rent", 424);
        p1.setSeller(s1);
        addProperty(p1);

        Properties p2 = new Properties("House", true, 3000, "456 Elm St", 200000, "Rent", 244);
        p2.setSeller(s1);
        addProperty(p2);
    }



    public void serializeTenantArrayListToFile(ArrayList<Tenant> list, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Tenant t : list) {
                writer.append(t.getName())
                        .append(',')
                        .append(t.getAddress())
                        .append(',')
                        .append(t.getPhoneNumber())
                        .append(',')
                        .append(t.getCnic())
                        .append(',')
                        .append(t.getFathersName())
                        .append(',')
                        .append(t.getPassword())
                        .append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Deserialize Tenant ArrayList from file
    public ArrayList<Tenant> deserializeTenantArrayListFromFile(String filename) {
        ArrayList<Tenant> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0];
                    String address = parts[1];
                    String phoneNumber = parts[2];
                    String cnic = parts[3];
                    String fathersName = parts[4];
                    String password = parts[5];
                    list.add(new Tenant(name, address, phoneNumber, cnic, fathersName, password));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
    // Similarly, methods for Seller, Buyer, and Property can be defined

    // Serialize Seller ArrayList to file
    public void serializeSellerArrayListToFile(ArrayList<Seller> list, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Seller s : list) {
                writer.append(s.getName())
                        .append(',')
                        .append(s.getAddress())
                        .append(',')
                        .append(s.getPhoneNumber())
                        .append(',')
                        .append(s.getCnic())
                        .append(',')
                        .append(s.getFathersName())
                        .append(',')
                        .append(s.getPassword())
                        .append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialize Seller ArrayList from file
    public ArrayList<Seller> deserializeSellerArrayListFromFile(String filename) {
        ArrayList<Seller> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0];
                    String address = parts[1];
                    String phoneNumber = parts[2];
                    String cnic = parts[3];
                    String fathersName = parts[4];
                    String password = parts[5];
                    list.add(new Seller(name, address, phoneNumber, cnic, fathersName, password));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
    // Serialize Buyer ArrayList to file
    public void serializeBuyerArrayListToFile(ArrayList<Buyers> list, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Buyers buyer : list) {
                writer.append(buyer.getName())
                        .append(',')
                        .append(buyer.getAddress())
                        .append(',')
                        .append(buyer.getPhoneNumber())
                        .append(',')
                        .append(buyer.getCnic())
                        .append(',')
                        .append(buyer.getFathersName())
                        .append(',')
                        .append(buyer.getPassword())
                        .append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Deserialize Buyer ArrayList from file
    public ArrayList<Buyers> deserializeBuyerArrayListFromFile(String filename) {
        ArrayList<Buyers> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0];
                    String address = parts[1];
                    String phoneNumber = parts[2];
                    String cnic = parts[3];
                    String fathersName = parts[4];
                    String password = parts[5];
                    list.add(new Buyers(name, address, phoneNumber, cnic, fathersName, password));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
    // Serialize Property ArrayList to file
    public void serializePropertyArrayListToFile(ArrayList<Properties> list, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialize Property ArrayList from file
    public ArrayList<Properties> deserializePropertyArrayListFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Properties>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

