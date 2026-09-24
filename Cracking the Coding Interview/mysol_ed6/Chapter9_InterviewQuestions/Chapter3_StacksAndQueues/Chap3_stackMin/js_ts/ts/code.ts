class NodeWithMin {

    value: number;
    min: number;

    constructor(value: number, min: number) {

        this.value = value;
        this.min = min;
    }

}


class StackWithMin {

    private stack: NodeWithMin[];

    constructor() {

        this.stack = [];
    }

    //====================================================
    // Solution 1 (Book)
    // Store Minimum in Every Node
    //====================================================

    push(value: number): void {

        const newMin =
            Math.min(
                value,
                this.min()
            );

        this.stack.push(
            new NodeWithMin(
                value,
                newMin
            )
        );
    }

    pop(): number {

        if (this.isEmpty()) {
            throw new Error(
                "Stack is empty."
            );
        }

        return this.stack.pop()!.value;
    }

    peek(): number {

        if (this.isEmpty()) {
            throw new Error(
                "Stack is empty."
            );
        }

        return this.stack[
            this.stack.length - 1
        ].value;
    }

    min(): number {

        if (this.isEmpty()) {
            return Number.POSITIVE_INFINITY;
        }

        return this.stack[
            this.stack.length - 1
        ].min;
    }

    isEmpty(): boolean {

        return this.stack.length === 0;
    }

    size(): number {

        return this.stack.length;
    }

    //====================================================
    // Display Helper
    //====================================================

    toString(): string {

        const values: number[] =
            this.stack.map(
                node => node.value
            );

        return `[${values.join(", ")}]`;
    }
}


class StackWithMin2 {

    private stack: number[];
    private minStack: number[];

    constructor() {

        this.stack = [];

        this.minStack = [];
    }

    //====================================================
    // Solution 2 (Book)
    // Auxiliary Stack for Minimums
    //====================================================

    push(value: number): void {

        /*
         * <= is important.
         *
         * If value equals the current minimum,
         * the duplicate minimum must also be stored.
         */
        if (value <= this.min()) {
            this.minStack.push(value);
        }

        this.stack.push(value);
    }

    pop(): number {

        if (this.isEmpty()) {
            throw new Error(
                "Stack is empty."
            );
        }

        const value =
            this.stack.pop()!;

        /*
         * If the value being removed is the
         * current minimum, remove it from
         * the min stack as well.
         */
        if (value === this.min()) {
            this.minStack.pop();
        }

        return value;
    }

    peek(): number {

        if (this.isEmpty()) {
            throw new Error(
                "Stack is empty."
            );
        }

        return this.stack[
            this.stack.length - 1
        ];
    }

    min(): number {

        if (this.minStack.length === 0) {
            return Number.POSITIVE_INFINITY;
        }

        return this.minStack[
            this.minStack.length - 1
        ];
    }

    isEmpty(): boolean {

        return this.stack.length === 0;
    }

    size(): number {

        return this.stack.length;
    }

    //====================================================
    // Display Helpers
    //====================================================

    toString(): string {

        return `[${this.stack.join(", ")}]`;
    }

    minStackToString(): string {

        return `[${this.minStack.join(", ")}]`;
    }
}


//====================================================
// Tests
//====================================================

let output =
    ">>> CTCI Chapter 3.2 - Stack Min <<<<br><br>";


//====================================================
// Solution 1
//====================================================

output +=
    "<b>========== Solution 1 : Min in Every Node ==========</b><br><br>";

const stack1 =
    new StackWithMin();


output += "Push 5:<br>";

stack1.push(5);

output +=
    `Stack : ${stack1.toString()}<br>`;

output +=
    `Min   : ${stack1.min()}<br><br>`;


output += "Push 6:<br>";

stack1.push(6);

output +=
    `Stack : ${stack1.toString()}<br>`;

output +=
    `Min   : ${stack1.min()}<br><br>`;


output += "Push 3:<br>";

stack1.push(3);

output +=
    `Stack : ${stack1.toString()}<br>`;

output +=
    `Min   : ${stack1.min()}<br><br>`;


output += "Push 7:<br>";

stack1.push(7);

output +=
    `Stack : ${stack1.toString()}<br>`;

output +=
    `Min   : ${stack1.min()}<br><br>`;


