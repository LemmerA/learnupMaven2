package ru.learnup.javaqa.learnupmvn2.tax;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxManagerTest {

    //AddIncome tests
    @Test
    public void addIncomePositive(){
        TaxManager account = new TaxManager();
        account.addIncome(1000);
        account.addIncome(7000);
        account.addIncome(2000);
        assertEquals(10000, account.getIncome());
    }

    @Test
    public void addIncomeNegative(){
        TaxManager account = new TaxManager();
        account.addIncome(-1000);
        account.addIncome(-13);
        account.addIncome(0);
        assertEquals(0, account.getIncome());
    }

    @Test
    public void addIncomeMixed(){
        TaxManager account = new TaxManager();
        account.addIncome(7000);
        account.addIncome(-6000);
        account.addIncome(-999);
        assertEquals(7000, account.getIncome());
    }

    @Test
    public void addIncomeNoOverflow(){
        TaxManager account = new TaxManager();
        account.addIncome(Long.MAX_VALUE - 1000);
        account.addIncome(5000);
        account.addIncome(5000);
        assertEquals(Long.MAX_VALUE, account.getIncome());
    }

    //AddExpense tests
    @Test
    public void addExpensePositive(){
        TaxManager account = new TaxManager();
        account.addExpense(10000);
        account.addExpense(7000);
        account.addExpense(3000);
        assertEquals(20000, account.getExpense());
    }

    @Test
    public void addExpenseNegative(){
        TaxManager account = new TaxManager();
        account.addExpense(-1000);
        account.addExpense(-13);
        account.addExpense(0);
        assertEquals(0, account.getExpense());
    }

    @Test
    public void addExpenseMixed(){
        TaxManager account = new TaxManager();
        account.addExpense(123);
        account.addExpense(-6000);
        account.addExpense(-999);
        assertEquals(123, account.getExpense());
    }

    @Test
    public void addExpenseNoOverflow(){
        TaxManager account = new TaxManager();
        account.addExpense(Long.MAX_VALUE - 1000);
        account.addExpense(100000);
        account.addExpense(-1);
        assertEquals(Long.MAX_VALUE, account.getExpense());
    }

    //GetIncomeTax tests
    @Test
    public void getIncomeTaxPositive(){
        TaxManager account = new TaxManager();
        account.addIncome(10000);
        assertEquals(600, account.getIncomeTax());
    }

    @Test
    public void getIncomeTaxNegative(){
        TaxManager account = new TaxManager();
        account.addIncome(-10000);
        assertEquals(0, account.getIncomeTax());
    }

    @Test
    public void getIncomeTaxRoundUp(){
        TaxManager account = new TaxManager();
        account.addIncome(9);
        assertEquals(1, account.getIncomeTax());
    }

    @Test
    public void getIncomeTaxRoundDown(){
        TaxManager account = new TaxManager();
        account.addIncome(8);
        assertEquals(0, account.getIncomeTax());
    }

    @Test
    public void getIncomeTaxZero(){
        TaxManager account = new TaxManager();
        assertEquals(0, account.getIncomeTax());
    }

    @Test
    public void getIncomeTaxMax(){
        TaxManager account = new TaxManager();
        account.addIncome(Long.MAX_VALUE);
        account.addIncome(100000000);
        assertEquals(553402322211286528L, account.getIncomeTax());
    }


    //GetProfitsTax tests
    @Test
    public void getProfitsTaxPositive(){
        TaxManager account = new TaxManager();
        account.addIncome(12000);
        account.addExpense(10000);
        assertEquals(300, account.getProfitsTax());
    }

    @Test
    public void getProfitsTaxNegative(){
        TaxManager account = new TaxManager();
        account.addIncome(-10000);
        account.addExpense(-8000);
        assertEquals(0, account.getProfitsTax());
    }

    @Test
    public void getProfitsTaxDeltaNegative(){
        TaxManager account = new TaxManager();
        account.addIncome(10000);
        account.addExpense(11111);
        assertEquals(0, account.getProfitsTax());
    }

    @Test
    public void getProfitsTaxDeltaZero(){
        TaxManager account = new TaxManager();
        account.addIncome(123123);
        account.addExpense(123123);
        assertEquals(0, account.getProfitsTax());
    }

    @Test
    public void getProfitsTaxRoundUp(){
        TaxManager account = new TaxManager();
        account.addIncome(9);
        account.addExpense(5);
        assertEquals(1, account.getProfitsTax());
    }

    @Test
    public void getProfitsTaxRoundDown(){
        TaxManager account = new TaxManager();
        account.addIncome(4);
        account.addExpense(1);
        assertEquals(0, account.getProfitsTax());
    }

    @Test
    public void getProfitsTaxZero(){
        TaxManager account = new TaxManager();
        assertEquals(0, account.getProfitsTax());
    }

    @Test
    public void getProfitsTaxMax(){
        TaxManager account = new TaxManager();
        account.addIncome(Long.MAX_VALUE);
        account.addExpense(0);
        assertEquals(1383505805528216320L, account.getProfitsTax());
    }
}