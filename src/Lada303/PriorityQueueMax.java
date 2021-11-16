package Lada303;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PriorityQueueMax {

    private final List<Integer> binaryMaxTree = new ArrayList<>();

    void insert(Integer value) {
        binaryMaxTree.add(value);
        shiftUp(binaryMaxTree.size());
    }

    void extractMax() {
        if (binaryMaxTree.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.println(binaryMaxTree.get(0));
        binaryMaxTree.set(0, binaryMaxTree.get(binaryMaxTree.size() - 1));
        binaryMaxTree.remove(binaryMaxTree.size() - 1);
        shiftDown(1);
    }

    void shiftDown(Integer index) {
        if (2 * index > binaryMaxTree.size()) return;
        Integer cash;
        int indexSon;
        if (2 * index == binaryMaxTree.size()) {
            indexSon = 2*index;
        } else {
            indexSon = binaryMaxTree.get(2 * index - 1) >  binaryMaxTree.get(2 * index) ? 2 * index : 2 * index + 1;
        }
        if (binaryMaxTree.get(indexSon - 1) > binaryMaxTree.get(index - 1)) {
            cash = binaryMaxTree.get(index - 1);
            binaryMaxTree.set(index - 1, binaryMaxTree.get(indexSon - 1));
            binaryMaxTree.set(indexSon - 1, cash);
        }
        shiftDown(indexSon);
    }

    void shiftUp(Integer index) {
        if (index == 1) return;
        int indexParent = index / 2;
        Integer cash;
        if (binaryMaxTree.get(index - 1) > binaryMaxTree.get(indexParent - 1)) {
            cash = binaryMaxTree.get(index - 1);
            binaryMaxTree.set(index - 1, binaryMaxTree.get(indexParent - 1));
            binaryMaxTree.set(indexParent - 1, cash);
        }
        shiftUp(indexParent);
    }

    @Override
    public String toString() {
        return "PriorityQueueMax{" +
                "binaryMaxTree=" + binaryMaxTree +
                '}';
    }
}
