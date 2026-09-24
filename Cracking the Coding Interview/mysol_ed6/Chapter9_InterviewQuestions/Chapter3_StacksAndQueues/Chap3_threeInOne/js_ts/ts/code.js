class FixedMultiStack {
    NUMBER_OF_STACKS = 3;
    stackCapacity;
    values;
    sizes;
    //====================================================
    // Solution 1 (Book)
    // Fixed Division
    //====================================================
    constructor(stackSize) {
        if (stackSize <= 0) {
            throw new Error("Stack size must be greater than 0.");
        }
        this.stackCapacity = stackSize;
        this.values =
            new Array(stackSize * this.NUMBER_OF_STACKS).fill(0);
        this.sizes =
            new Array(this.NUMBER_OF_STACKS).fill(0);
    }
    push(stackNum, value) {
        this.validateStackNum(stackNum);
        if (this.isFull(stackNum)) {
            throw new Error(`Stack ${stackNum} is full.`);
        }
        this.sizes[stackNum]++;
        this.values[this.indexOfTop(stackNum)] = value;
    }
    pop(stackNum) {
        this.validateStackNum(stackNum);
        if (this.isEmpty(stackNum)) {
            throw new Error(`Stack ${stackNum} is empty.`);
        }
        const topIndex = this.indexOfTop(stackNum);
        const value = this.values[topIndex];
        this.values[topIndex] = 0;
        this.sizes[stackNum]--;
        return value;
    }
    peek(stackNum) {
        this.validateStackNum(stackNum);
        if (this.isEmpty(stackNum)) {
            throw new Error(`Stack ${stackNum} is empty.`);
        }
        return this.values[this.indexOfTop(stackNum)];
    }
    isEmpty(stackNum) {
        this.validateStackNum(stackNum);
        return this.sizes[stackNum] === 0;
    }
    isFull(stackNum) {
        this.validateStackNum(stackNum);
        return this.sizes[stackNum]
            === this.stackCapacity;
    }
    size(stackNum) {
        this.validateStackNum(stackNum);
        return this.sizes[stackNum];
    }
    indexOfTop(stackNum) {
        const offset = stackNum * this.stackCapacity;
        const size = this.sizes[stackNum];
        return offset + size - 1;
    }
    validateStackNum(stackNum) {
        if (stackNum < 0 ||
            stackNum >= this.NUMBER_OF_STACKS) {
            throw new Error("Stack number must be 0, 1, or 2.");
        }
    }
}
//====================================================
// StackInfo
// Used by Solution 2
//====================================================
class StackInfo {
    start;
    size = 0;
    capacity;
    owner;
    constructor(start, capacity, owner) {
        this.start = start;
        this.capacity = capacity;
        this.owner = owner;
    }
    isWithinStackCapacity(index) {
        if (index < 0 ||
            index >= this.owner.arrayLength()) {
            return false;
        }
        const contiguousIndex = index < this.start
            ? index + this.owner.arrayLength()
            : index;
        const end = this.start + this.capacity;
        return (this.start <= contiguousIndex &&
            contiguousIndex < end);
    }
    lastCapacityIndex() {
        return this.owner.adjustIndex(this.start + this.capacity - 1);
    }
    lastElementIndex() {
        return this.owner.adjustIndex(this.start + this.size - 1);
    }
    isFull() {
        return this.size === this.capacity;
    }
    isEmpty() {
        return this.size === 0;
    }
}
class FlexibleMultiStack {
    info;
    values;
    //====================================================
    // Solution 2 (Book)
    // Flexible Division
    //====================================================
    constructor(numberOfStacks, defaultSize) {
        if (numberOfStacks <= 0 ||
            defaultSize <= 0) {
            throw new Error("Number of stacks and stack size must be greater than 0.");
        }
        this.info =
            new Array(numberOfStacks);
        this.values =
            new Array(numberOfStacks * defaultSize).fill(0);
        for (let i = 0; i < numberOfStacks; i++) {
            this.info[i] =
                new StackInfo(defaultSize * i, defaultSize, this);
        }
    }
    push(stackNum, value) {
        this.validateStackNum(stackNum);
        if (this.allStacksAreFull()) {
            throw new Error("All stacks are full.");
        }
        const stack = this.info[stackNum];
        /*
         * If this stack is full,
         * borrow capacity from another stack.
         */
        if (stack.isFull()) {
            this.expand(stackNum);
        }
        stack.size++;
        this.values[stack.lastElementIndex()] = value;
    }
    pop(stackNum) {
        this.validateStackNum(stackNum);
        const stack = this.info[stackNum];
        if (stack.isEmpty()) {
            throw new Error(`Stack ${stackNum} is empty.`);
        }
        const topIndex = stack.lastElementIndex();
        const value = this.values[topIndex];
        this.values[topIndex] = 0;
        stack.size--;
        return value;
    }
    peek(stackNum) {
        this.validateStackNum(stackNum);
        const stack = this.info[stackNum];
        if (stack.isEmpty()) {
            throw new Error(`Stack ${stackNum} is empty.`);
        }
        return this.values[stack.lastElementIndex()];
    }
    isEmpty(stackNum) {
        this.validateStackNum(stackNum);
        return this.info[stackNum].isEmpty();
    }
    size(stackNum) {
        this.validateStackNum(stackNum);
        return this.info[stackNum].size;
    }
    //====================================================
    // Expand
    //====================================================
    expand(stackNum) {
        this.shift((stackNum + 1) % this.info.length);
        this.info[stackNum].capacity++;
    }
    //====================================================
    // Shift
    //====================================================
    shift(stackNum) {
        const stack = this.info[stackNum];
        /*
         * If this stack is full, shift the next stack
         * first so this stack can gain one position.
         */
        if (stack.size >= stack.capacity) {
            const nextStack = (stackNum + 1)
                % this.info.length;
            this.shift(nextStack);
            stack.capacity++;
        }
        /*
         * Shift all elements in this stack
         * one position to the right.
         */
        let index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(index)) {
            this.values[index] =
                this.values[this.previousIndex(index)];
            index =
                this.previousIndex(index);
        }
        /*
         * Clear the old starting position.
         */
        this.values[stack.start] = 0;
        /*
         * Move the start of the stack
         * one position forward.
         */
        stack.start =
            this.nextIndex(stack.start);
        /*
         * This stack gives one capacity position
         * to the previous stack.
         */
        stack.capacity--;
    }
    //====================================================
    // Helpers
    //====================================================
    numberOfElements() {
        let size = 0;
        for (const stack of this.info) {
            size += stack.size;
        }
        return size;
    }
    allStacksAreFull() {
        return this.numberOfElements()
            === this.values.length;
    }
    arrayLength() {
        return this.values.length;
    }
    adjustIndex(index) {
        const max = this.values.length;
        return ((index % max) + max) % max;
    }
    nextIndex(index) {
        return this.adjustIndex(index + 1);
    }
    previousIndex(index) {
        return this.adjustIndex(index - 1);
    }
    validateStackNum(stackNum) {
        if (stackNum < 0 ||
            stackNum >= this.info.length) {
            throw new Error(`Invalid stack number: ${stackNum}`);
        }
    }
    //====================================================
    // Display Helper
    //====================================================
    stateToString() {
        let result = `Array : [${this.values.join(", ")}]<br>`;
        for (let i = 0; i < this.info.length; i++) {
            const stack = this.info[i];
            result +=
                `Stack ${i} -> ` +
                    `start: ${stack.start}, ` +
                    `size: ${stack.size}, ` +
                    `capacity: ${stack.capacity}<br>`;
        }
        return result;
    }
}
//====================================================
// Tests
//====================================================
let output = ">>> CTCI Chapter 3.1 - Three in One <<<<br><br>";
//====================================================
// Solution 1
//====================================================
output +=
    "<b>========== Solution 1 : Fixed Division ==========</b><br><br>";
