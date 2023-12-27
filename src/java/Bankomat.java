
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author adamr
 */
public class Bankomat {

    public static ArrayList<BankomatNominal> Kasa = new ArrayList<>();

    public Bankomat() {

    }

    public static void sort_nominals_desc() {
        /* sorting from highest to lowest*/
        for (int j = 0; j < 30; j++) {

            boolean is_change = false;

            for (int i = 0; i < Kasa.size() - 1; i++) {

                BankomatNominal actual = Kasa.get(i);
                BankomatNominal next = Kasa.get(i + 1);

                if (next.getNominal() > actual.getNominal()) {
                    is_change = true;
                    Kasa.set(i, next);
                    Kasa.set(i + 1, actual);

                }
                if (is_change = false) {
                    break;
                }
            }
        }
    }

    public String withdraw(int amount) {
        ArrayList<Integer> nominals_withdraw = new ArrayList<>();
        ArrayList<Integer> nominals_counts_withdraw = new ArrayList<>();
        String message = "Wypłacono: \n";
        int nominal;
        int nominal_count;
        // sort nominlas from highest to lowest
        sort_nominals_desc();
        for (int i = 0; i < Kasa.size(); i++) {
            nominal = Kasa.get(i).getNominal();
            nominal_count = Kasa.get(i).getCount();
            // add to arraylist nominals
            nominals_withdraw.add(nominal);
            nominals_counts_withdraw.add(amount / nominal);
            if (nominals_counts_withdraw.get(i) > nominal_count) {
                nominals_counts_withdraw.set(i, nominal_count);
            }
            amount -= nominal * nominals_counts_withdraw.get(i);
            // refresh nominal count in cash mashine
            Kasa.get(i).subNominalCount(nominal, nominals_counts_withdraw.get(i));
            
        }
    if (amount!= 0) {
        message = "Nie można wypłacić żądanej kwoty";
    }

    else {
    for (int i = 0; i < nominals_withdraw.size(); i++) {
        message = message + " " + nominals_withdraw.get(i) + " x " + nominals_counts_withdraw.get(i);
    }
    }
    return message ;
}

public String payment(ArrayList<Integer> nominal,ArrayList<Integer> amount){
    String message = "Wpłacono \n";
    int payment_sum = 0;
        for(int i =0; i < nominal.size(); i++){
            // check if nominal is in kasa and index is not ot of bounds in Kasa
            if ( i < Kasa.size() && Kasa.get(i).getNominal() == nominal.get(i)){
                Kasa.get(i).addNominalCount(nominal.get(i), amount.get(i));
            }
            else{
                Kasa.add(new BankomatNominal(nominal.get(i), amount.get(i)));
            }
            payment_sum += nominal.get(i)*amount.get(i);
        message = message + " " + nominal.get(i) + " x " + amount.get(i);
    }
        message = message + "\n" + "Suma wpłat:\n" + payment_sum;
    return message;
}
}

