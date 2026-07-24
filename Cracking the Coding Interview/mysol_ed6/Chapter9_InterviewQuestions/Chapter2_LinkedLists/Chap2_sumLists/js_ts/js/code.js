class LinkedListNode {

    constructor(data) {
        this.data = data;
        this.next = null;
    }
}

class Chap2_removeDups {

    deleteDups(head) {

        const set = new Set();

        let previous = null;
        let current = head;

        while (current) {

            if (set.has(current.data)) {
                previous.next = current.next;
            } else {
                set.add(current.data);
                previous = current;
            }

            current = current.next;
        }
    }

    deleteDupsNoBuffer(head) {

        let current = head;

        while (current) {

            let runner = current;

            while (runner.next) {

                if (runner.next.data === current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }

            current = current.next;
        }
    }

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

const test = new Chap2_removeDups();

let output = ">>> CTCI Chapter 2.1 - Remove Dups <<<br><br>";

output += "<b>========== Solution 1 : deleteDups (HashSet) ==========</b><br><br>";

let list = test.buildList(1, 2, 3, 2, 4, 3, 5, 1);
output += "Original : " + test.listToString(list) + "<br>";
test.deleteDups(list);
output += "Result&nbsp;&nbsp;&nbsp;&nbsp;: " + test.listToString(list) + "<br><br>";

list = test.buildList(1, 1, 1, 1, 1);
output += "Original : " + test.listToString(list) + "<br>";
test.deleteDups(list);
output += "Result&nbsp;&nbsp;&nbsp;&nbsp;: " + test.listToString(list) + "<br><br>";

list = test.buildList(1, 2, 3, 4, 5);
output += "Original : " + test.listToString(list) + "<br>";
test.deleteDups(list);
output += "Result&nbsp;&nbsp;&nbsp;&nbsp;: " + test.listToString(list) + "<br><br>";

list = test.buildList(5);
output += "Original : " + test.listToString(list) + "<br>";
test.deleteDups(list);
output += "Result&nbsp;&nbsp;&nbsp;&nbsp;: " + test.listToString(list) + "<br><br>";

list = test.buildList();
output += "Original : " + test.listToString(list) + "<br>";
test.deleteDups(list);
output += "Result&nbsp;&nbsp;&nbsp;&nbsp;: " + test.listToString(list) + "<br><br>";

list = test.buildList(-1, 3, -1, 4, 3, 5, 5);
output += "Original : " + test.listToString(list) + "<br>";
test.deleteDups(list);
output += "Result&nbsp;&nbsp;&nbsp;&nbsp;: " + test.listToString(list) + "<br><br>";

output += "<b>========== Solution 2 : deleteDupsNoBuffer ==========</b><br><br>";

list = test.buildList(1, 2, 3, 2, 4, 3, 5, 1);
output += "Original : " + test.listToString(list) + "<br>";
test.deleteDupsNoBuffer(list);
output += "Result&nbsp;&nbsp;&nbsp;&nbsp;: " + test.listToString(list) + "<br><br>";

list = test.buildList(1, 1, 1, 1, 1);
output += "Original : " + test.listToString(list) + "<br>";
test.deleteDupsNoBuffer(list);
output += "Result&nbsp;&nbsp;&nbsp;&nbsp;: " + test.listToString(list) + "<br><br>";

list = test.buildList(1, 2, 3, 4, 5);
output += "Original : " + test.listToString(list) + "<br>";
test.deleteDupsNoBuffer(list);
output += "Result&nbsp;&nbsp;&nbsp;&nbsp;: " + test.listToString(list) + "<br><br>";

output += "<b>Study Complete.</b>";

document.querySelector("#t1").innerHTML = output;