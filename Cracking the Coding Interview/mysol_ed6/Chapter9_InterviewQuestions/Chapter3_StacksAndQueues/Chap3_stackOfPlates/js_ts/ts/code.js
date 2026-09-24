class PlateNode {
    value;
    above;
    below;
    constructor(value) {
        this.value = value;
        this.above = null;
        this.below = null;
    }
}
//====================================================
// Solution Helper (Book)
// Individual Sub-Stack
//====================================================
class PlateStack {
    capacity;
    top;
    bottom;
    size;
    constructor(capacity) {
        this.capacity = capacity;
        this.top = null;
        this.bottom = null;
        this.size = 0;
    }
    isFull() {
        return this.size === this.capacity;
    }
    isEmpty() {
        return this.size === 0;
    }
    join(above, below) {
        if (below !== null) {
            below.above = above;
        }
        if (above !== null) {
            above.below = below;
        }
    }
    push(value) {
        if (this.size >= this.capacity) {
            return false;
        }
        this.size++;
        const node = new PlateNode(value);
        if (this.size === 1) {
            this.bottom = node;
        }
        this.join(node, this.top);
        this.top = node;
        return true;
    }
    pop() {
        if (this.top === null) {
            throw new Error("Stack is empty.");
        }
        const oldTop = this.top;
        this.top =
            this.top.below;
        if (this.top !== null) {
            this.top.above = null;
        }
        else {
            this.bottom = null;
        }
        this.size--;
        return oldTop.value;
    }
    removeBottom() {
        if (this.bottom === null) {
            throw new Error("Stack is empty.");
        }
        const oldBottom = this.bottom;
        this.bottom =
            this.bottom.above;
        if (this.bottom !== null) {
            this.bottom.below = null;
        }
        else {
            this.top = null;
        }
        this.size--;
        return oldBottom.value;
    }
    toString() {
        const values = [];
        let current = this.bottom;
        while (current !== null) {
            values.push(current.value);
            current =
                current.above;
        }
        return `[${values.join(", ")}]`;
    }
}
//====================================================
// Solution (Book)
// SetOfStacks
//====================================================
class SetOfStacks {
    stacks;
    capacity;
    constructor(capacity) {
        if (capacity <= 0) {
            throw new Error("Capacity must be greater than 0.");
        }
        this.capacity = capacity;
        this.stacks = [];
    }
    getLastStack() {
        if (this.stacks.length === 0) {
            return null;
        }
        return this.stacks[this.stacks.length - 1];
    }
    push(value) {
        const last = this.getLastStack();
        if (last !== null &&
            !last.isFull()) {
            last.push(value);
        }
        else {
            const stack = new PlateStack(this.capacity);
            stack.push(value);
            this.stacks.push(stack);
        }
    }
    pop() {
        const last = this.getLastStack();
        if (last === null) {
            throw new Error("SetOfStacks is empty.");
        }
        const value = last.pop();
        if (last.isEmpty()) {
            this.stacks.pop();
        }
        return value;
    }
    //====================================================
    // popAt
    // Book Follow Up
    //====================================================
    popAt(index) {
        if (index < 0 ||
            index >= this.stacks.length) {
            throw new Error(`Invalid stack index: ${index}`);
        }
        return this.leftShift(index, true);
    }
    //====================================================
    // leftShift
    // Book Rollover Algorithm
    //====================================================
    leftShift(index, removeTop) {
        const stack = this.stacks[index];
        let removedItem;
        if (removeTop) {
            removedItem =
                stack.pop();
        }
        else {
            removedItem =
                stack.removeBottom();
        }
        if (stack.isEmpty()) {
            this.stacks.splice(index, 1);
        }
        else if (this.stacks.length > index + 1) {
            const value = this.leftShift(index + 1, false);
            stack.push(value);
        }
        return removedItem;
    }
    numberOfStacks() {
        return this.stacks.length;
    }
    toString() {
        return `[${this.stacks
            .map((stack) => stack.toString())
            .join(", ")}]`;
    }
}
//====================================================
// Tests
//====================================================
let output = ">>> CTCI Chapter 3.3 - Stack of Plates <<<<br><br>";
//====================================================
// Test 1
// Push - Create Multiple Sub-Stacks
//====================================================
output +=
    "<b>========== Test 1 : Push / Multiple Stacks ==========</b><br><br>";
