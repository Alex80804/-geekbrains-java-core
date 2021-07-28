package lesson4hw;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private HashMap<String, String> phones;

    public Phonebook() {
        phones = new HashMap<String, String>();
    }

    public void add(String phoneNumber, String lastname) {
        phones.put(phoneNumber, lastname);
    }

    public void get(String lastname) {
        for (Map.Entry<String, String> record : phones.entrySet()) {
            String entryValue = record.getValue();
            if (entryValue.equals(lastname)) {
                System.out.println("Lastname: " + entryValue + ", Phone: " + record.getKey());
            }
        }
    }
}
