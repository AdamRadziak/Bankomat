/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author adamr
 */
public class BankomatNominal {

    int nominal;
    int count;

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

    public void subNominalCount(int nominal, int count) {
        this.nominal = nominal;
        this.count -= count;
    }

    public void addNominalCount(int nominal, int count) {
        this.nominal = nominal;
        this.count += count;
    }
}
