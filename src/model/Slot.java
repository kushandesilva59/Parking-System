package model;

public class Slot {
    private int slotNumber;
    private boolean isEmpty = true;

    public Slot() {
    }

    public Slot(int slotNumber, boolean isEmpty) {
        this.slotNumber = slotNumber;
        this.isEmpty = isEmpty;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "slotNumber=" + slotNumber +
                ", isEmpty=" + isEmpty +
                '}';
    }
}
