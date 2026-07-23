class LinkedListNode {

    constructor(data) {
        this.data = data;
        this.next = null;
    }

}

class Chap2_partition {

    //====================================================
    // Solution 1 (Book)
    //====================================================

    partition(node, x) {

        let beforeStart = null;
        let beforeEnd = null;
        let afterStart = null;
        let afterEnd = null;

        while (node !== null) {

            const next = node.next;
            node.next = null;

            if (node.data < x) {

                if (beforeStart === null) {

                    beforeStart = node;
                    beforeEnd = beforeStart;

                } else {

                    beforeEnd.next = node;
                    beforeEnd = node;
                }

            } else {

                if (afterStart === null) {

                    afterStart = node;
                    afterEnd = afterStart;

                } else {

                    afterEnd.next = node;
                    afterEnd = node;
                }
            }

            node = next;
        }

        if (beforeStart === null) {
            return afterStart;
        }

        beforeEnd.next = afterStart;

        return beforeStart;
    }

    //====================================================
    // Solution 2 (Book)
    //====================================================

    partition2(node, x) {

        if (node === null) {
            return null;
        }

        let head = node;
        let tail = node;

        while (node !== null) {

            const next = node.next;

            if (node.data < x) {

                node.next = head;
                head = node;

            } else {

                tail.next = node;
                tail = node;
            }

            node = next;
        }

        tail.next = null;

        return head;
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

const test = new Chap2_partition();

let output = ">>> CTCI Chapter 2.4 - Partition <<<br><br>";

output += "<b>========== Solution 1 : Stable Partition ==========</b><br><br>";

let list = test.buildList(3, 5, 8, 5, 10, 2, 1);

output += `Original : ${test.listToString(list)}<br>`;

list = test.partition(list, 5);

output += "Partition = 5<br>";
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

output += "<b>========== Solution 2 : Head / Tail ==========</b><br><br>";

list = test.buildList(3, 5, 8, 5, 10, 2, 1);

output += `Original : ${test.listToString(list)}<br>`;

list = test.partition2(list, 5);

output += "Partition = 5<br>";
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

output += "<b>========== More Tests ==========</b><br><br>";

list = test.buildList(1, 2, 3, 4);

output += `Original : ${test.listToString(list)}<br>`;
list = test.partition(list, 5);
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

list = test.buildList(9, 8, 7, 6);

output += `Original : ${test.listToString(list)}<br>`;
list = test.partition(list, 5);
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

list = test.buildList(5, 5, 5, 5);

output += `Original : ${test.listToString(list)}<br>`;
list = test.partition(list, 5);
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

list = test.buildList(2);

output += `Original : ${test.listToString(list)}<br>`;
list = test.partition(list, 5);
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

list = test.buildList();

output += `Original : ${test.listToString(list)}<br>`;
list = test.partition(list, 5);
output += `Result&nbsp;&nbsp;&nbsp;&nbsp;: ${test.listToString(list)}<br><br>`;

output += "<b>Study Complete.</b>";

document.querySelector("#t1").innerHTML = output;