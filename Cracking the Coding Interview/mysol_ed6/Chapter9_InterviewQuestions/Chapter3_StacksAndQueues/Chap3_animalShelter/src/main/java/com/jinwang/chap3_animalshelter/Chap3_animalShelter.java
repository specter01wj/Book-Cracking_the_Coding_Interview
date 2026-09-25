
package com.jinwang.chap3_animalshelter;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Chap3_animalShelter {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 3.6 - Animal Shelter <<<");
        System.out.println();


        //====================================================
        // Test 1: Enqueue animals
        //====================================================

        System.out.println("Test 1: Enqueue animals");

        AnimalQueue shelter1 = new AnimalQueue();

        shelter1.enqueue(new Dog("Dog-A"));
        shelter1.enqueue(new Cat("Cat-A"));
        shelter1.enqueue(new Dog("Dog-B"));
        shelter1.enqueue(new Cat("Cat-B"));
        shelter1.enqueue(new Dog("Dog-C"));

        System.out.println("Shelter size: " + shelter1.size());
        System.out.println("Dog count: " + shelter1.dogSize());
        System.out.println("Cat count: " + shelter1.catSize());

        System.out.println();


        //====================================================
        // Test 2: dequeueAny()
        //====================================================

        System.out.println("Test 2: dequeueAny()");

        AnimalQueue shelter2 = new AnimalQueue();

        shelter2.enqueue(new Dog("Dog-A")); // order 0
        shelter2.enqueue(new Cat("Cat-A")); // order 1
        shelter2.enqueue(new Dog("Dog-B")); // order 2
        shelter2.enqueue(new Cat("Cat-B")); // order 3

        System.out.println("Adopt: " + shelter2.dequeueAny());
        System.out.println("Adopt: " + shelter2.dequeueAny());
        System.out.println("Adopt: " + shelter2.dequeueAny());
        System.out.println("Adopt: " + shelter2.dequeueAny());

        System.out.println();


        //====================================================
        // Test 3: dequeueDog()
        //====================================================

        System.out.println("Test 3: dequeueDog()");

        AnimalQueue shelter3 = new AnimalQueue();

        shelter3.enqueue(new Cat("Cat-A"));
        shelter3.enqueue(new Dog("Dog-A"));
        shelter3.enqueue(new Cat("Cat-B"));
        shelter3.enqueue(new Dog("Dog-B"));
        shelter3.enqueue(new Dog("Dog-C"));

        System.out.println("Adopt dog: " + shelter3.dequeueDog());
        System.out.println("Adopt dog: " + shelter3.dequeueDog());
        System.out.println("Adopt dog: " + shelter3.dequeueDog());

        System.out.println();


        //====================================================
        // Test 4: dequeueCat()
        //====================================================

        System.out.println("Test 4: dequeueCat()");

        AnimalQueue shelter4 = new AnimalQueue();

        shelter4.enqueue(new Dog("Dog-A"));
        shelter4.enqueue(new Cat("Cat-A"));
        shelter4.enqueue(new Dog("Dog-B"));
        shelter4.enqueue(new Cat("Cat-B"));
        shelter4.enqueue(new Cat("Cat-C"));

        System.out.println("Adopt cat: " + shelter4.dequeueCat());
        System.out.println("Adopt cat: " + shelter4.dequeueCat());
        System.out.println("Adopt cat: " + shelter4.dequeueCat());

        System.out.println();


        //====================================================
        // Test 5: Mixed dequeue operations
        //====================================================

        System.out.println("Test 5: Mixed dequeue operations");

        AnimalQueue shelter5 = new AnimalQueue();

        shelter5.enqueue(new Dog("Dog-A")); // 0
        shelter5.enqueue(new Cat("Cat-A")); // 1
        shelter5.enqueue(new Dog("Dog-B")); // 2
        shelter5.enqueue(new Cat("Cat-B")); // 3
        shelter5.enqueue(new Dog("Dog-C")); // 4
        shelter5.enqueue(new Cat("Cat-C")); // 5

        System.out.println("dequeueDog: " + shelter5.dequeueDog());
        // Dog-A

        System.out.println("dequeueAny: " + shelter5.dequeueAny());
        // Cat-A

        System.out.println("dequeueCat: " + shelter5.dequeueCat());
        // Cat-B

        System.out.println("dequeueAny: " + shelter5.dequeueAny());
        // Dog-B

        System.out.println("dequeueAny: " + shelter5.dequeueAny());
        // Dog-C

        System.out.println("dequeueAny: " + shelter5.dequeueAny());
        // Cat-C

        System.out.println();


        //====================================================
        // Test 6: Only dogs remain
        //====================================================

        System.out.println("Test 6: Only dogs remain");

        AnimalQueue shelter6 = new AnimalQueue();

        shelter6.enqueue(new Cat("Cat-A"));
        shelter6.enqueue(new Dog("Dog-A"));
        shelter6.enqueue(new Dog("Dog-B"));

        System.out.println("Adopt cat: " + shelter6.dequeueCat());

        System.out.println("dequeueAny: " + shelter6.dequeueAny());
        System.out.println("dequeueAny: " + shelter6.dequeueAny());

        System.out.println();


        //====================================================
        // Test 7: Only cats remain
        //====================================================

        System.out.println("Test 7: Only cats remain");

        AnimalQueue shelter7 = new AnimalQueue();

        shelter7.enqueue(new Dog("Dog-A"));
        shelter7.enqueue(new Cat("Cat-A"));
        shelter7.enqueue(new Cat("Cat-B"));

        System.out.println("Adopt dog: " + shelter7.dequeueDog());

        System.out.println("dequeueAny: " + shelter7.dequeueAny());
        System.out.println("dequeueAny: " + shelter7.dequeueAny());

        System.out.println();


        //====================================================
        // Test 8: Empty shelter
        //====================================================

        System.out.println("Test 8: Empty shelter");

        AnimalQueue shelter8 = new AnimalQueue();

        try {
            shelter8.dequeueAny();
        } catch (NoSuchElementException e) {
            System.out.println(
                    "Caught expected exception: " + e.getMessage());
        }

        System.out.println();


        //====================================================
        // Test 9: No dogs available
        //====================================================

        System.out.println("Test 9: No dogs available");

        AnimalQueue shelter9 = new AnimalQueue();

        shelter9.enqueue(new Cat("Cat-A"));

        try {
            shelter9.dequeueDog();
        } catch (NoSuchElementException e) {
            System.out.println(
                    "Caught expected exception: " + e.getMessage());
        }

        System.out.println();


        //====================================================
        // Test 10: No cats available
        //====================================================

        System.out.println("Test 10: No cats available");

        AnimalQueue shelter10 = new AnimalQueue();

        shelter10.enqueue(new Dog("Dog-A"));

        try {
            shelter10.dequeueCat();
        } catch (NoSuchElementException e) {
            System.out.println(
                    "Caught expected exception: " + e.getMessage());
        }

        System.out.println();

        System.out.println("Study Complete.");
    }
    
    //====================================================
    // Solution
    //====================================================

    static abstract class Animal {

        private int order;
        protected String name;

        public Animal(String name) {
            this.name = name;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }

        /*
         * Compare orders of animals to return the older item.
         */
        public boolean isOlderThan(Animal animal) {
            return this.order < animal.getOrder();
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName()
                    + "(" + name
                    + ", order=" + order + ")";
        }
    }


    static class Dog extends Animal {

        public Dog(String name) {
            super(name);
        }
    }


    static class Cat extends Animal {

        public Cat(String name) {
            super(name);
        }
    }


    static class AnimalQueue {

        private final LinkedList<Dog> dogs;
        private final LinkedList<Cat> cats;

        /*
         * Acts as a timestamp.
         */
        private int order;

        public AnimalQueue() {
            dogs = new LinkedList<>();
            cats = new LinkedList<>();
            order = 0;
        }

        public void enqueue(Animal animal) {

            /*
             * Order is used as a sort of timestamp,
             * so that we can compare the insertion
             * order of a dog to a cat.
             */
            animal.setOrder(order);
            order++;

            if (animal instanceof Dog) {
                dogs.addLast((Dog) animal);
            } else if (animal instanceof Cat) {
                cats.addLast((Cat) animal);
            } else {
                throw new IllegalArgumentException(
                        "Animal must be a Dog or Cat.");
            }
        }

        public Animal dequeueAny() {

            /*
             * Look at the heads of the dog and cat queues
             * and dequeue whichever animal is oldest.
             */

            if (dogs.isEmpty() && cats.isEmpty()) {
                throw new NoSuchElementException(
                        "Shelter is empty.");
            }

            if (dogs.isEmpty()) {
                return dequeueCat();
            }

            if (cats.isEmpty()) {
                return dequeueDog();
            }

            Dog dog = dogs.peek();
            Cat cat = cats.peek();

            if (dog.isOlderThan(cat)) {
                return dequeueDog();
            } else {
                return dequeueCat();
            }
        }

        public Dog dequeueDog() {

            if (dogs.isEmpty()) {
                throw new NoSuchElementException(
                        "No dogs available.");
            }

            return dogs.poll();
        }

        public Cat dequeueCat() {

            if (cats.isEmpty()) {
                throw new NoSuchElementException(
                        "No cats available.");
            }

            return cats.poll();
        }

        public int size() {
            return dogs.size() + cats.size();
        }

        public int dogSize() {
            return dogs.size();
        }

        public int catSize() {
            return cats.size();
        }
    }
}
