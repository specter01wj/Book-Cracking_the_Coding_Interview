"use strict";
//====================================================
// Solution
//====================================================
class Animal {
    order;
    name;
    constructor(name) {
        this.name = name;
        this.order = 0;
    }
    setOrder(order) {
        this.order = order;
    }
    getOrder() {
        return this.order;
    }
    /*
     * Compare orders of animals to return the older item.
     */
    isOlderThan(animal) {
        return this.order < animal.getOrder();
    }
    getName() {
        return this.name;
    }
    toString() {
        return this.constructor.name
            + "(" + this.name
            + ", order=" + this.order + ")";
    }
}
class Dog extends Animal {
    constructor(name) {
        super(name);
    }
}
class Cat extends Animal {
    constructor(name) {
        super(name);
    }
}
class AnimalQueue {
    dogs;
    cats;
    /*
     * Acts as a timestamp.
     */
    order;
    constructor() {
        this.dogs = [];
        this.cats = [];
        this.order = 0;
    }
    enqueue(animal) {
        /*
         * Order is used as a sort of timestamp,
         * so that we can compare the insertion
         * order of a dog to a cat.
         */
        animal.setOrder(this.order);
        this.order++;
        if (animal instanceof Dog) {
            this.dogs.push(animal);
        }
        else if (animal instanceof Cat) {
            this.cats.push(animal);
        }
        else {
            throw new Error("Animal must be a Dog or Cat.");
        }
    }
    dequeueAny() {
        /*
         * Look at the heads of the dog and cat queues
         * and dequeue whichever animal is oldest.
         */
        if (this.dogs.length === 0 &&
            this.cats.length === 0) {
            throw new Error("Shelter is empty.");
        }
        if (this.dogs.length === 0) {
            return this.dequeueCat();
        }
        if (this.cats.length === 0) {
            return this.dequeueDog();
        }
        const dog = this.dogs[0];
        const cat = this.cats[0];
        if (dog.isOlderThan(cat)) {
            return this.dequeueDog();
        }
        else {
            return this.dequeueCat();
        }
    }
    dequeueDog() {
        if (this.dogs.length === 0) {
            throw new Error("No dogs available.");
        }
        return this.dogs.shift();
    }
    dequeueCat() {
        if (this.cats.length === 0) {
            throw new Error("No cats available.");
        }
        return this.cats.shift();
    }
    size() {
        return this.dogs.length + this.cats.length;
    }
    dogSize() {
        return this.dogs.length;
    }
    catSize() {
        return this.cats.length;
    }
}
//====================================================
// Tests
//====================================================
let output = "";
output += "<b>>>> CTCI Chapter 3.6 - Animal Shelter <<<</b><br><br>";
//====================================================
// Test 1: Enqueue animals
//====================================================
output += "<b>Test 1: Enqueue animals</b><br>";
const shelter1 = new AnimalQueue();
shelter1.enqueue(new Dog("Dog-A"));
shelter1.enqueue(new Cat("Cat-A"));
shelter1.enqueue(new Dog("Dog-B"));
shelter1.enqueue(new Cat("Cat-B"));
shelter1.enqueue(new Dog("Dog-C"));
output += "Shelter size: " + shelter1.size() + "<br>";
output += "Dog count: " + shelter1.dogSize() + "<br>";
output += "Cat count: " + shelter1.catSize() + "<br><br>";
//====================================================
// Test 2: dequeueAny()
//====================================================
output += "<b>Test 2: dequeueAny()</b><br>";
const shelter2 = new AnimalQueue();
shelter2.enqueue(new Dog("Dog-A")); // order 0
shelter2.enqueue(new Cat("Cat-A")); // order 1
shelter2.enqueue(new Dog("Dog-B")); // order 2
shelter2.enqueue(new Cat("Cat-B")); // order 3
output += "Adopt: " + shelter2.dequeueAny().toString() + "<br>";
output += "Adopt: " + shelter2.dequeueAny().toString() + "<br>";
output += "Adopt: " + shelter2.dequeueAny().toString() + "<br>";
output += "Adopt: " + shelter2.dequeueAny().toString() + "<br><br>";
//====================================================
// Test 3: dequeueDog()
//====================================================
output += "<b>Test 3: dequeueDog()</b><br>";
const shelter3 = new AnimalQueue();
shelter3.enqueue(new Cat("Cat-A"));
shelter3.enqueue(new Dog("Dog-A"));
shelter3.enqueue(new Cat("Cat-B"));
shelter3.enqueue(new Dog("Dog-B"));
shelter3.enqueue(new Dog("Dog-C"));
output += "Adopt dog: "
    + shelter3.dequeueDog().toString() + "<br>";
