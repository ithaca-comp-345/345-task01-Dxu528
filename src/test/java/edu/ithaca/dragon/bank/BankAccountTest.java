package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
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
        //Blank Boundry case
        assertFalse(BankAccount.isEmailValid(""));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}