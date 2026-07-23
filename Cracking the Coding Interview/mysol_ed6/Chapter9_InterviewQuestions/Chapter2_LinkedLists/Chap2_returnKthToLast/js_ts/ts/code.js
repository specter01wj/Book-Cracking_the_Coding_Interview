class LinkedListNode {
    data;
    next = null;
    constructor(data) {
        this.data = data;
    }
}
class Index {
    value = 0;
}
class Chap2_returnKthToLast {
    //====================================================
    // Solution 1 (Book)
    //====================================================
    printKthToLast(head, k) {
        if (head === null) {
            return 0;
        }
        const index = this.printKthToLast(head.next, k) + 1;
        if (index === k) {
            output += `k = ${k} : ${head.data}<br>`;
        }
        return index;
    }
    //====================================================
    // Solution 2 (Book)
    //====================================================
    kthToLast(head, k) {
        const idx = new Index();
        return this.kthToLastHelper(head, k, idx);
    }
    kthToLastHelper(head, k, idx) {
        if (head === null) {
            return null;
        }
        const node = this.kthToLastHelper(head.next, k, idx);
        idx.value++;
        if (idx.value === k) {
            return head;
        }
        return node;
    }
    //====================================================
    // Solution 3 (Book)
    //====================================================
    nthToLast(head, k) {
        if (head === null || k <= 0) {
            return null;
        }
        let p1 = head;
        let p2 = head;
        for (let i = 0; i < k; i++) {
            if (p1 === null) {
                return null;
            }
            p1 = p1.next;
        }
        while (p1 !== null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
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
}
const test = new Chap2_returnKthToLast();
let output = ">>> CTCI Chapter 2.2 - Return Kth To Last <<<br><br>";
const list = test.buildList(10, 20, 30, 40, 50, 60, 70);
output += `Linked List : ${test.listToString(list)}<br><br>`;
output += "<b>========== Solution 1 : printKthToLast ==========</b><br><br>";
for (let k = 1; k <= 8; k++) {
    const before = output.length;
    test.printKthToLast(list, k);
    if (output.length === before) {
        output += `k = ${k} : null<br>`;
    }
}
output += "<br><b>========== Solution 2 : kthToLast (Recursive Wrapper) ==========</b><br><br>";
for (let k = 1; k <= 8; k++) {
    const node = test.kthToLast(list, k);
    output += `k = ${k} : ${node ? node.data : "null"}<br>`;
}
output += "<br><b>========== Solution 3 : nthToLast (Two Pointers) ==========</b><br><br>";
for (let k = 1; k <= 8; k++) {
    const node = test.nthToLast(list, k);
    output += `k = ${k} : ${node ? node.data : "null"}<br>`;
}
output += "<br><b>========== Edge Cases ==========</b><br><br>";
const single = test.buildList(100);
output += `Single Node : ${test.listToString(single)}<br>`;
output += `k = 1 : ${test.nthToLast(single, 1)?.data}<br>`;
output += `k = 2 : ${test.nthToLast(single, 2)}<br><br>`;
const empty = test.buildList();
output += `Empty List : ${test.listToString(empty)}<br>`;
output += `k = 1 : ${test.nthToLast(empty, 1)}<br><br>`;
output += "<b>Study Complete.</b>";
document.querySelector("#t1").innerHTML = output;
