package ru.job4j.isp.menu;

import lombok.AllArgsConstructor;

import java.util.*;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        rootElements.add(new SimpleMenuItem(parentName, actionDelegate));
        rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> optionalItemInfo = findItem(itemName);
        if (optionalItemInfo.isPresent()) {
            ItemInfo currentItemInfo = optionalItemInfo.get();
            currentItemInfo.menuItem.getActionDelegate().delegate();
            return Optional.of(new MenuItemInfo(currentItemInfo.menuItem, currentItemInfo.number));
        }
        return Optional.empty();
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator dfsIterator = new DFSIterator();
        ItemInfo current;
        while (dfsIterator.hasNext()) {
            current = dfsIterator.next();
            if (current.menuItem.getName().equals(name)) {
                return Optional.of(current);
            }
        }
        return Optional.empty();
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator dfsIterator = new DFSIterator();
        Iterator<MenuItemInfo> iterator = new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo currentItemInfo = dfsIterator.next();
                return new MenuItemInfo(currentItemInfo.menuItem, currentItemInfo.number);
            }
        };
        return iterator;
    }

    private static class SimpleMenuItem implements MenuItem {

        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious(); ) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    @AllArgsConstructor
    private class ItemInfo {
        MenuItem menuItem;
        String number;
    }
}