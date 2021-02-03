package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        //positive bank balance
        BankAccount bankAccount1 = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount1.getBalance());
        //zero bank balance
        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount2.getBalance());
        //negative bank balance
        BankAccount bankAccount3 = new BankAccount("a@b.com", -300);
        assertEquals(-300, bankAccount3.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        // Withdraw within balance
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());
        // withdraw with insufficient balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        // withdraw so balance goes to 0
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());
        // withdraw when balance is 0
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(100));
        // withdraw negative amount
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(-100));
        
        BankAccount bankAccount1 = new BankAccount("A@b.com", 200);
        //withdraw with decimal places
        bankAccount1.withdraw(99.99);
        assertEquals(100.01, bankAccount1.getBalance());
        //withdraw with more than two decimal places
        assertThrows(InsufficientFundsException.class, () -> bankAccount1.withdraw(100.001));
    }

    @Test
    void isEmailValidTest(){
        //Default correct case
        assertTrue(BankAccount.isEmailValid("a@b.com"));
        //Class - no characters betwee @ and . boundry case
        assertFalse(BankAccount.isEmailValid("a@.com"));
        //Class - nothing infront boundry case
        assertFalse(BankAccount.isEmailValid("@b.com"));
        //no . boundry case
        assertFalse(BankAccount.isEmailValid("@"));
        // class no @ no text boundry case
        assertFalse(BankAccount.isEmailValid(".com"));
        //Class no text before or inbetween @ and . equilvalence case
        assertFalse(BankAccount.isEmailValid("@.com"));
        //No domain equivalence case
        assertFalse(BankAccount.isEmailValid("a@b."));
        //Once character domain boundary case
        assertTrue(BankAccount.isEmailValid("a@b.c"));
        //Blank equivalence case
        assertFalse(BankAccount.isEmailValid(""));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        //check for exception when amount is negative
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100));
        //check for exception when amount has more than two decimal places
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.001));
        //check for exception when amount is negative and has more than two decimal places
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100.001));
    }

    @Test
    void isAmountValidTest() {
        //correct case
        assertTrue(BankAccount.isAmountValid(1.99));
        //non-negative border case
        assertTrue(BankAccount.isAmountValid(1.0));
        //negative border case
        assertFalse(BankAccount.isAmountValid(-1.0));
        //middle case
        assertTrue(BankAccount.isAmountValid(0.0));
        //two digit equivalence case
        assertTrue(BankAccount.isAmountValid(10.0));
        //two decimal place broder case
        assertTrue(BankAccount.isAmountValid(2.99));
        //over two decimal place border case
        assertFalse(BankAccount.isAmountValid(1.999));
        //negative and decimal equivalence case
        assertFalse(BankAccount.isAmountValid(-2.99));
    }

    @Test
    void depositTest() throws IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        //base case
        bankAccount.deposit(100);
        assertEquals(300, bankAccount.getBalance());
        //deposit with deciaml places
        bankAccount.deposit(1.99);
        assertEquals(201.99, bankAccount.getBalance());
        //deposit with zero value
        bankAccount.deposit(0);
        assertEquals(201.99, bankAccount.getBalance());
        //negative value deposit
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-100));
        //more than two decimal places deposit
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(100.001));
        //negative and more than two decimal place deposit
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-100.001));
        
    }

}