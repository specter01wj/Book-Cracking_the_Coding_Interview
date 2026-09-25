//====================================================
// Solution
//====================================================

function sort(s) {

    const r = [];

    while (s.length !== 0) {

        /*
         * Insert each element in s in sorted order into r.
         */
        const tmp = s.pop();

        while (r.length !== 0 && r[r.length - 1] > tmp) {
            s.push(r.pop());
        }

        r.push(tmp);
    }

    /*
     * Copy the elements from r back into s.
     */
    while (r.length !== 0) {
        s.push(r.pop());
    }
}


//====================================================
// Test Helper
//====================================================

function stackToString(stack) {
    return "[" + stack.join(", ") + "]";
}

function printPopOrder(stack) {

    const values = [];

    while (stack.length !== 0) {
        values.push(stack.pop());
    }

    return values.join(" ");
}


//====================================================
// Tests
//====================================================

let output = "";

output += "<b>>>> CTCI Chapter 3.5 - Sort Stack <<<</b><br><br>";


//====================================================
// Test 1: Book-style example
//====================================================

output += "<b>Test 1: Book-style example</b><br>";

const stack1 = [];

stack1.push(7);
stack1.push(10);
stack1.push(5);
stack1.push(8);
stack1.push(12);
stack1.push(3);
stack1.push(1);

output += "Before sort: " + stackToString(stack1) + "<br>";

sort(stack1);

output += "After sort:  " + stackToString(stack1) + "<br>";
output += "Top: " + stack1[stack1.length - 1] + "<br>";
output += "Pop order: " + printPopOrder(stack1) + "<br><br>";


//====================================================
// Test 2: Reverse order
//====================================================

output += "<b>Test 2: Reverse order</b><br>";

const stack2 = [];

stack2.push(5);
stack2.push(4);
stack2.push(3);
stack2.push(2);
stack2.push(1);

output += "Before sort: " + stackToString(stack2) + "<br>";

sort(stack2);

output += "After sort:  " + stackToString(stack2) + "<br>";
output += "Pop order: " + printPopOrder(stack2) + "<br><br>";


//====================================================
// Test 3: Already sorted
//====================================================

output += "<b>Test 3: Already sorted</b><br>";

const stack3 = [];

stack3.push(1);
stack3.push(2);
stack3.push(3);
stack3.push(4);
stack3.push(5);

output += "Before sort: " + stackToString(stack3) + "<br>";

sort(stack3);

output += "After sort:  " + stackToString(stack3) + "<br>";
output += "Pop order: " + printPopOrder(stack3) + "<br><br>";


//====================================================
// Test 4: Duplicate values
//====================================================

output += "<b>Test 4: Duplicate values</b><br>";

const stack4 = [];

stack4.push(4);
stack4.push(2);
stack4.push(5);
stack4.push(2);
stack4.push(3);
stack4.push(4);
stack4.push(1);

output += "Before sort: " + stackToString(stack4) + "<br>";

sort(stack4);

output += "After sort:  " + stackToString(stack4) + "<br>";
output += "Pop order: " + printPopOrder(stack4) + "<br><br>";


//====================================================
// Test 5: Negative numbers
//====================================================

output += "<b>Test 5: Negative numbers</b><br>";

const stack5 = [];

stack5.push(3);
stack5.push(-2);
stack5.push(7);
stack5.push(0);
stack5.push(-5);
stack5.push(4);

output += "Before sort: " + stackToString(stack5) + "<br>";

sort(stack5);

output += "After sort:  " + stackToString(stack5) + "<br>";
output += "Pop order: " + printPopOrder(stack5) + "<br><br>";


//====================================================
// Test 6: Single element
//====================================================

output += "<b>Test 6: Single element</b><br>";

const stack6 = [];

stack6.push(42);

output += "Before sort: " + stackToString(stack6) + "<br>";

sort(stack6);

output += "After sort:  " + stackToString(stack6) + "<br>";
output += "Pop order: " + printPopOrder(stack6) + "<br><br>";


//====================================================
// Test 7: Empty stack
//====================================================

output += "<b>Test 7: Empty stack</b><br>";

const stack7 = [];

output += "Before sort: " + stackToString(stack7) + "<br>";

sort(stack7);

output += "After sort:  " + stackToString(stack7) + "<br><br>";

output += "<b>Study Complete.</b>";

document.querySelector("#t1").innerHTML = output;