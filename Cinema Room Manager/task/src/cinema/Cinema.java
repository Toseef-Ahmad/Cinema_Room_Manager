package cinema;

import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Write your code here

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        CinemaProject project = new CinemaProject(rows, seats);
        System.out.println();
//        project.displaySeats();
        System.out.println();
        while (true) {
            mainMenu(project);
        }
    }

    public static void buyTicket(CinemaProject project) {
        System.out.println("Enter a row number: ");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int seat = scanner.nextInt();
        try {
            project.reserveSeat(row, seat);
        } catch (Exception e) {
            System.out.println("Wrong input!");
        }
    }

    public static void mainMenu(CinemaProject project) {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");

        switch (scanner.nextInt()) {
            case 1:
                project.displaySeats();
                break;
            case 2:
                buyTicket(project);
                break;
            case 3:
                project.printStatistics();
                break;
            case 0:
                System.exit(0);

        }

    }

}


class CinemaProject {

    private int rows;
    private int seats;
    private int purchasedTicket;

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    private int currentIncome;
    public int getPurchasedTicket() {
        return purchasedTicket;
    }

    public void setPurchasedTicket(int purchasedTicket) {
        this.purchasedTicket = purchasedTicket;
    }

    private char[][] cinema;
    private int profit;
    private int totalSeats;
    private int ticketPrice;

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    private int totalIncome;
    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public CinemaProject(int rows, int seats) {

        this.rows = rows;
        this.seats = seats;
        setTotalSeats(rows * seats);
        cinema = new char[rows][seats];
        generateCinema();
        printTotalIncome();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getProfit() {
        return profit;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void displaySeats() {
        System.out.println("Cinema: ");

        for (int i = 0; i < seats; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printProfit() {

        int firstHalf;
        int secondHalf;

        setTotalSeats(rows * seats);
        System.out.println("Total income:");
        if (getTotalSeats() <= 60) {
            setProfit(getTotalSeats() * 10);
            System.out.println("$" + getProfit());
        } else if (getTotalSeats() > 60) {
             firstHalf = getRows() / 2;
             secondHalf = getRows() - firstHalf;
             setProfit(firstHalf * getSeats() * 10 + secondHalf * getSeats() * 8);
             System.out.println("$" + getProfit());
        }
    }

    public void printTicketPrice(int row) {
        int firstHalf;
        int secondHalf;
        if (getTotalSeats() <= 60) {
            setTicketPrice(10);
            setCurrentIncome(getCurrentIncome() + 10);
        } else if (getTotalSeats() > 60) {
            firstHalf = getRows() / 2;
            secondHalf = getRows() - firstHalf;

            if (row > firstHalf) {
                setTicketPrice(8);
                setCurrentIncome(getCurrentIncome() + 8);
            } else {
                setTicketPrice(10);
                setCurrentIncome(getCurrentIncome() + 10);
            }
        }
    }

    public void reserveSeat(int row, int seatNumber) {
        if (cinema[row - 1][seatNumber - 1] == 'B') {
            System.out.println("That ticket has already been purchased ");
            System.out.println();
            Cinema.buyTicket(this);
        } else {
            cinema[row - 1][seatNumber - 1] = 'B';
            setPurchasedTicket(getPurchasedTicket() + 1);
        }
        printTicketPrice(row);
        System.out.println();
        System.out.println("Ticket price: $" + getTicketPrice());
        System.out.println();
//        displaySeats();
    }

    public void generateCinema() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }
    }

    public void printStatistics() {
        System.out.println("Number of purchased tickets: " + getPurchasedTicket());
        System.out.printf("Percentage: %.2f%s \n", getPercentage(), "%");
        System.out.println("Current income: $" + getCurrentIncome());
        System.out.println("Total income: $" + getTotalIncome());
        System.out.println();
    }

    public double getPercentage() {
        return (double) getPurchasedTicket() / (double) getTotalSeats() * 100;
    }

    public void printTotalIncome() {
        int firstHalf;
        int secondHalf;

        firstHalf = getRows() / 2;
        secondHalf = getRows() - firstHalf;
        if (getTotalSeats() <= 60) {
            setTotalIncome(firstHalf * 10 * getSeats() + secondHalf * 10 * getSeats());
        } else {
            setTotalIncome(firstHalf * 10 * getSeats() + secondHalf * 8 * getSeats());
        }
    }
}