output +=
    `Pop : ${stack1.pop()}<br>`;

output +=
    `Stack : ${stack1.toString()}<br>`;

output +=
    `Min   : ${stack1.min()}<br><br>`;


output +=
    `Pop : ${stack1.pop()}<br>`;

output +=
    `Stack : ${stack1.toString()}<br>`;

output +=
    `Min   : ${stack1.min()}<br><br>`;


//====================================================
// Solution 1 - Duplicate Minimum Test
//====================================================

output +=
    "Test duplicate minimums:<br>";

stack1.push(3);
stack1.push(3);

output +=
    `Stack : ${stack1.toString()}<br>`;

output +=
    `Min   : ${stack1.min()}<br><br>`;


output +=
    `Pop : ${stack1.pop()}<br>`;

output +=
    `Min : ${stack1.min()}<br><br>`;


output +=
    `Pop : ${stack1.pop()}<br>`;

output +=
    `Min : ${stack1.min()}<br><br>`;


//====================================================
// Solution 2
//====================================================

output +=
    "<b>========== Solution 2 : Auxiliary Min Stack ==========</b><br><br>";

const stack2 =
    new StackWithMin2();


output += "Push 5:<br>";

stack2.push(5);

output +=
    `Stack     : ${stack2.toString()}<br>`;

output +=
    `Min Stack : ${stack2.minStackToString()}<br>`;

output +=
    `Min       : ${stack2.min()}<br><br>`;


output += "Push 6:<br>";

stack2.push(6);

output +=
    `Stack     : ${stack2.toString()}<br>`;

output +=
    `Min Stack : ${stack2.minStackToString()}<br>`;

output +=
    `Min       : ${stack2.min()}<br><br>`;


output += "Push 3:<br>";

stack2.push(3);

output +=
    `Stack     : ${stack2.toString()}<br>`;

output +=
    `Min Stack : ${stack2.minStackToString()}<br>`;

output +=
    `Min       : ${stack2.min()}<br><br>`;


output += "Push 7:<br>";

stack2.push(7);

output +=
    `Stack     : ${stack2.toString()}<br>`;

output +=
    `Min Stack : ${stack2.minStackToString()}<br>`;

output +=
    `Min       : ${stack2.min()}<br><br>`;


output +=
    `Pop : ${stack2.pop()}<br>`;

output +=
    `Stack     : ${stack2.toString()}<br>`;

output +=
    `Min Stack : ${stack2.minStackToString()}<br>`;

output +=
    `Min       : ${stack2.min()}<br><br>`;


output +=
    `Pop : ${stack2.pop()}<br>`;

output +=
    `Stack     : ${stack2.toString()}<br>`;

output +=
    `Min Stack : ${stack2.minStackToString()}<br>`;

output +=
    `Min       : ${stack2.min()}<br><br>`;


//====================================================
// Solution 2 - Duplicate Minimum Test
//====================================================

output +=
    "Test duplicate minimums:<br>";

stack2.push(3);
stack2.push(3);

output +=
    `Stack     : ${stack2.toString()}<br>`;

output +=
    `Min Stack : ${stack2.minStackToString()}<br>`;

output +=
    `Min       : ${stack2.min()}<br><br>`;


output +=
    `Pop : ${stack2.pop()}<br>`;

output +=
    `Stack     : ${stack2.toString()}<br>`;

output +=
    `Min Stack : ${stack2.minStackToString()}<br>`;

output +=
    `Min       : ${stack2.min()}<br><br>`;


output +=
    `Pop : ${stack2.pop()}<br>`;

output +=
    `Stack     : ${stack2.toString()}<br>`;

output +=
    `Min Stack : ${stack2.minStackToString()}<br>`;

output +=
    `Min       : ${stack2.min()}<br><br>`;


//====================================================
// Empty Stack Test
//====================================================

output +=
    "<b>========== Empty Stack Test ==========</b><br><br>";

const emptyStack =
    new StackWithMin2();

try {

    emptyStack.pop();

} catch (error) {

    output +=
        `Pop from empty stack -> ${(error as Error).message}<br>`;
}

output +=
    `Min of empty stack : ${emptyStack.min()}<br><br>`;


output +=
    "<b>Study Complete.</b>";


(document.querySelector("#t1") as HTMLElement).innerHTML =
    output;