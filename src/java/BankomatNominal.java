/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This class is for nominals in atm
 * @author adamr
 */
public class BankomatNominal {

    int nominal;
    int count;
    /**
     * constructor
     * @param nominal - nominal
     * @param count - nominal count
     */
    public BankomatNominal(int nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }

    public int getNominal() {
        return nominal;
    }

    public int getCount() {
        return count;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public void setCount(int count) {
        this.count = count;
    }
    /**
     * this function sub count of nominals in atm
     * @param nominal - nominal
     * @param count - sub count
     */
    public void subNominalCount(int nominal, int count) {
        this.nominal = nominal;
        this.count -= count;
    }
    /**
     * this function add count of nominals in atm
     * @param nominal - nominal
     * @param count - add count
     */
    public void addNominalCount(int nominal, int count) {
        this.nominal = nominal;
        this.count += count;
    }
}
