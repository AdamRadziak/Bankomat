
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
    public static int Sum_of_nominals;
    
    public Bankomat() {

    }

    public static void sort_nominals_desc_in_kasa() {
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
    
       public static ArrayList<Integer> sort_desc(ArrayList<Integer> list) {
        /* sorting from highest to lowest*/
        // create temp list to add everything from list
        ArrayList<Integer> sorted_list = new ArrayList<>();
        for(int el:list){
            sorted_list.add(el);
        }
        // next sort sorted list
        for (int j = 0; j < 30; j++) {

            boolean is_change = false;

            for (int i = 0; i < sorted_list.size() - 1; i++) {

                int actual = sorted_list.get(i);
                int next = sorted_list.get(i + 1);

                if (next > actual) {
                    is_change = true;
                    sorted_list.set(i, next);
                    sorted_list.set(i + 1, actual);

                }
                if (is_change = false) {
                    break;
                }
            }
        }
        return sorted_list;
    }

    public String withdraw(int amount) {
        ArrayList<Integer> nominals_withdraw = new ArrayList<>();
        ArrayList<Integer> nominals_counts_withdraw = new ArrayList<>();
        String message = "Wypłacono: \n";
        // initialize remain_amount to amount
        int remain_amount = amount;
        int nominal;
        int nominal_count;
        int withdraw_sum = 0;
        // sort nominlas from highest to lowest
        sort_nominals_desc_in_kasa();
        for (int i = 0; i < Kasa.size(); i++) {
            nominal = Kasa.get(i).getNominal();
            nominal_count = Kasa.get(i).getCount();
            // add to arraylist nominals
            nominals_withdraw.add(nominal);
            nominals_counts_withdraw.add(remain_amount / nominal);
            // if nominals withdraw count more than nominals in bankomat
            if (nominals_counts_withdraw.get(i) > nominal_count) {
                nominals_counts_withdraw.set(i, nominal_count);
            }
            remain_amount -= nominal * nominals_counts_withdraw.get(i);
            // refresh nominal count in cash mashine
//            Kasa.get(i).subNominalCount(nominal, nominals_counts_withdraw.get(i));
            
        }
    if (remain_amount!= 0) {
        message = "Nie można wypłacić żądanej kwoty\n";
        message += "Żądana kwota " + amount;
        message += " Dostępne nominały\n";
        for(int i = 0; i < Kasa.size(); i++){
            message += Kasa.get(i).getNominal() + "x" + Kasa.get(i).getCount() + " ";
        }
    }

    else {
    for (int i = 0; i < nominals_withdraw.size(); i++) {
        withdraw_sum += nominals_withdraw.get(i)*nominals_counts_withdraw.get(i);
        // refresh nominal count in cash mashine
        Kasa.get(i).subNominalCount(nominals_withdraw.get(i), nominals_counts_withdraw.get(i));
        message = message + " " + nominals_withdraw.get(i) + " x " + nominals_counts_withdraw.get(i) + "\n";
    }
        Sum_of_nominals -= withdraw_sum;
        message = message + "Wypłacona kwota: " + withdraw_sum + "\n";
        message = message + "Pozostało środków: " + Sum_of_nominals;
        
    }
    return message ;
}

public String payment(ArrayList<Integer> nominal,ArrayList<Integer> amount){
    String message = "Wpłacono \n";
    int payment_sum = 0;
    ArrayList<Integer> nominals_sort_desc = new ArrayList<>();
    ArrayList<Integer> amount_sort_desc = new ArrayList<>();
    // first sorting nominals arraylist desc
    nominals_sort_desc = sort_desc(nominal);
    amount_sort_desc = sort_desc(amount);
        for(int i =0; i < nominals_sort_desc.size(); i++){
            // check if nominal is in kasa and Kasa is not empty
            if ( i < Kasa.size() && Kasa.get(i).getNominal() == nominals_sort_desc.get(i)){
                Kasa.get(i).addNominalCount(nominals_sort_desc.get(i), amount_sort_desc.get(i));
            }
            else{
                Kasa.add(new BankomatNominal(nominals_sort_desc.get(i), amount_sort_desc.get(i)));
            }
            payment_sum += nominals_sort_desc.get(i)*amount_sort_desc.get(i);
        message = message + " " + nominals_sort_desc.get(i) + " x " + amount_sort_desc.get(i);
    }
        Sum_of_nominals += payment_sum;
        message = message + "\n" + "Suma wpłat:\n" + payment_sum;
        message = message + "\n" + "Stan konta po wpłacie:\n" + Sum_of_nominals;
    return message;
}
}

