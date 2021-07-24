package lesson3hw.fruitBoxes;

import java.util.Arrays;

public class Box<T extends Fruit> {
    private T[] boxContent;
    private T[] boxContentBuffer;

    public Box(T[] boxContent) {
        this.boxContent = boxContent;
    }

    public T[] getBoxContent() {
        return boxContent;
    }

    @Override
    public String toString() {
        return "Box{" +
                "boxContent=" + Arrays.toString(boxContent) +
                '}';
    }

    public float getWeight() {
        if (boxContent[0] != null) {
            return boxContent.length * boxContent[0].getFruitWeight();
        } else return 0;
    }

    public boolean compare(Box<?> boxToCompare) {
        if (this.getBoxContent()[0] != null && boxToCompare.getBoxContent()[0] != null) {
            return this.getWeight() == boxToCompare.getWeight();
        } else return false;
    }

    private void addFruitToBox(T itemToAdd) {
        for (int i = 0; i < boxContent.length; i++) {
            if (boxContent[i] == null) {
                boxContent[i] = itemToAdd;
                return;
            }
        }
    }

    public void moveBoxContent(Box<T> boxToMoveTo) {
        if (this.boxContent[0] == null || this == boxToMoveTo) {
            return;
        }
        boxToMoveTo.boxContent = Arrays.copyOf(boxToMoveTo.boxContent, boxToMoveTo.boxContent.length + this.boxContent.length);
        for (int i = 0; i < boxContent.length; i++) {
            boxToMoveTo.addFruitToBox(boxContent[i]);
        }
        boxContent = Arrays.copyOf(boxContent, 0);
    }
}
