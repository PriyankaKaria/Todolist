package com.company;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;

public class ToDoEngine {

    private Scanner input = new Scanner(System.in);
    private AccountMaker accountMaker = new AccountMaker();
    private AccountLogger accountLogger = new AccountLogger(accountMaker);
    private boolean loopIsTrue = true;
    private Tasker tasks = new Tasker();
    private User user = new User(tasks);

    void displayMainMenu() {
        System.out.println("What do you wanna to do?");
        System.out.println("1. Add Account 2. Log into my account 3. Exit");

        while (loopIsTrue) {
            try {
                getOptionsOfMainMenu(input.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("You've inputed something wrong!");
                System.out.println("What do you wanna to do?");
                System.out.println("1. Add Account 2. Log into my account");
                input.next();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getOptionsOfMainMenu(int option) throws InterruptedException {
        if (option >= 1 && option <= 3) {
            switch (option) {
                case 1:
                    accountMaker.inputLoginAndPassword();
                    accountMaker.createAccount();
                    System.out.println("Now let's log into you account.");

                case 2:
                    while (loopIsTrue) {
                        accountLogger.inputLoginAndPassword();
                        if (!accountLogger.isLoginDataIncorrect()) {
                            loopIsTrue = false;
                        }
                    }
                        break;




            }
        }
    }

    public void displayUserMenu() {
        loopIsTrue = true;
        while (loopIsTrue) {
            System.out.println("What do you wanna to do?");
            System.out.println("1.Add Item 2. Show my ActionItems 3. Delete MyItems 4. Exit");
            getOptionsOfUserMenu(input.nextInt());
        }
    }

    private void getOptionsOfUserMenu(int option) {


        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        if (option >= 1 && option <= 4) {
            switch (option) {
                case 1:

                    try {
                    System.out.println("Write down your Action Iem.");
                    input.nextLine();
                    String taskName =   input.nextLine();

                        System.out.println("Enter a Name of Project :");
                        String projectName =input.nextLine();

                        System.out.println("Enter Task Priority in numeric format. 1 for highest and bigger number for lowest");
                    String taskPriority =input.nextLine();

                    System.out.println("Enter Deadline in yyyy.MM.dd HH:mm:ss Format");
                    String date=input.nextLine();
                    Date taskDedaline = format.parse(date);

                    System.out.println("Enter the time when you want to be reminded in yyyy.MM.dd HH:mm:ss Format");
                    String taskReminder = input.nextLine();
                    Date reminderTime = format.parse(taskReminder);


                    user.addNewTask(new ActionItem (taskName,0,taskDedaline,reminderTime),projectName);
                    System.out.println("Task was added.");

                    }catch(Exception e) {
                        System.out.println("Invalid values entered. Please try again") ;
                    }
                    break;


                case 2:
                    System.out.println("----------------");
                    System.out.println("YOUR TASK LIST:");
                    user.showAllTasks();
                    System.out.println("----------------");
                    break;
                case 3:

                    System.out.println("Enter a Name of Project :");
                    input.nextLine();
                    String projectName =input.nextLine();
                    System.out.println("Write down your Number that you want to delete from task list.");
                    user.deleteTask(input.nextLine(),projectName);
                    System.out.println("Task was deleted");
                    break;

                case 4:
                    loopIsTrue = false;
                    break;

            }
        }
    }
}