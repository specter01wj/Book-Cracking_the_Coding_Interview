class LinkedListNode {
    data;
    next = null;
    constructor(data) {
        this.data = data;
    }
}
class PartialSum {
    sum = null;
    carry = 0;
}
class Chap2_sumLists {
    //====================================================
    // Helpers
    //====================================================
    buildList(...values) {
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
    listToString(head) {
        if (head === null) {
            return "Empty";
        }
        const result = [];
        while (head !== null) {
            result.push(head.data);
            head = head.next;
        }
        return result.join(" -> ");
    }
    //====================================================
    // Solution 1 (Book)
    // Digits stored in reverse order
    //====================================================
    addLists(l1, l2, carry) {
        if (l1 === null && l2 === null && carry === 0) {
            return null;
        }
        let value = carry;
        if (l1 !== null) {
            value += l1.data;
        }
        if (l2 !== null) {
            value += l2.data;
        }
        const result = new LinkedListNode(value % 10);
        if (l1 !== null || l2 !== null) {
            result.next = this.addLists(l1 === null ? null : l1.next, l2 === null ? null : l2.next, value >= 10 ? 1 : 0);
        }
        return result;
    }
    //====================================================
    // Solution 2 (Book Follow Up)
    // Digits stored in forward order
    //====================================================
    addLists2(l1, l2) {
        const len1 = this.length(l1);
        const len2 = this.length(l2);
        // Pad the shorter list with zeros.
        if (len1 < len2) {
            l1 = this.padList(l1, len2 - len1);
        }
        else if (len2 < len1) {
            l2 = this.padList(l2, len1 - len2);
        }
        const sum = this.addListsHelper(l1, l2);
        if (sum.carry === 0) {
            return sum.sum;
        }
        else {
            return this.insertBefore(sum.sum, sum.carry);
        }
    }
    addListsHelper(l1, l2) {
        if (l1 === null && l2 === null) {
            return new PartialSum();
        }
        const sum = this.addListsHelper(l1.next, l2.next);
        const val = sum.carry + l1.data + l2.data;
        const fullResult = this.insertBefore(sum.sum, val % 10);
        sum.sum = fullResult;
        sum.carry = Math.floor(val / 10);
        return sum;
    }
    //====================================================
    // Helpers for Solution 2
    //====================================================
    padList(l, padding) {
        let head = l;
        for (let i = 0; i < padding; i++) {
            head = this.insertBefore(head, 0);
        }
        return head;
    }
    insertBefore(list, data) {
        const node = new LinkedListNode(data);
        if (list !== null) {
            node.next = list;
        }
        return node;
    }
    length(head) {
        let size = 0;
        while (head !== null) {
            size++;
            head = head.next;
        }
        return size;
    }
}
const test = new Chap2_sumLists();
let output = ">>> CTCI Chapter 2.5 - Sum Lists <<<br><br>";
//====================================================
// Solution 1
//====================================================
output += "<b>========== Solution 1 : Reverse Order ==========</b><br><br>";
let l1 = test.buildList(7, 1, 6);
let l2 = test.buildList(5, 9, 2);
output += `List1 : ${test.listToString(l1)}<br>`;
output += `List2 : ${test.listToString(l2)}<br>`;
let result = test.addLists(l1, l2, 0);
output += `Result: ${test.listToString(result)}<br><br>`;
l1 = test.buildList(9, 9, 9);
l2 = test.buildList(1);
output += `List1 : ${test.listToString(l1)}<br>`;
output += `List2 : ${test.listToString(l2)}<br>`;
result = test.addLists(l1, l2, 0);
output += `Result: ${test.listToString(result)}<br><br>`;
l1 = test.buildList(0);
l2 = test.buildList(0);
result = test.addLists(l1, l2, 0);
output += `0 + 0 : ${test.listToString(result)}<br><br>`;
l1 = test.buildList(1, 8);
l2 = test.buildList(0);
result = test.addLists(l1, l2, 0);
output += `18 + 0 : ${test.listToString(result)}<br><br>`;
l1 = null;
l2 = test.buildList(5, 4);
result = test.addLists(l1, l2, 0);
output += `Empty + List : ${test.listToString(result)}<br><br>`;
//====================================================
// Solution 2
//====================================================
output += "<b>========== Solution 2 : Forward Order ==========</b><br><br>";
l1 = test.buildList(6, 1, 7);
l2 = test.buildList(2, 9, 5);
output += `List1 : ${test.listToString(l1)}<br>`;
output += `List2 : ${test.listToString(l2)}<br>`;
result = test.addLists2(l1, l2);
output += `Result: ${test.listToString(result)}<br><br>`;
l1 = test.buildList(9, 9, 9);
l2 = test.buildList(1);
result = test.addLists2(l1, l2);
output += `999 + 1 : ${test.listToString(result)}<br><br>`;
l1 = test.buildList(1, 2, 3, 4);
l2 = test.buildList(5, 6, 7);
result = test.addLists2(l1, l2);
output += `1234 + 567 : ${test.listToString(result)}<br><br>`;
l1 = test.buildList(0);
l2 = test.buildList(0);
result = test.addLists2(l1, l2);
output += `0 + 0 : ${test.listToString(result)}<br><br>`;
l1 = null;
l2 = test.buildList(5, 4);
result = test.addLists2(l1, l2);
output += `Empty + List : ${test.listToString(result)}<br><br>`;
output += "<b>Study Complete.</b>";
document.querySelector("#t1").innerHTML = output;
