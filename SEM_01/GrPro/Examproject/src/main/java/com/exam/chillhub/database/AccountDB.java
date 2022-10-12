package com.exam.chillhub.database;

import com.exam.chillhub.ChillhubApplication;
import com.exam.chillhub.models.Account;
import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountDB {
    private final static AccountDB instance;
    private final static String dbPath = "./accounts.txt";

    static {
        instance = new AccountDB();
    }

    private final Filter media;
    private final List<Account> AccountDB;

    private AccountDB() {
        AccountDB = new ArrayList<>();
        media = MediaDB.getInstance().getDB();
        readAccounts();
    }

    public static AccountDB getInstance() {
        return instance;
    }

    private void readAccounts() {
        try {
            Scanner inputFile = new Scanner(new FileReader(dbPath));
            while (inputFile.hasNext()) {
                String line = inputFile.nextLine();
                String[] accountInfo = line.split(";");
                Account account = new Account(accountInfo[0], accountInfo[1]);
                for (int i = 0; i < Integer.parseInt(accountInfo[2]); i++) {
                    String[] userInfo = inputFile.nextLine().split(";");
                    User u = new User(userInfo[0], userInfo[1]);
                    String[] favorites = inputFile.nextLine().split(";");
                    for (String fav : favorites) {
                        if (!fav.equals("")) {
                            u.changeFavorite(media.getFilteredData().get(Integer.parseInt(fav)));
                        }
                    }
                    account.addUser(u);
                }
                AccountDB.add(account);
            }
            inputFile.close();
        } catch (Exception e) {
            try {
                FileWriter file = new FileWriter(dbPath, false);
                // Creates standard accounts if it was not possible to read the accounts initially (lack of accounts.txt file)
                file.write("admin;admin;2;\n" +
                        "admin1;#7e6d92, #290c5f;\n" +
                        "12;19;142;\n" +
                        "admin2;#936472, #cf503c;\n" +
                        "66;87;101;112;188;\n");
                file.close();
            } catch (Exception ex) {
                ChillhubApplication.showError("Kunne ikke læse brugerindstillinger", "Brugerindstillinger kunne ikke læses fra filsystemet. Prøv at give Chillhub administrator rettigheder.", "");
                throw new RuntimeException(ex.getMessage());
            }
            readAccounts();
        }
    }

    public void saveAccounts() {
        StringBuilder savetxt = new StringBuilder();
        for (Account acc : AccountDB) {
            List<User> users = acc.getUsers();
            savetxt.append(acc.getUsername() + ";" + acc.getPassword() + ";" + users.size() + ";\n");
            for (User u : users) {
                savetxt.append(u.getName() + ";" + u.getColorString() + ";\n");
                for (Media m : u.getFavorites().getFilteredData()) {
                    savetxt.append(m.getIdx() + ";");
                }
                savetxt.append("\n");
            }
        }
        try {
            FileWriter file = new FileWriter(dbPath, false);
            file.write(savetxt.toString());
            file.close();
        } catch (Exception e) {
            ChillhubApplication.showError("Data kunne ikke gemmes", "Brugerdataene kunne ikke gemmes til filsystemet. Prøv at give Chillhub administrator rettigheder.", "");
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @param account Account to be added
     * @return True if account creation succeeds, false if username is already in use
     */
    public boolean addAccount(Account account) {
        for (Account acc : AccountDB) {
            if (acc.getUsername().equals(account.getUsername())) {
                // Account med samme username findes allerede
                return false;
            }
        }
        AccountDB.add(account);
        return true;
    }

    public void removeAccount(Account account) {
        AccountDB.remove(account);
    }

    public List<Account> getAccounts() {
        return this.AccountDB;
    }
}
