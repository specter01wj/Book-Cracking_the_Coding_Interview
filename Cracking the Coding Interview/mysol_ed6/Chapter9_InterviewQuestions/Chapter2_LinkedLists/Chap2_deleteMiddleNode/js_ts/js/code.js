class LinkedListNode {

    constructor(data) {
        this.data = data;
        this.next = null;
    }

}

class Chap2_deleteMiddleNode {

    //====================================================
    // Book Solution
    //====================================================

    deleteNode(node) {

        if (node === null || node.next === null) {
            return false;
        }

        node.data = node.next.data;
        node.next = node.next.next;

        return true;
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

    findNode(head, value) {

        while (head !== null) {

            if (head.data === value) {
                return head;
            }

            head = head.next;
        }

        return null;
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

const test = new Chap2_deleteMiddleNode();

let output = ">>> CTCI Chapter 2.3 - Delete Middle Node <<<br><br>";

output += "<b>========== Test 1 : Delete Middle Node ==========</b><br><br>";

let list = test.buildList("a", "b", "c", "d", "e", "f");

output += `Original : ${test.listToString(list)}<br>`;

let node = test.findNode(list, "c");

output += `Delete Node : ${node.data}<br>`;
output += `Success : ${test.deleteNode(node)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

output += "<b>========== Test 2 : Delete Another Middle Node ==========</b><br><br>";

list = test.buildList("a", "b", "c", "d", "e", "f");

output += `Original : ${test.listToString(list)}<br>`;

node = test.findNode(list, "e");

output += `Delete Node : ${node.data}<br>`;
output += `Success : ${test.deleteNode(node)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

output += "<b>========== Test 3 : Last Node ==========</b><br><br>";

list = test.buildList("a", "b", "c");

output += `Original : ${test.listToString(list)}<br>`;

node = test.findNode(list, "c");

output += `Delete Node : ${node.data}<br>`;
output += `Success : ${test.deleteNode(node)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

output += "<b>========== Test 4 : Single Node ==========</b><br><br>";

list = test.buildList("x");

output += `Original : ${test.listToString(list)}<br>`;
output += `Success : ${test.deleteNode(list)}<br>`;
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

output += "<b>========== Test 5 : Null ==========</b><br><br>";

output += `Success : ${test.deleteNode(null)}<br><br>`;

output += "<b>Study Complete.</b>";

document.querySelector("#t1").innerHTML = output;