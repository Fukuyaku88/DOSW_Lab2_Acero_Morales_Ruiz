package edu.eci.dosw.reto6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import edu.eci.dosw.reto6.enums.DifficultyLevel;
import edu.eci.dosw.reto6.enums.Priority;
import edu.eci.dosw.reto6.enums.TicketState;

public class TalkToTechnicalSupportC6 {
    private static ArrayList<Ticket> tickets;
    private static int currentTicketId;
    private static TechnicianHandler headResponsabilityChain;

    public static void main(String[] args) {
        tickets = new ArrayList<Ticket>();
        currentTicketId = 1;

        addNewHeadResponsabilityChain("Karla Sanchez", DifficultyLevel.ADVANCED, Priority.MEDIUM);
        addNewHeadResponsabilityChain("Carlos Savedra", DifficultyLevel.INTERMEDIATE, Priority.MEDIUM);
        addNewHeadResponsabilityChain("Saray Ovalle", DifficultyLevel.BASIC, Priority.LOW);

        String difficultyLevels = Arrays.stream(DifficultyLevel.values())
                                                    .map(Enum::name)
                                                    .collect(Collectors.joining(", "));;
        String priorities = Arrays.stream(Priority.values())
                                                    .map(Enum::name)
                                                    .collect(Collectors.joining(", "));;;

        Scanner sc = new Scanner(System.in);

        System.out.println("¡Hola bienvenido al soporte tecnico, aqui puedes generar tus tickets!\n");

        

        boolean state = true;
        while (state) {
            System.out.println("Para crear un ticket digite; nivel de dificultad, prioridad y descripcion, separados por un '-' y en ese orden.\n");
            System.out.println("Niveles de dificultad; " + difficultyLevels);
            System.out.println("Niveles de Prioridad; " + priorities + "\n");
            System.out.println("Si desea ver el estado final de todos sus tickets ingrese 'STATISTICS'");
            System.out.println("Si desea borrar todos sus tickets, ingrese 'CLEAR'");
            System.out.println("Para salir, ingrese 'EXIT'\n");
            System.out.print("Ingrese su respuesta: ");
            String selected = sc.nextLine();

            if (selected.toUpperCase().equals("EXIT")) {
                state = false;
                break;
            } else if (selected.toUpperCase().equals("STATISTICS")) {
                beginToSolveTickets();
                printTicketStatistics();
            } else if (selected.toUpperCase().equals("CLEAR")) {
                clearTicketsList();
            } else {
                String[] selectedArray = selected.trim(). split("-");
                if (selectedArray.length != 3) {
                    System.out.println("\nIngrese los 3 datos solicitados correctamente\n");
                    continue;
                }
                try {
                    DifficultyLevel difficultyLevel = DifficultyLevel.valueOf(selectedArray[0].trim().toUpperCase());
                    Priority priority = Priority.valueOf(selectedArray[1].trim().toUpperCase());
                    String description = selectedArray[2].trim();

                    addTicket(currentTicketId, difficultyLevel, priority, description);
                    System.out.println( "\nEl ticket numero " + String.valueOf(currentTicketId) + ", con nivel de dificultad " + difficultyLevel + ", y nivel de prioridad; " + priority + " fue agregado correctamente\n");
                    currentTicketId ++;
                } catch (IllegalArgumentException e) {
                    System.out.println("\n\nError: Uno o mas de los valores ingresados no coincide con los valores disponibles\n\n");
                }
            }
        }

        sc.close();
    }

    private static void addNewHeadResponsabilityChain(String name, DifficultyLevel difficultySpecialization, Priority prioritySpecialization) {
        TechnicianHandler newHead = new Technician(name, difficultySpecialization, prioritySpecialization);
        if (headResponsabilityChain == null) {
            headResponsabilityChain = newHead;
        } else {
            newHead.setNextHandler(headResponsabilityChain);
            headResponsabilityChain = newHead;
        }
    }

    private static void addTicket(int id, DifficultyLevel difficultyLevel, Priority priority, String description) {
        tickets.add(new Ticket(id, difficultyLevel, priority, description));
    }

