package bootcamp.model.Finance;

import java.math.BigDecimal;

public class Finance {

    private BigDecimal initialBalance;
    private BigDecimal totalBalance;
    private BigDecimal sumOfWholesale;
    private BigDecimal deposit;
    private BigDecimal withdrawal;

    public Finance() {
        initialBalance  = new BigDecimal(5000.0);
        totalBalance = new BigDecimal(5000.0);
    }

    public Finance(BigDecimal initialBalance, BigDecimal totalBalance, BigDecimal sumOfWholesale, BigDecimal deposit, BigDecimal withdrawal) {
        this.initialBalance = initialBalance;
        this.totalBalance = totalBalance;
        this.sumOfWholesale = sumOfWholesale;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        totalBalance = totalBalance.add(deposit);
    }

    public BigDecimal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(BigDecimal withdrawal) {
        totalBalance = totalBalance.subtract(withdrawal);
    }


    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        initialBalance = initialBalance;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        totalBalance = totalBalance;
    }

    public BigDecimal getSumOfWholesale() {
        return sumOfWholesale;
    }

    public void setSumOfWholesale(BigDecimal sumOfWholesale) {
        sumOfWholesale = sumOfWholesale;
    }


//    public BigDecimal resupplierPayment(BigDecimal invoicePayment) {
//        if (invoicePayment.compareTo(this.totalBalance) == -1 || invoicePayment.compareTo(this.totalBalance) == 0) {
//            totalBalance = totalBalance.subtract(invoicePayment);
//            return invoicePayment;
//        } else {
//            System.out.println("We do not have sufficient fund in our bank");
//            return new BigDecimal(0.0);
//        }
//    }

//    public void vendorDeposit(BigDecimal vendorInvoice) {
//        if(vendorInvoice.compareTo(new BigDecimal(0.0)) == 1) {
//            totalBalance = totalBalance.add(vendorInvoice);
//        } else {
//            System.out.println("You need more more money man ");
//        }
//    }
}
