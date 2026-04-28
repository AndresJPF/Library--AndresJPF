package Model;

import java.util.Date;

public class Loan {

    private int idLoan;
    private Date loanDate;
    private Date returnDate;
    private int user;
    private int book;

    public Loan() {}

    public Loan(int idLoan, Date loanDate, Date returnDate, int user, int book) {
        this.idLoan = idLoan;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.user = user;
        this.book = book;
    }

    public int getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

   public void setUser(User user) { 
       this.user = user.getIdUser(); 
   }
   
    public void setBook(Book book) { 
        this.book = book.getIdBook(); 
    }
}

