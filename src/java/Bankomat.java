
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author adamr
 */
public class Bankomat {
    // list of nominals and count of nominals in atm
    public static ArrayList<BankomatNominal> Checkout = new ArrayList<>();
    // value of all nominals in atm
    public static int Sum_of_nominals;


    public String withdraw(int amount) {
        // withdrawed nominals
        ArrayList<Integer> nominals_withdraw = new ArrayList<>();
        // withdrawed nominals count
        ArrayList<Integer> nominals_counts_withdraw = new ArrayList<>();
        // this comparator is to sort reverse order in nominals
        Comparator<BankomatNominal> compareByNominaldesc = Comparator
                                                            .comparing(BankomatNominal::getNominal)
                                                            .reversed();
        String message = "Wypłacono: \n";
        // initialize remain_amount to amount
        int remain_amount = amount;
        // nominla in chekout
        int nominal;
        // max nominla count inn checkout
        int nominal_count;
        // sum of withhdrawed nominals
        int withdraw_sum = 0;
        // count of n=withdrawed nominals
        int withdraw_nominal_count = 0;
        // sort nominals from highest to lowest by Nominal
        Checkout.sort(compareByNominaldesc);
        for (int i = 0; i < Checkout.size(); i++) {
            nominal = Checkout.get(i).getNominal();
            nominal_count = Checkout.get(i).getCount();
            // if  remain_amount is nominal * nominal count in checkout
            if(remain_amount % nominal == 0){
                withdraw_nominal_count = remain_amount/nominal;
            }
            else{
                withdraw_nominal_count = (remain_amount -(remain_amount % nominal))/nominal ;
            }
            // if nominals withdraw count more than nominals in checkout
            if(withdraw_nominal_count >= nominal_count){
                withdraw_nominal_count = nominal_count;
            }
            // add to arraylist nominals
            nominals_withdraw.add(nominal);
            nominals_counts_withdraw.add(withdraw_nominal_count);
            // equal remain account
            remain_amount -= nominal * nominals_counts_withdraw.get(i);
        }
        if (remain_amount != 0) {
            message = "Nie można wypłacić żądanej kwoty\n";
            message += "Żądana kwota " + amount;
            message += " Dostępne nominały\n";
            for (int i = 0; i < Checkout.size(); i++) {
                message += Checkout.get(i).getNominal() + "x" + Checkout.get(i).getCount() + " ";
            }
        } else {
            for (int i = 0; i < nominals_withdraw.size(); i++) {
                withdraw_sum += nominals_withdraw.get(i) * nominals_counts_withdraw.get(i);
                // refresh nominal count in cash mashine
                Checkout.get(i).subNominalCount(nominals_withdraw.get(i), nominals_counts_withdraw.get(i));
                message = message + " " + nominals_withdraw.get(i) + " x " + nominals_counts_withdraw.get(i) + "\n";
            }
            Sum_of_nominals -= withdraw_sum;
            message = message + "Wypłacona kwota: " + withdraw_sum + "\n";
            message = message + "Pozostało środków: " + Sum_of_nominals;
            // sorting in ascending order nominals
            Checkout.sort(Comparator.comparing(BankomatNominal::getNominal));
        }
        return message;
    }

    public String payment(ArrayList<Integer> nominal, ArrayList<Integer> amount) {
        String message = "Wpłacono \n";
        int payment_sum = 0;
        // first sorting nominals arraylist desc
        for (int i = 0; i < nominal.size(); i++) {
            // check if nominal is in kasa and Checkout is not empty
            if (i < Checkout.size() && Checkout.get(i).getNominal() == nominal.get(i)) {
                Checkout.get(i).addNominalCount(nominal.get(i), amount.get(i));
            } else {
                Checkout.add(new BankomatNominal(nominal.get(i), amount.get(i)));
            }
            payment_sum += nominal.get(i) * amount.get(i);
            message = message + " " + nominal.get(i) + " x " + amount.get(i);
        }
        Sum_of_nominals += payment_sum;
        message = message + "\n" + "Suma wpłat:\n" + payment_sum;
        message = message + "\n" + "Stan konta po wpłacie:\n" + Sum_of_nominals;
        return message;
    }
}
