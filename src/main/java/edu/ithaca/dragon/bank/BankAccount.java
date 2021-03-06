package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;
    private double balance2;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email) && isAmountValid(startingBalance)){
            this.email = email;
            this.balance = startingBalance;
            this.balance2 = 0;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " or " + "Starting Balance: " + startingBalance + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public double getBalance2(){
        return balance2;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (isAmountValid(amount) == false){
            throw new InsufficientFundsException("Not enough money");
        }
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        if (email.indexOf('@') < 1){
            return false;
        }
        if (email.indexOf('.')==-1){
            return false;
        }
        if (email.length()-email.indexOf('.')<2){
            return false;
        }
        if (email.indexOf('.')-email.indexOf('@')==1){
            return false;
        }
        else {
            return true;
        }
    }
    /**
     * Checks if the amount is a non-negative and has no more than two decimal places
     */
    public static boolean isAmountValid(double amount){
        if (amount < 0){
            return false;
        }
        if ((amount*100)%(1) != 0){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Deposits certain amount into the balance
     */
    public void deposit(double amount) throws IllegalArgumentException{
        if (isAmountValid(amount)){
            balance += amount;
        }
        else{
            throw new IllegalArgumentException("Amount: " + amount + " is an invalid amount to deposit.");
        }
    }

    /**
     * transfers a certain amount from one balance to another
     */
    public void transfer(double amount) throws InsufficientFundsException{
        withdraw(amount);
        balance2 += amount;
    }
}