const fixedStacks = new FixedMultiStack(3);
output +=
    "Push values into all three stacks:<br>";
fixedStacks.push(0, 10);
fixedStacks.push(0, 20);
fixedStacks.push(0, 30);
fixedStacks.push(1, 100);
fixedStacks.push(1, 200);
fixedStacks.push(2, 1000);
output +=
    `Stack 0 top : ${fixedStacks.peek(0)}<br>`;
output +=
    `Stack 1 top : ${fixedStacks.peek(1)}<br>`;
output +=
    `Stack 2 top : ${fixedStacks.peek(2)}<br><br>`;
output +=
    `Pop Stack 0 : ${fixedStacks.pop(0)}<br>`;
output +=
    `Pop Stack 0 : ${fixedStacks.pop(0)}<br>`;
output +=
    `Stack 0 top : ${fixedStacks.peek(0)}<br><br>`;
output +=
    `Stack 0 size : ${fixedStacks.size(0)}<br>`;
output +=
    `Stack 1 size : ${fixedStacks.size(1)}<br>`;
output +=
    `Stack 2 size : ${fixedStacks.size(2)}<br><br>`;
output +=
    "Fill Stack 0 again:<br>";
fixedStacks.push(0, 40);
fixedStacks.push(0, 50);
output +=
    `Stack 0 top : ${fixedStacks.peek(0)}<br>`;