const stacks = new SetOfStacks(3);
for (let i = 1; i <= 10; i++) {
    output +=
        `Push : ${i}<br>`;
    stacks.push(i);
    output +=
        `Stacks : ${stacks.toString()}<br>`;
}
output += "<br>";
output +=
    `Number of sub-stacks : ${stacks.numberOfStacks()}<br><br>`;
//====================================================
// Test 2
// Normal Pop
//====================================================
output +=
    "<b>========== Test 2 : Normal Pop ==========</b><br><br>";
output +=
    `Before : ${stacks.toString()}<br>`;
output +=
    `Pop : ${stacks.pop()}<br>`;
output +=
    `After : ${stacks.toString()}<br><br>`;
output +=
    `Pop : ${stacks.pop()}<br>`;
output +=
    `After : ${stacks.toString()}<br><br>`;
//====================================================
// Test 3
// popAt(index) - Book Follow Up
//====================================================
output +=
    "<b>========== Test 3 : popAt(index) / Rollover ==========</b><br><br>";
const rolloverStacks = new SetOfStacks(3);
for (let i = 1; i <= 10; i++) {
    rolloverStacks.push(i);
}
output +=
    `Before         : ${rolloverStacks.toString()}<br>`;
output +=
    `popAt(0)       : ${rolloverStacks.popAt(0)}<br>`;
output +=
    `After rollover : ${rolloverStacks.toString()}<br><br>`;
//====================================================
// Test 4
// popAt Middle Stack
//====================================================
output +=
    "<b>========== Test 4 : popAt Middle Stack ==========</b><br><br>";
const middleStacks = new SetOfStacks(3);
for (let i = 1; i <= 10; i++) {
    middleStacks.push(i);
}
output +=
    `Before         : ${middleStacks.toString()}<br>`;
output +=
    `popAt(1)       : ${middleStacks.popAt(1)}<br>`;
output +=
    `After rollover : ${middleStacks.toString()}<br><br>`;
//====================================================
// Test 5
// Pop Removes Empty Last Stack
//====================================================
output +=
    "<b>========== Test 5 : Remove Empty Last Stack ==========</b><br><br>";
const smallStacks = new SetOfStacks(2);
smallStacks.push(10);
smallStacks.push(20);
smallStacks.push(30);
output +=
    `Before : ${smallStacks.toString()}<br>`;
output +=
    `Pop : ${smallStacks.pop()}<br>`;
output +=
    `After : ${smallStacks.toString()}<br>`;
output +=
    `Number of sub-stacks : ${smallStacks.numberOfStacks()}<br><br>`;
//====================================================
// Test 6
// Empty Stack
//====================================================
output +=
    "<b>========== Test 6 : Empty Stack ==========</b><br><br>";
const emptyStacks = new SetOfStacks(3);
try {
    emptyStacks.pop();
}
catch (error) {
    output +=
        `Pop from empty SetOfStacks -> ${error.message}<br>`;
}
output += "<br>";
//====================================================
// Test 7
// Invalid popAt Index
//====================================================
output +=
    "<b>========== Test 7 : Invalid popAt Index ==========</b><br><br>";
const invalidStacks = new SetOfStacks(3);
invalidStacks.push(1);
invalidStacks.push(2);
invalidStacks.push(3);
try {
    invalidStacks.popAt(5);
}
catch (error) {
    output +=
        `popAt(5) -> ${error.message}<br>`;
}
output += "<br>";
output +=
    "<b>Study Complete.</b>";
document.querySelector("#t1").innerHTML =
    output;
