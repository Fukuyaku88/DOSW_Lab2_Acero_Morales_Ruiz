package edu.eci.dosw.reto7;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RemoteControl {

    private final List<ActionRecord> history;
    private final Deque<ActionRecord> activeStack;

    public RemoteControl(){
        this.history = new ArrayList<>();
        this.activeStack = new ArrayDeque<>();
    }

    public String executeAction(RCCommand action, User user){
        String result = action.execute();
        String currentState = action.getDevice().getState();

        ActionRecord record = new ActionRecord(action, user, currentState, false);

        history.add(record);
        activeStack.push(record);

        return result;
    }

    public String undoLastAction(User user){
        if (activeStack.isEmpty()) {
            return "No past actions to undo.";
        }

        ActionRecord lastActive = activeStack.pop();

        String undoResult = lastActive.rcCommand().undo();

        String restoredState = lastActive.rcCommand().getDevice().getState();

        ActionRecord undoRecord = new ActionRecord(
                lastActive.rcCommand(),
                user,
                restoredState,
                true
        );

        history.add(undoRecord);

        return undoResult;
    }

    public List<ActionRecord> getHistory(){
        return this.history;
    }

    public void printActionHistory(){
        history.forEach(AR -> System.out.println(
                "User Name: " + AR.user().getName() + "\n" +
                        "Action: " + AR.rcCommand().getCName() + "\n" +
                        "Undo: " + AR.isUndone() + "\n" +
                        "Device: " + AR.rcCommand().getDevice().getName() + "\n" +
                        "Device State: " + AR.deviceStateAtTime() + "\n"
        ));

        long totalExecutedActions = history.stream().filter(ar -> !ar.isUndone()).count();
        long totalUndos = history.stream().filter(ActionRecord::isUndone).count();

        System.out.println("=== SUMMARY DETAILS ===");
        System.out.println("Total Regular Actions: " + totalExecutedActions);
        System.out.println("Total Undone Operations: " + totalUndos);
        System.out.println("Total History Records: " + history.size());
        System.out.println("=======================");
    }

}