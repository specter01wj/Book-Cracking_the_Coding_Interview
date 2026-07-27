class LinkedListNode {

    data: any;
    next: LinkedListNode | null = null;

    constructor(data: any) {
        this.data = data;
    }

}

class Chap2_loopDetection {

    //====================================================
    // Book Solution
    //====================================================

    findBeginning(head: LinkedListNode | null): LinkedListNode | null {

        let slow = head;
        let fast = head;

        while (fast !== null && fast.next !== null) {

            slow = slow!.next;
            fast = fast.next.next;

            if (slow === fast) {
                break;
            }
        }

        if (fast === null || fast.next === null) {
            return null;
        }

        slow = head;

        while (slow !== fast) {
            slow = slow!.next;
            fast = fast!.next;
        }

        return fast;
    }

    //====================================================
    // Helpers
    //====================================================

    createList(...values: any[]): LinkedListNode | null {

        if (values.length === 0) {
            return null;
        }

        const head = new LinkedListNode(values[0]);
        let current = head;

        for (let i = 1; i < values.length; i++) {
            current.next = new LinkedListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    getLastNode(head: LinkedListNode | null): LinkedListNode | null {

        if (head === null) {
            return null;
        }

        let current = head;

        while (current.next !== null) {
            current = current.next;
        }

        return current;
    }

    getNode(head: LinkedListNode | null, index: number): LinkedListNode | null {

        let current = head;

        while (index > 0 && current !== null) {
            current = current.next;
            index--;
        }

        return current;
    }

    nodeValue(node: LinkedListNode | null): string {

        return node === null ? "null" : String(node.data);
    }

}

const test = new Chap2_loopDetection();

let output = ">>> CTCI Chapter 2.8 - Loop Detection <<<br><br>";

//====================================================
// Test 1: Book Example
//====================================================

const a = new LinkedListNode("A");
const b = new LinkedListNode("B");
const c = new LinkedListNode("C");
const d = new LinkedListNode("D");
const e = new LinkedListNode("E");

a.next = b;
b.next = c;
c.next = d;
d.next = e;
e.next = c;

let actual = test.findBeginning(a);

output += "<b>========== Test 1 : Book Example ==========</b><br><br>";
output += `Expected : C<br>`;
output += `Actual&nbsp;&nbsp;&nbsp;&nbsp;: ${test.nodeValue(actual)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${actual === c ? "PASS" : "FAIL"}<br><br>`;

//====================================================
// Test 2: No Loop
//====================================================

let list = test.createList(1, 2, 3, 4, 5);

actual = test.findBeginning(list);

output += "<b>========== Test 2 : No Loop ==========</b><br><br>";
output += `Expected : null<br>`;
output += `Actual&nbsp;&nbsp;&nbsp;&nbsp;: ${test.nodeValue(actual)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${actual === null ? "PASS" : "FAIL"}<br><br>`;

//====================================================
// Test 3: Loop Starts at Head
//====================================================

list = test.createList(1, 2, 3, 4);

test.getLastNode(list)!.next = list;

actual = test.findBeginning(list);

output += "<b>========== Test 3 : Loop Starts at Head ==========</b><br><br>";
output += `Expected : 1<br>`;
output += `Actual&nbsp;&nbsp;&nbsp;&nbsp;: ${test.nodeValue(actual)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${actual === list ? "PASS" : "FAIL"}<br><br>`;

//====================================================
// Test 4: Single Node
//====================================================

const single = new LinkedListNode(10);

actual = test.findBeginning(single);

output += "<b>========== Test 4 : Single Node ==========</b><br><br>";
output += `Expected : null<br>`;
output += `Actual&nbsp;&nbsp;&nbsp;&nbsp;: ${test.nodeValue(actual)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${actual === null ? "PASS" : "FAIL"}<br><br>`;

//====================================================
// Test 5: Self Loop
//====================================================

const selfLoop = new LinkedListNode(99);
selfLoop.next = selfLoop;

actual = test.findBeginning(selfLoop);

output += "<b>========== Test 5 : Self Loop ==========</b><br><br>";
output += `Expected : 99<br>`;
output += `Actual&nbsp;&nbsp;&nbsp;&nbsp;: ${test.nodeValue(actual)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${actual === selfLoop ? "PASS" : "FAIL"}<br><br>`;

//====================================================
// Test 6: Two Node Loop
//====================================================

const n1 = new LinkedListNode(1);
const n2 = new LinkedListNode(2);

n1.next = n2;
n2.next = n1;

actual = test.findBeginning(n1);

output += "<b>========== Test 6 : Two Node Loop ==========</b><br><br>";
output += `Expected : 1<br>`;
output += `Actual&nbsp;&nbsp;&nbsp;&nbsp;: ${test.nodeValue(actual)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${actual === n1 ? "PASS" : "FAIL"}<br><br>`;

//====================================================
// Test 7: Loop in Middle
//====================================================

list = test.createList(1, 2, 3, 4, 5, 6);

const loopStart = test.getNode(list, 3)!;

test.getLastNode(list)!.next = loopStart;

actual = test.findBeginning(list);

output += "<b>========== Test 7 : Loop in Middle ==========</b><br><br>";
output += `Expected : 4<br>`;
output += `Actual&nbsp;&nbsp;&nbsp;&nbsp;: ${test.nodeValue(actual)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${actual === loopStart ? "PASS" : "FAIL"}<br><br>`;

//====================================================
// Test 8: Empty List
//====================================================

actual = test.findBeginning(null);

output += "<b>========== Test 8 : Empty List ==========</b><br><br>";
output += `Expected : null<br>`;
output += `Actual&nbsp;&nbsp;&nbsp;&nbsp;: ${test.nodeValue(actual)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${actual === null ? "PASS" : "FAIL"}<br><br>`;

output += "<b>Study Complete.</b>";

(document.querySelector("#t1") as HTMLElement).innerHTML = output;