    private static void beginToSolveTickets() {
        if (tickets.isEmpty()) {
            System.out.println("\nNo hay tickets que revisar, ingrese tickets para obtener estadisticas de los mismos.\n");
            return;
        }
        tickets.forEach(ticket -> headResponsabilityChain.handle(ticket));
    }

    private static void printTicketStatistics() {
        if (tickets.isEmpty()) {
            return;
        }

        //Tickets reviewed by more than one technician
        ArrayList<Ticket> moreThanOneTechnicianReviewed = tickets.stream().filter(ticket -> ticket.getAmountOfHandles() > 1).collect(Collectors.toCollection(ArrayList::new));

        //List of unsolved Tickets
        ArrayList<Ticket> unsolvedTickets = tickets.stream().filter(ticket -> ticket.getState() == TicketState.PENDING_ESCALATION).collect(Collectors.toCollection(ArrayList::new));

        //Number of solved tickets
        int numberOfSolvedTickets = (int) tickets.stream().filter(ticket -> ticket.getState() == TicketState.SOLVED).count(); //Here I use type casting because it's very sure that this program will not get near to use all the long number

        //Getting the number of tickets per level.
        Map<DifficultyLevel, Long> ticketsPerLevel = tickets.stream()
                                            .collect(Collectors.groupingBy(Ticket::getDifficultyLevel, Collectors.counting()));

        //Finding the most common priority of solved tickets, and it's amount
        String communSolvedTicketsPriority = null;
        int numberOfCommunSolvedTicketsPriority = 0;
        Optional<Map.Entry<Priority, Long>> solvedTicketsPriorities = tickets.stream()
                                            .filter(ticket -> ticket.getState() == TicketState.SOLVED)
                                            .collect(Collectors.groupingBy(Ticket::getPriority, Collectors.counting()))
                                            .entrySet().stream().max(Map.Entry.comparingByValue()); //Here I transform the new map again into a stream, to organize the pairs from max to min value

        if (solvedTicketsPriorities.isPresent()) {
            communSolvedTicketsPriority = solvedTicketsPriorities.get().getKey().name();
            numberOfCommunSolvedTicketsPriority = solvedTicketsPriorities.get().getValue().intValue();
        } else {
            communSolvedTicketsPriority = "No hay tickets resueltos.";
        }

        System.out.println("\n=============================================================================================================");
        System.out.println("                                           ALL TICKET'S CURRENT STATE                                        ");
        System.out.println("=============================================================================================================");
        for (int i = 0; i < tickets.size(); i++) {
            tickets.get(i).printStatistics();
        }
        System.out.println("\n=============================================================================================================");
        System.out.println("                                  TICKETS REVIEWED BY MORE THAN ONE TECHNICIAN                               ");
        System.out.println("=============================================================================================================");
        for (int i = 0; i < moreThanOneTechnicianReviewed.size(); i++) {
            moreThanOneTechnicianReviewed.get(i).printStatistics();
        }
        System.out.println("\n=============================================================================================================");
        System.out.println("                                        TICKET'S THAT REMAINS UNSOLVED                                       ");
        System.out.println("=============================================================================================================");
        for (int i = 0; i < unsolvedTickets.size(); i++) {
            unsolvedTickets.get(i).printStatistics();
        }
        System.out.println("\n=============================================================================================================");
        System.out.println("                                          TICKET'S GENERAL STATISTICS                                        ");
        System.out.println("=============================================================================================================");
        ticketsPerLevel.forEach((difficultyLevel, amount) -> {
            System.out.println("Difficulty Level: " + difficultyLevel + " | Amount of Tickets: " + amount);
        });
        System.out.println("Number of Resolved Tickets: " + String.valueOf(numberOfSolvedTickets));
        System.out.println("Number of Pending Tickets: " + String.valueOf(unsolvedTickets.size()));
        System.out.println("Most Common Priority of Resolved Tickets (MCPRT): " + communSolvedTicketsPriority);
        System.out.println("Number of tickets with the MCPRT: " + String.valueOf(numberOfCommunSolvedTicketsPriority) + "\n");

    }

    private static void clearTicketsList() {
        tickets = new ArrayList<Ticket>();
        currentTicketId = 1;
        System.out.println("\nSus tickets fueron eliminados con exito\n");
    }

    public static void run() {
        main(new String[0]);
    }
}