output += "Adopt dog: "
    + shelter3.dequeueDog().toString() + "<br>";
output += "Adopt dog: "
    + shelter3.dequeueDog().toString() + "<br><br>";
//====================================================
// Test 4: dequeueCat()
//====================================================
output += "<b>Test 4: dequeueCat()</b><br>";
const shelter4 = new AnimalQueue();
shelter4.enqueue(new Dog("Dog-A"));
shelter4.enqueue(new Cat("Cat-A"));
shelter4.enqueue(new Dog("Dog-B"));
shelter4.enqueue(new Cat("Cat-B"));
shelter4.enqueue(new Cat("Cat-C"));
output += "Adopt cat: "
    + shelter4.dequeueCat().toString() + "<br>";
output += "Adopt cat: "
    + shelter4.dequeueCat().toString() + "<br>";
output += "Adopt cat: "
    + shelter4.dequeueCat().toString() + "<br><br>";
//====================================================
// Test 5: Mixed dequeue operations
//====================================================
output += "<b>Test 5: Mixed dequeue operations</b><br>";
const shelter5 = new AnimalQueue();
shelter5.enqueue(new Dog("Dog-A")); // 0
shelter5.enqueue(new Cat("Cat-A")); // 1
shelter5.enqueue(new Dog("Dog-B")); // 2
shelter5.enqueue(new Cat("Cat-B")); // 3
shelter5.enqueue(new Dog("Dog-C")); // 4
shelter5.enqueue(new Cat("Cat-C")); // 5
output += "dequeueDog: "
    + shelter5.dequeueDog().toString() + "<br>";
output += "dequeueAny: "
    + shelter5.dequeueAny().toString() + "<br>";
output += "dequeueCat: "
    + shelter5.dequeueCat().toString() + "<br>";
output += "dequeueAny: "
    + shelter5.dequeueAny().toString() + "<br>";
output += "dequeueAny: "
    + shelter5.dequeueAny().toString() + "<br>";
output += "dequeueAny: "
    + shelter5.dequeueAny().toString() + "<br><br>";
//====================================================
// Test 6: Only dogs remain
//====================================================
output += "<b>Test 6: Only dogs remain</b><br>";
const shelter6 = new AnimalQueue();
shelter6.enqueue(new Cat("Cat-A"));
shelter6.enqueue(new Dog("Dog-A"));
shelter6.enqueue(new Dog("Dog-B"));
output += "Adopt cat: "
    + shelter6.dequeueCat().toString() + "<br>";
output += "dequeueAny: "
    + shelter6.dequeueAny().toString() + "<br>";
output += "dequeueAny: "
    + shelter6.dequeueAny().toString() + "<br><br>";
//====================================================
// Test 7: Only cats remain
//====================================================
output += "<b>Test 7: Only cats remain</b><br>";
const shelter7 = new AnimalQueue();
shelter7.enqueue(new Dog("Dog-A"));
shelter7.enqueue(new Cat("Cat-A"));
shelter7.enqueue(new Cat("Cat-B"));
output += "Adopt dog: "
    + shelter7.dequeueDog().toString() + "<br>";
output += "dequeueAny: "
    + shelter7.dequeueAny().toString() + "<br>";
output += "dequeueAny: "
    + shelter7.dequeueAny().toString() + "<br><br>";
//====================================================
// Test 8: Empty shelter
//====================================================
output += "<b>Test 8: Empty shelter</b><br>";
const shelter8 = new AnimalQueue();
try {
    shelter8.dequeueAny();
}
catch (error) {
    output += "Caught expected error: "
        + error.message + "<br>";
}
output += "<br>";
//====================================================
// Test 9: No dogs available
//====================================================
output += "<b>Test 9: No dogs available</b><br>";
const shelter9 = new AnimalQueue();
shelter9.enqueue(new Cat("Cat-A"));
try {
    shelter9.dequeueDog();
}
catch (error) {
    output += "Caught expected error: "
        + error.message + "<br>";
}
output += "<br>";
//====================================================
// Test 10: No cats available
//====================================================
output += "<b>Test 10: No cats available</b><br>";
const shelter10 = new AnimalQueue();
shelter10.enqueue(new Dog("Dog-A"));
try {
    shelter10.dequeueCat();
}
catch (error) {
    output += "Caught expected error: "
        + error.message + "<br>";
}
output += "<br><b>Study Complete.</b>";
document.querySelector("#t1").innerHTML = output;
