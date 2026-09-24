//====================================================
// Solution
//====================================================

class MyQueue<T> {

    private readonly stackNewest: T[];
    private readonly stackOldest: T[];

    constructor() {
        this.stackNewest = [];
        this.stackOldest = [];
    }

    size(): number {
        return this.stackNewest.length + this.stackOldest.length;
    }

    add(value: T): void {

        /*
         * Push onto stackNewest, which always has
         * the newest elements on top.
         */
        this.stackNewest.push(value);
    }

    /*
     * Move elements from stackNewest into stackOldest.
     *
     * This is done only when stackOldest is empty.
     * Moving the elements reverses their order, placing
     * the oldest element on top of stackOldest.
     */
    private shiftStacks(): void {

        if (this.stackOldest.length === 0) {

            while (this.stackNewest.length !== 0) {
                const value: T | undefined = this.stackNewest.pop();

                if (value !== undefined) {
                    this.stackOldest.push(value);
                }
            }
        }
    }

    peek(): T {

        // Ensure stackOldest has the oldest element on top.
        this.shiftStacks();

        if (this.stackOldest.length === 0) {
            throw new Error("Queue is empty.");
        }

        return this.stackOldest[this.stackOldest.length - 1];
    }

    remove(): T {

        // Ensure stackOldest has the oldest element on top.
        this.shiftStacks();

        if (this.stackOldest.length === 0) {
            throw new Error("Queue is empty.");
        }

        return this.stackOldest.pop() as T;
    }
}


//====================================================
// Tests
//====================================================

let output: string = "";

output += "<b>>>> CTCI Chapter 3.4 - Queue via Stacks <<<</b><br><br>";


//====================================================
// Test 1: Basic FIFO behavior
//====================================================

output += "<b>Test 1: Basic FIFO behavior</b><br>";

const queue1: MyQueue<number> = new MyQueue<number>();

queue1.add(1);
queue1.add(2);
queue1.add(3);
queue1.add(4);
queue1.add(5);

output += "Queue size: " + queue1.size() + "<br>";
output += "Peek: " + queue1.peek() + "<br>";

output += "Remove: " + queue1.remove() + "<br>";
output += "Remove: " + queue1.remove() + "<br>";
output += "Remove: " + queue1.remove() + "<br>";

output += "Queue size: " + queue1.size() + "<br><br>";


//====================================================
// Test 2: Add after remove
//====================================================

output += "<b>Test 2: Add after remove</b><br>";

const queue2: MyQueue<number> = new MyQueue<number>();

queue2.add(10);
queue2.add(20);
queue2.add(30);

output += "Remove: " + queue2.remove() + "<br>"; // 10

queue2.add(40);
queue2.add(50);

output += "Remove: " + queue2.remove() + "<br>"; // 20
output += "Remove: " + queue2.remove() + "<br>"; // 30
output += "Remove: " + queue2.remove() + "<br>"; // 40
output += "Remove: " + queue2.remove() + "<br><br>"; // 50


//====================================================
// Test 3: Peek should not remove
//====================================================

output += "<b>Test 3: Peek should not remove</b><br>";

const queue3: MyQueue<number> = new MyQueue<number>();

queue3.add(100);
queue3.add(200);
queue3.add(300);

output += "Size before peek: " + queue3.size() + "<br>";
output += "Peek: " + queue3.peek() + "<br>";
output += "Peek again: " + queue3.peek() + "<br>";
output += "Size after peek: " + queue3.size() + "<br>";

output += "Remove: " + queue3.remove() + "<br><br>";


//====================================================
// Test 4: Interleaved operations
//====================================================

output += "<b>Test 4: Interleaved operations</b><br>";

const queue4: MyQueue<number> = new MyQueue<number>();

queue4.add(1);
queue4.add(2);

output += "Remove: " + queue4.remove() + "<br>"; // 1

queue4.add(3);
queue4.add(4);

output += "Remove: " + queue4.remove() + "<br>"; // 2

queue4.add(5);

output += "Remove: " + queue4.remove() + "<br>"; // 3
output += "Remove: " + queue4.remove() + "<br>"; // 4
output += "Remove: " + queue4.remove() + "<br><br>"; // 5


//====================================================
// Test 5: Generic type
//====================================================

output += "<b>Test 5: Generic type</b><br>";

const queue5: MyQueue<string> = new MyQueue<string>();

queue5.add("A");
queue5.add("B");
queue5.add("C");

output += "Remove: " + queue5.remove() + "<br>";
output += "Remove: " + queue5.remove() + "<br>";
output += "Remove: " + queue5.remove() + "<br><br>";


//====================================================
// Test 6: Remove from empty queue
//====================================================

output += "<b>Test 6: Remove from empty queue</b><br>";

const queue6: MyQueue<number> = new MyQueue<number>();

try {
    queue6.remove();
} catch (error) {
    output += "Caught expected error: "
        + (error as Error).message + "<br>";
}

output += "<br>";


//====================================================
// Test 7: Peek empty queue
//====================================================

output += "<b>Test 7: Peek empty queue</b><br>";

const queue7: MyQueue<number> = new MyQueue<number>();

try {
    queue7.peek();
} catch (error) {
    output += "Caught expected error: "
        + (error as Error).message + "<br>";
}

output += "<br><b>Study Complete.</b>";


(document.querySelector("#t1") as HTMLElement).innerHTML = output;