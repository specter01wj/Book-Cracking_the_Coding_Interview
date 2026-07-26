class LinkedListNode {
    data;
    next = null;
    constructor(data) {
        this.data = data;
    }
}
class Result {
    tail;
    size;
    constructor(tail, size) {
        this.tail = tail;
        this.size = size;
    }
}
function findIntersection(list1, list2) {
    if (list1 === null || list2 === null) {
        return null;
    }
    const result1 = getTailAndSize(list1);
    const result2 = getTailAndSize(list2);
    if (result1.tail !== result2.tail) {
        return null;
    }
    let shorter = result1.size < result2.size ? list1 : list2;
    let longer = result1.size < result2.size ? list2 : list1;
    longer = getKthNode(longer, Math.abs(result1.size - result2.size));
    while (shorter !== longer) {
        shorter = shorter.next;
        longer = longer.next;
    }
    return longer;
}
function getTailAndSize(list) {
    let size = 1;
    let current = list;
    while (current.next !== null) {
        size++;
        current = current.next;
    }
    return new Result(current, size);
}
function getKthNode(head, k) {
    let current = head;
    while (k > 0 && current !== null) {
        current = current.next;
        k--;
    }
    return current;
}
function createList(...values) {
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
function getLastNode(head) {
    if (head === null) {
        return null;
    }
    let current = head;
    while (current.next !== null) {
        current = current.next;
    }
    return current;
}
function listToString(head) {
    if (head === null) {
        return "null";
    }
    const values = [];
    let current = head;
    while (current !== null) {
        values.push(current.data);
        current = current.next;
    }
    return values.join(" -> ");
}
function nodeValue(node) {
    return node === null ? "null" : String(node.data);
}
function runTest(testName, list1, list2, expected) {
    const actual = findIntersection(list1, list2);
    let output = `<b>${testName}</b><br>`;
    output += `List 1: ${listToString(list1)}<br>`;
    output += `List 2: ${listToString(list2)}<br>`;
    output += `Expected: ${nodeValue(expected)}<br>`;
    output += `Actual: ${nodeValue(actual)}<br>`;
    output += `Result: ${actual === expected ? "PASS" : "FAIL"}<br><br>`;
    return output;
}
// Test 1: Different lengths, intersection in the middle
const shared1 = createList(7, 2, 1);
const list1 = createList(3, 1, 5, 9);
getLastNode(list1).next = shared1;
const list2 = createList(4, 6);
getLastNode(list2).next = shared1;
// Test 2: Equal lengths
const shared2 = createList(8, 10);
const list3 = createList(1, 2);
getLastNode(list3).next = shared2;
const list4 = createList(3, 4);
getLastNode(list4).next = shared2;
// Test 3: Same head
const sameList = createList(11, 12, 13);
// Test 4: Intersection at tail
const sharedTail = new LinkedListNode(99);
const list5 = createList(1, 2, 3);
getLastNode(list5).next = sharedTail;
const list6 = createList(4, 5);
getLastNode(list6).next = sharedTail;
// Test 5: Same values, different references
const list7 = createList(1, 2, 3);
const list8 = createList(1, 2, 3);
// Test 6: No intersection
const list9 = createList(1, 2, 3);
const list10 = createList(4, 5, 6);
// Test 7
const list11 = createList(1, 2, 3);
let output = ">>> CTCI Chapter 2.7 – Intersection <<br><br>";
output += runTest("Test 1: Different lengths", list1, list2, shared1);
output += runTest("Test 2: Equal lengths", list3, list4, shared2);
output += runTest("Test 3: Same head", sameList, sameList, sameList);
output += runTest("Test 4: Intersection at tail", list5, list6, sharedTail);
output += runTest("Test 5: Same values, different references", list7, list8, null);
output += runTest("Test 6: No intersection", list9, list10, null);
output += runTest("Test 7: First list is null", null, list11, null);
output += runTest("Test 8: Both lists are null", null, null, null);
document.querySelector("#t1").innerHTML = output;
