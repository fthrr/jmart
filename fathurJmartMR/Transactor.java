package fathurJmartMR;


/**
 * Write a description of class Transaction here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 September 2021
 */
public abstract interface Transactor
{
    public abstract boolean validate();
    public abstract Invoice perform();
}
