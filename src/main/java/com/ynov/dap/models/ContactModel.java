package com.ynov.dap.models;

/**
 * MODEL CONTACT.
 * @author POL
 */
public class ContactModel {

    /** The amount. */
    private Integer amount;


    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }


    /**
     * Sets the amount.
     *
     * @param inAmount the new amount
     */
    public void setAmount(final Integer inAmount) {
       this.amount = inAmount;
    }


    /**
     * Instantiates a new contact model.
     *
     * @param inAmount the in amount
     */
    public ContactModel(final Integer inAmount) {
       this.setAmount(inAmount);
    }

}
