package ru.learnup.javaqa.learnupmvn2.tax;

import lombok.*;

@Getter
@NoArgsConstructor
public class TaxManager {
    private long income;
    private long expense;
    public void addIncome(long income){
        if (income <=0){
            return;
        }
        this.income = Math.round(this.income + (double)income);
    }

    public void addExpense(long expense){
        if (expense <=0){
            return;
        }
        this.expense = Math.round(this.expense + (double)expense);
    }

    public long getIncomeTax(){
        return Math.round(income*0.06);
    }

    public long getProfitsTax(){
        long delta = income-expense;
        if (delta <= 0){
            return 0;
        } else {
            return Math.round(delta*0.15);
        }
    }
}