output +=
    `Stack 0 full: ${fixedStacks.isFull(0)}<br><br>`;
output +=
    "Try to push another value into full Stack 0:<br>";
try {
    fixedStacks.push(0, 60);
}
catch (error) {
    output +=
        `Exception : ${error.message}<br>`;
}
output += "<br>";
//====================================================
// Solution 2
//====================================================
output +=
    "<b>========== Solution 2 : Flexible Division ==========</b><br><br>";
const flexibleStacks = new FlexibleMultiStack(3, 2);
output +=
    "Initial capacity:<br>";
output +=
    flexibleStacks.stateToString();
output += "<br>";
output +=
    "Fill Stack 0:<br>";
flexibleStacks.push(0, 10);
flexibleStacks.push(0, 20);
output +=
    flexibleStacks.stateToString();
output += "<br>";
output +=
    "Push 30 into full Stack 0.<br>";
output +=
    "Stack 0 expands by shifting another stack.<br>";
flexibleStacks.push(0, 30);
output +=
    flexibleStacks.stateToString();
output += "<br>";
output +=
    "Push more values:<br>";
flexibleStacks.push(1, 100);
flexibleStacks.push(1, 200);
flexibleStacks.push(2, 1000);
output +=
    flexibleStacks.stateToString();
output += "<br>";
output +=
    `Stack 0 top : ${flexibleStacks.peek(0)}<br>`;
output +=
    `Stack 1 top : ${flexibleStacks.peek(1)}<br>`;
output +=
    `Stack 2 top : ${flexibleStacks.peek(2)}<br><br>`;
output +=
    `Pop Stack 0 : ${flexibleStacks.pop(0)}<br>`;
output +=
    `Stack 0 top : ${flexibleStacks.peek(0)}<br><br>`;
output +=
    flexibleStacks.stateToString();
output += "<br>";
output +=
    "Try to fill the remaining shared space:<br>";
flexibleStacks.push(2, 2000);
output +=
    flexibleStacks.stateToString();
output += "<br>";
output +=
    "Try to push when entire array is full:<br>";
try {
    flexibleStacks.push(2, 3000);
}
catch (error) {
    output +=
        `Exception : ${error.message}<br>`;
}
output += "<br>";
output +=
    "Final tops:<br>";
output +=
    `Stack 0 : ${flexibleStacks.peek(0)}<br>`;
output +=
    `Stack 1 : ${flexibleStacks.peek(1)}<br>`;
output +=
    `Stack 2 : ${flexibleStacks.peek(2)}<br><br>`;
output +=
    "<b>Study Complete.</b>";
document.querySelector("#t1").innerHTML =
    output;
